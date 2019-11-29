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

    private static final List<String> allowedTags = Arrays.asList("papier", "dokument", "rechung", "text", "buch");

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
            String imageTag = imaggaClient.getImageInfo(image.getContent());

            if (!allowedTags.contains(imageTag.toLowerCase())) {
                throw new IllegalStateException("Only pictures of documents allowed!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        image.setPerson(person);
        imageRepo.save(image);
    }

    @Override
    public List<Image> getAll(Long personId) {
        return imageRepo.getAllByPersonId(personId);
    }

}
