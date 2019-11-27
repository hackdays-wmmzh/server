package com.wmmzh.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Person {

    private String email;
    private String vorname;
    private String nachname;
    private String versichertennummer;
    private String svnr;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate geburtsdatum;
    private Adresse adresse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVersichertennummer() {
        return versichertennummer;
    }

    public void setVersichertennummer(String versichertennummer) {
        this.versichertennummer = versichertennummer;
    }

    public String getSvnr() {
        return svnr;
    }

    public void setSvnr(String svnr) {
        this.svnr = svnr;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

}
