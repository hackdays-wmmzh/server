package com.wmmzh.backend.service;

import com.wmmzh.backend.model.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> getByEmail(String email);

}
