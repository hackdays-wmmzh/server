package com.wmmzh.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DemoData {

    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private ObjectMapper objMapper;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        try {
            for (String personStr : readPersons()) {
                Person person = objMapper.readValue(personStr, Person.class);
                person.getAdresse().setPerson(person);
                personRepo.save(person);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readPersons() {
        return Arrays.asList(
                "{\n" +
                        "    \"email\": \"richard.garyson@waynmanor.com\",\n" +
                        "    \"vorname\": \"Richard\",\n" +
                        "    \"nachname\": \"Grayson\",\n" +
                        "    \"versichertennummer\": \"568.785.186\",\n" +
                        "    \"svnr\": \"756.596.777.892.3\",\n" +
                        "    \"geburtsdatum\": \"03.12.1990\",\n" +
                        "    \"adresse\": {\n" +
                        "        \"strasse\": \"Wayne Manor 2\",\n" +
                        "        \"plz\": \"8280\",\n" +
                        "        \"ort\": \"Kreuzlingen\",\n" +
                        "        \"land\": \"Schweiz\"\n" +
                        "    }\n" +
                        "}",
                "{\n" +
                        "    \"email\": \"selina.kyle@waynmanor.com\",\n" +
                        "    \"vorname\": \"Selina\",\n" +
                        "    \"nachname\": \"Kyle\",\n" +
                        "    \"versichertennummer\": \"785.349.712\",\n" +
                        "    \"svnr\": \"756.697.254.041.5\",\n" +
                        "    \"geburtsdatum\": \"20.08.1993\",\n" +
                        "    \"adresse\": {\n" +
                        "        \"strasse\": \"Wayne Manor 3\",\n" +
                        "        \"plz\": \"8280\",\n" +
                        "        \"ort\": \"Kreuzlingen\",\n" +
                        "        \"land\": \"Schweiz\"\n" +
                        "    }\n" +
                        "}");
    }

}
