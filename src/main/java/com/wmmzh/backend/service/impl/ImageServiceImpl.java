package com.wmmzh.backend.service.impl;

import com.wmmzh.backend.imagga.ImaggaClient;
import com.wmmzh.backend.model.Ereignis;
import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.ocr.OcrClient;
import com.wmmzh.backend.repository.ImageRepository;
import com.wmmzh.backend.repository.PersonRepository;
import com.wmmzh.backend.service.EreignisService;
import com.wmmzh.backend.service.ImageService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImaggaClient imaggaClient;
    private final OcrClient ocrClient;
    private PersonRepository personRepo;
    private ImageRepository imageRepo;
    private final EreignisService ereignisService;

    private static final List<String> allowedTags = Arrays.asList(
            "papier", "dokument", "rechung", "text", "buch", "schreiben", "währung", "gewinn", "text",
            "finanzelles", "markt", "textmaker", "erfolg", "grafik", "retro", "linien", "block", "kreative",
            "geschäft", "maschine", "schule", "digital", "dokuments", "im menü", "hinweis"
    );

    private static final List<String> catTags = Arrays.asList(
            "katze", "tabby", "tier"
    );

    public ImageServiceImpl(ImaggaClient imaggaClient, OcrClient ocrClient, PersonRepository personRepo, ImageRepository imageRepo, EreignisService ereignisService) {
        this.imaggaClient = imaggaClient;
        this.ocrClient = ocrClient;
        this.personRepo = personRepo;
        this.imageRepo = imageRepo;
        this.ereignisService = ereignisService;
    }

    @Override
    @Transactional
    public void add(long personId, Image image) {
        Person person = personRepo.getById(personId).orElseThrow(() -> new IllegalArgumentException("Person '" + personId + "' does not exist!"));
        Image savedImage = imageRepo.save(image);
        ereignisService.createEreignis(person, Ereignis.Type.RECHNUNG, "Rechnung eingereicht", savedImage);

        try {
            checkForCatTag(image);
            checkForAllowedImageTag(image);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        imageRepo.save(image);
    }

    private void checkForCatTag(Image image) throws IOException {
        List<String> imageTags = imaggaClient.getImageInfo(image.getContent());
        System.out.println(String.join(", ", imageTags));
        for (String imageTag : imageTags) {
            if (catTags.contains(imageTag.toLowerCase())) {
                String errorMessage = "Only pictures of documents allowed! Image was probably a cat or: " + String.join(", ", imageTags);
                throw new IllegalStateException(errorMessage);
            }
        }
    }

    private void checkForAllowedImageTag(Image image) throws IOException {
        List<String> imageTags = imaggaClient.getImageInfo(image.getContent());
        System.out.println(String.join(", ", imageTags));
        for (String imageTag : imageTags) {
            if (allowedTags.contains(imageTag.toLowerCase())) {
                return;
            }
        }

        String errorMessage = "Only pictures of documents allowed! Image was one of the following: " + String.join(", ", imageTags);
        throw new IllegalStateException(errorMessage);
    }

    @Override
    public List<Image> getAll() {
        return imageRepo.findAll();
    }

    @Override
    public String getTextFromImage(String base64) {
        return ocrClient.getTextFromImage(base64);
    }

}
