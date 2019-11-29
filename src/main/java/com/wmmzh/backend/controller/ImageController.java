package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/persons/{personId}/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public void post(@PathVariable Long personId, @RequestBody Image image) {
        imageService.add(personId, image);
    }

    @GetMapping
    public List<Image> getAll(@PathVariable Long personId) {
        return imageService.getAll(personId);
    }
}
