package com.wmmzh.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class Vertrag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policennummer;

    @JsonIgnore
    @OneToOne
    private Person person;

    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate beginn;

    private String waehrung;

    @OneToOne(cascade = CascadeType.ALL)
    private Grundversicherung grundversicherung;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produkt> zusatzversicherungen = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Produkt> spitalversicherungen = new ArrayList<>();;

    public Vertrag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicennummer() {
        return policennummer;
    }

    public void setPolicennummer(String policennummer) {
        this.policennummer = policennummer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getBeginn() {
        return beginn;
    }

    public void setBeginn(LocalDate beginn) {
        this.beginn = beginn;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    @JsonProperty("praemieGesamt")
    public BigDecimal getPraemieGesamt() {
        List<BigDecimal> praemien = new ArrayList<>();
        if (Objects.nonNull(grundversicherung)) {
            praemien.add(grundversicherung.getPraemie());
        }
        praemien.add(zusatzversicherungen.stream()
                .map(Produkt::getPraemie)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        praemien.add(spitalversicherungen.stream()
                .map(Produkt::getPraemie)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return praemien.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Grundversicherung getGrundversicherung() {
        return grundversicherung;
    }

    public void setGrundversicherung(Grundversicherung grundversicherung) {
        this.grundversicherung = grundversicherung;
    }

    public List<Produkt> getZusatzversicherungen() {
        return zusatzversicherungen;
    }

    public void setZusatzversicherungen(List<Produkt> zusatzversicherungen) {
        this.zusatzversicherungen = zusatzversicherungen;
    }

    public List<Produkt> getSpitalversicherungen() {
        return spitalversicherungen;
    }

    public void setSpitalversicherungen(List<Produkt> spitalversicherungen) {
        this.spitalversicherungen = spitalversicherungen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertrag vertrag = (Vertrag) o;
        return Objects.equals(id, vertrag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
