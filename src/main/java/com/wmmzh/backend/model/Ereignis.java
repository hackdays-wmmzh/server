package com.wmmzh.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ereignis {

    public enum Type { RECHNUNG, LEISTUNGSABRECHNUNG, POLICE_ERHALTEN, PERSONEN_DATEN_GEAENDERT }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    private Person person;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime zeitstempel;

    private Type type;

    @Lob
    private String text;

    @OneToOne
    private Image image;

    public Ereignis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(LocalDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ereignis ereignis = (Ereignis) o;
        return Objects.equals(id, ereignis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
