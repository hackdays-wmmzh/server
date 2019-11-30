package com.wmmzh.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmmzh.backend.model.Ereignis;
import com.wmmzh.backend.model.Person;
import com.wmmzh.backend.model.Vertrag;
import com.wmmzh.backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
            List<Person> persons = insertPersons(vertraege, getEreignisse());
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

    private List<Person> insertPersons(List<Vertrag> vertraege, List<Ereignis> ereignisse) throws JsonProcessingException {
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

    private List<Ereignis> getEreignisse() {
        return Arrays.asList(
          createEreignis(LocalDateTime.of(2019, 05, 02, 14, 35), Ereignis.Type.POLICE_ERHALTEN, "Police erhalten")
        );
    }

    private Ereignis createEreignis(LocalDateTime zeitstempel, Ereignis.Type type, String text) {
        Ereignis ereignis = new Ereignis();
        ereignis.setZeitstempel(zeitstempel);
        ereignis.setType(type);
        ereignis.setText(text);
        return ereignis;
    }

    private List<String> readPersons() {
        return Arrays.asList(
                "{\n" +
                        "    \"email\": \"emanuele.diquattro@adcubum.com\",\n" +
                        "    \"vorname\": \"Emanuele\",\n" +
                        "    \"nachname\": \"Diquattro \",\n" +
                        "    \"versichertennummer\": \"405.895.789\",\n" +
                        "    \"svnr\": \"756.123.456.789.8\",\n" +
                        "    \"geburtsdatum\": \"05.07.1983\",\n" +
                        "    \"adresse\": {\n" +
                        "        \"strasse\": \"Wayne Manor 1\",\n" +
                        "        \"plz\": \"8180\",\n" +
                        "        \"ort\": \"Bülach\",\n" +
                        "        \"land\": \"Schweiz\"\n" +
                        "    }\n" +
                        "}",
                "{\n" +
                        "    \"email\": \"claudia.grolimund@adcubum.com\",\n" +
                        "    \"vorname\": \"Claudia\",\n" +
                        "    \"nachname\": \"Grolimund\",\n" +
                        "    \"versichertennummer\": \"568.785.186\",\n" +
                        "    \"svnr\": \"756.596.777.892.3\",\n" +
                        "    \"geburtsdatum\": \"03.12.1978\",\n" +
                        "    \"adresse\": {\n" +
                        "        \"strasse\": \"Zürcherstrasse 464\",\n" +
                        "        \"plz\": \"9015\",\n" +
                        "        \"ort\": \"St. Gallen\",\n" +
                        "        \"land\": \"Schweiz\"\n" +
                        "    }\n" +
                        "}",
                "{\n" +
                        "    \"email\": \"walter.meister@adcubum.com\",\n" +
                        "    \"vorname\": \"Walter\",\n" +
                        "    \"nachname\": \"Meister\",\n" +
                        "    \"versichertennummer\": \"785.349.712\",\n" +
                        "    \"svnr\": \"756.697.254.041.5\",\n" +
                        "    \"geburtsdatum\": \"20.08.1969\",\n" +
                        "    \"adresse\": {\n" +
                        "        \"strasse\": \"Bahnhofplatz 1A\",\n" +
                        "        \"plz\": \"8304\",\n" +
                        "        \"ort\": \"Wallisellen\",\n" +
                        "        \"land\": \"Schweiz\"\n" +
                        "    }\n" +
                        "}"
                );
    }

    private List<String> readVertraege() {
        return Arrays.asList(
                "{\n" +
                        "    \"policennummer\": \"4505.895.789.001\",\n" +
                        "    \"versicherteperson\": \"405.895.789\",\n" +
                        "    \"beginn\": \"01.01.2018\",\n" +
                        "    \"waehrung\": \"CHF\",\n" +
                        "    \"praemiegesamt\": \"298.70\",\n" +
                        "    \"grundversicherung\": {\n" +
                        "        \"name\": \"Home Med Simplx Basis\",\n" +
                        "        \"beschreibung\": \"OKP Hausarztmodell\",\n" +
                        "        \"praemie\": \"298.70\",\n" +
                        "        \"franchise\": {\n" +
                        "            \"gesamt\": \"1500\",\n" +
                        "            \"bezahlt\": \"200\",\n" +
                        "            \"offen\": \"1300\"\n" +
                        "        },\n" +
                        "        \"selbstbehalt\": {\n" +
                        "            \"bezahlt\": \"210\",\n" +
                        "            \"max\": \"700\"\n" +
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
                        "    \"spitalversicheurngen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Travel World\",\n" +
                        "            \"beschreibung\": \"Reiseversicherung\",\n" +
                        "            \"praemie\": \"18.50\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}\n",
                "{\n" +
                        "    \"policennummer\": \"4505.895.789.001\",\n" +
                        "    \"versicherteperson\": \"568.785.186\",\n" +
                        "    \"beginn\": \"01.01.2019\",\n" +
                        "    \"geburtsdatum\": \"05.07.1985\",\n" +
                        "    \"waehrung\": \"CHF\",\n" +
                        "    \"praemiegesamt\": \"222.10\",\n" +
                        "    \"grundversicherung\": {\n" +
                        "        \"name\": \"Simplx Telmed Eco\",\n" +
                        "        \"beschreibung\": \"OKP Telemedizin\",\n" +
                        "        \"praemie\": \"190.20\",\n" +
                        "        \"franchise\": {\n" +
                        "            \"gesamt\": \"2500\",\n" +
                        "            \"bezahlt\": \"850\",\n" +
                        "            \"offen\": \"1650\"\n" +
                        "        },\n" +
                        "        \"selbstbehalt\": {\n" +
                        "            \"bezahlt\": \"512\",\n" +
                        "            \"max\": \"700\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"zusatzversicherungen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Animal Protect\",\n" +
                        "            \"beschreibung\": \"Hunde Rechtsschutzversicherung\",\n" +
                        "            \"praemie\": \"31.90\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}\n",
                "{\n" +
                        "    \"policennummer\": \"4505.895.789.001\",\n" +
                        "    \"versicherteperson\": \"568.785.186\",\n" +
                        "    \"beginn\": \"01.01.2019\",\n" +
                        "    \"geburtsdatum\": \"05.07.1965\",\n" +
                        "    \"waehrung\": \"CHF\",\n" +
                        "    \"praemiegesamt\": \"551.40\",\n" +
                        "    \"grundversicherung\": {\n" +
                        "        \"name\": \"Simplx Deluxe All\",\n" +
                        "        \"beschreibung\": \"OKP Grundversichung\",\n" +
                        "        \"praemie\": \"420.60\",\n" +
                        "        \"franchise\": {\n" +
                        "            \"gesamt\": \"500\",\n" +
                        "            \"bezahlt\": \"380\",\n" +
                        "            \"offen\": \"120\"\n" +
                        "        },\n" +
                        "        \"selbstbehalt\": {\n" +
                        "            \"bezahlt\": \"380\",\n" +
                        "            \"max\": \"700\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"zusatzversicherungen\": [\n" +
                        "        {\n" +
                        "            \"name\": \"Avengers Accident\",\n" +
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
