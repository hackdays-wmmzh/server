package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class EreignissController {

    private ImageService imgService;

    public EreignissController(ImageService imgService) {
        this.imgService = imgService;
    }

    @GetMapping
    public List<Image> getAll() {
        return imgService.getAll();
    }
}
