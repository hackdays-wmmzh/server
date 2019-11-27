package com.wmmzh.backend.controller;

import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public Optional<Person> getByEmail(@RequestParam String email) {
        return personService.getByEmail(email);
    }

}
