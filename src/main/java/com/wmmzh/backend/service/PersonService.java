package com.wmmzh.backend.service;

import com.wmmzh.backend.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getAll();

    Optional<Person> getById(Long id);

}
