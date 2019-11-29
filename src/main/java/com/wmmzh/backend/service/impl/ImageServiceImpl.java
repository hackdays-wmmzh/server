package com.wmmzh.backend.service.impl;

import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.repository.ImageRepository;
import com.wmmzh.backend.repository.PersonRepository;
import com.wmmzh.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepo;

    @Autowired
    private PersonRepository personRepo;

    @Override
    public void add(long personId, Image image) {
        Person person = personRepo.getById(personId).orElseThrow(() -> new IllegalArgumentException("Person '" + personId + "' does not exist!"));
        image.setPerson(person);
        imageRepo.save(image);
    }

    @Override
    public List<Image> getAll(Long personId) {
        return imageRepo.getAllByPersonId(personId);
    }

}
