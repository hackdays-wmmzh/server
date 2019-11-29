package com.wmmzh.backend.service.impl;

import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.repository.PersonRepository;
import com.wmmzh.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepo;

    @Override
    public List<Person> getAll() {
        return personRepo.findAll(Sort.by("id"));
    }

    @Override
    public Optional<Person> getById(Long id) {
        return personRepo.getById(id);
    }

}
