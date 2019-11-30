package com.wmmzh.backend.service;

import com.wmmzh.backend.model.Ereignis;
import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.model.Person;

import java.util.List;

public interface EreignisService {

    void createEreignis(Person person, Ereignis.Type type, String text, Image image);

    List<Ereignis> getAll();
}
