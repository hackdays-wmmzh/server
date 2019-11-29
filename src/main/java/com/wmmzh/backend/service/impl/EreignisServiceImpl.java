package com.wmmzh.backend.service.impl;

import com.wmmzh.backend.model.Ereignis;
import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.repository.EreignisRepository;
import com.wmmzh.backend.service.EreignisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EreignisServiceImpl implements EreignisService {

    @Autowired
    private EreignisRepository ereignisRepo;

    @Override
    public void createEreignis(Person person, Ereignis.Type type, String text, Image image) {
        Ereignis ereignis = new Ereignis();
        ereignis.setPerson(person);
        ereignis.setType(type);
        ereignis.setText(text);
        ereignis.setZeitstempel(LocalDateTime.now());
        ereignis.setImage(image);
        ereignisRepo.save(ereignis);
    }

}
