package com.wmmzh.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String beschreibung;
    private BigDecimal praemie;

    public Produkt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public BigDecimal getPraemie() {
        return praemie;
    }

    public void setPraemie(BigDecimal praemie) {
        this.praemie = praemie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produkt produkt = (Produkt) o;
        return Objects.equals(id, produkt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
