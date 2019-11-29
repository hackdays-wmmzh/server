package com.wmmzh.backend.repository;

import com.wmmzh.backend.model.Image;
import com.wmmzh.backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> getAllByPerson(Long person);

}
