package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.service.ImageService;
import com.wmmzh.backend.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    private final ImageService imageService;

    public PersonController(PersonService personService, ImageService imageService) {
        this.personService = personService;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }
}
