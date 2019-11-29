package com.wmmzh.backend.service;

import com.wmmzh.backend.model.Image;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ImageService {

    List<Image> getAll(@PathVariable Long personId);

}
