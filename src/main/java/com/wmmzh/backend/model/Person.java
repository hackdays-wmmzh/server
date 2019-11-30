package com.wmmzh.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String vorname;
    private String nachname;
    private String versichertennummer;
    private String svnr;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate geburtsdatum;

    @OneToOne(cascade = CascadeType.ALL)
    private Image avatar;

    @OneToOne(cascade = CascadeType.ALL)
    private Adresse adresse;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Vertrag> vertraege = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.EAGER)
    private List<Ereignis> ereignisse = new ArrayList<>();

    public Person() {
    }

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

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Vertrag> getVertraege() {
        return vertraege;
    }

    public void setVertraege(List<Vertrag> vertraege) {
        this.vertraege = vertraege;
    }

    public List<Ereignis> getEreignisse() {
        return ereignisse;
    }

    public void setEreignisse(List<Ereignis> ereignisse) {
        this.ereignisse = ereignisse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
