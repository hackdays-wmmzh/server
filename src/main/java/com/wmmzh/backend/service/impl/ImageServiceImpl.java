package com.wmmzh.backend.service.impl;

import com.wmmzh.backend.imagga.ImaggaClient;
import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.ocr.OcrClient;
import com.wmmzh.backend.repository.ImageRepository;
import com.wmmzh.backend.repository.PersonRepository;
import com.wmmzh.backend.service.ImageService;
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

    private static final List<String> allowedTags = Arrays.asList("papier", "dokument", "rechung", "text", "buch", "schreiben", "währung", "gewinn", "text");

    public ImageServiceImpl(ImaggaClient imaggaClient, OcrClient ocrClient, PersonRepository personRepo, ImageRepository imageRepo) {
        this.imaggaClient = imaggaClient;
        this.ocrClient = ocrClient;
        this.personRepo = personRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public void add(long personId, Image image) {
        Person person = personRepo.getById(personId).orElseThrow(() -> new IllegalArgumentException("Person '" + personId + "' does not exist!"));

        try {
            isImageTagAllowed(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        image.setPerson(person);
        imageRepo.save(image);
    }

    private void isImageTagAllowed(Image image) throws IOException {
        for (String imageTag : imaggaClient.getImageInfo(image.getContent())) {
            if (allowedTags.contains(imageTag.toLowerCase())) {
                break;
            }

            throw new IllegalStateException("Only pictures of documents allowed! Image was of " + imageTag);
        }
    }

    @Override
    public List<Image> getAll(Long personId) {
        return imageRepo.getAllByPersonId(personId);
    }

}
