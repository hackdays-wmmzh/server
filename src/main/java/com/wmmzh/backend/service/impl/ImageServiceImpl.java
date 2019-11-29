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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        "finanzelles", "markt"
    );

    public ImageServiceImpl(ImaggaClient imaggaClient, OcrClient ocrClient, PersonRepository personRepo, ImageRepository imageRepo, EreignisService ereignisService) {
        this.imaggaClient = imaggaClient;
        this.ocrClient = ocrClient;
        this.personRepo = personRepo;
        this.imageRepo = imageRepo;
        this.ereignisService = ereignisService;
    }

    @Override
    public void add(long personId, Image image) {
        Person person = personRepo.getById(personId).orElseThrow(() -> new IllegalArgumentException("Person '" + personId + "' does not exist!"));
        Image savedImage = imageRepo.save(image);
        ereignisService.createEreignis(person, Ereignis.Type.RECHNUNG, "Rechnung eingereicht", savedImage);

        try {
            isImageTagAllowed(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imageRepo.save(image);
    }

    private void isImageTagAllowed(Image image) throws IOException {
        List<String> imageTags = imaggaClient.getImageInfo(image.getContent());
        for (String imageTag : imageTags) {
            if (allowedTags.contains(imageTag.toLowerCase())) {
                return;
            }
        }

        throw new IllegalStateException("Only pictures of documents allowed! Image was one of the following: " + String.join(", ", imageTags));
    }

    @Override
    public String getTextFromImage(String base64) {
        return ocrClient.getTextFromImage(base64);
    }

}
