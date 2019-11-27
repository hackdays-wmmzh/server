package com.wmmzh.backend.service.impl;

import com.wmmzh.backend.model.Adresse;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Optional<Person> getByEmail(String email) {
        Person person = new Person();
        person.setEmail(email);
        person.setVorname("Hans");
        person.setNachname("Heeb");
        person.setVersichertennummer("123.456.789");
        person.setSvnr("756.000.000.000.1");

        Adresse adresse = new Adresse();
        adresse.setStrasse("Kaiserweg 97");
        adresse.setPlz("8280");
        adresse.setOrt("Kreuzlingen");
        adresse.setLand("Schweiz");
        person.setAdresse(adresse);

        return Optional.of(person);
    }

}
