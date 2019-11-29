package com.wmmzh.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.model.Vertrag;
import com.wmmzh.backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
            List<Vertrag> vertraege = mapVertraege(readVertraege());
            List<Person> persons = insertPersons(vertraege);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Vertrag> mapVertraege(List<String> vertraegeStr) throws JsonProcessingException {
        List<Vertrag> vertraege = new ArrayList<>();
        for (String vertragStr : vertraegeStr) {
            vertraege.add(objMapper.readValue(vertragStr, Vertrag.class));
        }
        return vertraege;
    }

    private List<Person> insertPersons(List<Vertrag> vertraege) throws JsonProcessingException {
        List<Person> persons = new ArrayList<>();
        int counter = 0;
        for (String personStr : readPersons()) {
            Vertrag vertrag = vertraege.get(counter++);

            Person person = objMapper.readValue(personStr, Person.class);
            person.getAdresse().setPerson(person);
            persons.add(person);
            person.getVertraege().add(vertrag);
            vertrag.setPerson(person);

            personRepo.save(person);
        }
        return persons;
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
                        "}",
                "{\n" +
                        "    \"email\": \"bruce.wayne@waynmanor.com\",\n" +
                        "    \"vorname\": \"Bruce\",\n" +
                        "    \"nachname\": \"Wayne\",\n" +
                        "    \"versichertennummer\": \"405.895.789\",\n" +
                        "    \"svnr\": \"756.123.456.789.8\",\n" +
                        "    \"geburtsdatum\": \"05.07.1985\",\n" +
                        "    \"adresse\": {\n" +
                        "        \"strasse\": \"Wayne Manor 1\",\n" +
                        "        \"plz\": \"8280\",\n" +
                        "        \"ort\": \"Kreuzlingen\",\n" +
                        "        \"land\": \"Schweiz\"\n" +
                        "    }\n" +
                        "}"
                );
    }

    private List<String> readVertraege() {
        return Arrays.asList(
                "{\n" +
                        "    \"policennummer\": \"4505.895.789.001\",\n" +
                        "    \"beginn\": \"01.01.2018\",\n" +
                        "    \"waehrung\": \"CHF\",\n" +
                        "    \"grundversicherung\": {\n" +
                        "        \"name\": \"Home Med Simplx Basis\",\n" +
                        "        \"beschreibung\": \"OKP Hausarztmodell\",\n" +
                        "        \"praemie\": \"231.70\",\n" +
                        "        \"franchise\": {\n" +
                        "            \"gesamt\": \"1500\",\n" +
                        "            \"bezahlt\": \"200\",\n" +
                        "            \"offen\": \"1300\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"zusatzversicherungen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Travel World\",\n" +
                        "            \"beschreibung\": \"Reiseversicherung\",\n" +
                        "            \"praemie\": \"12.60\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"name\": \"Denat Tutti\",\n" +
                        "            \"beschreibung\": \"Zahnzusatz\",\n" +
                        "            \"praemie\": \"35.90\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"spitalversicherungen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Travel World\",\n" +
                        "            \"beschreibung\": \"Reiseversicherung\",\n" +
                        "            \"praemie\": \"18.50\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}",
                "{\n" +
                        "    \"policennummer\": \"4505.895.789.001\",\n" +
                        "    \"beginn\": \"01.01.2019\",\n" +
                        "    \"geburtsdatum\": \"05.07.1985\",\n" +
                        "    \"waehrung\": \"CHF\",\n" +
                        "    \"praemiegesamt\": \"232.5\",\n" +
                        "    \"grundversicherung\": {\n" +
                        "        \"name\": \"Home Med Simplx Basis\",\n" +
                        "        \"beschreibung\": \"OKP Hausarztmodell\",\n" +
                        "        \"praemie\": \"190.20\",\n" +
                        "        \"franchise\": {\n" +
                        "            \"gesamt\": \"2500\",\n" +
                        "            \"bezahlt\": \"850\",\n" +
                        "            \"offen\": \"1650\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"zusatzversicherungen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Justice League\",\n" +
                        "            \"beschreibung\": \"Superhelden Unfall\",\n" +
                        "            \"praemie\": \"42.30\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}",
                "{\n" +
                        "    \"policennummer\": \"4505.895.789.001\",\n" +
                        "    \"beginn\": \"01.01.2019\",\n" +
                        "    \"geburtsdatum\": \"05.07.1985\",\n" +
                        "    \"waehrung\": \"CHF\",\n" +
                        "    \"praemiegesamt\": \"130.80\",\n" +
                        "    \"zusatzversicherungen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Justice League\",\n" +
                        "            \"beschreibung\": \"Superhelden Unfall\",\n" +
                        "            \"praemie\": \"42.30\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"name\": \"Protection Plus\",\n" +
                        "            \"beschreibung\": \"Rechtsschutz\",\n" +
                        "            \"praemie\": \"88.50\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"
        );
    }

}
