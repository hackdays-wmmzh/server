package com.wmmzh.backend.service;

import com.wmmzh.backend.model.Image;

public interface ImageService {

    void add(long personId, Image image);

    String getTextFromImage(String base64);
}
