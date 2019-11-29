package com.wmmzh.backend.service;

import com.wmmzh.backend.model.Image;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface ImageService {

    void add(long personId, Image image);

    List<Image> getAll(@PathVariable Long personId);

    String getTextFromImage(String base64);
}
