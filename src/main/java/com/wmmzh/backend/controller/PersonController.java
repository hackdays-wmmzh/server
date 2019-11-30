package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(path = "/{id}")
    @Transactional
    public Optional<Person> getById(@PathVariable Long id) {
        return personService.getById(id);
    }
}
