package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Person> getById(@PathVariable Long id) {
        return personService.getById(id);
    }

}
