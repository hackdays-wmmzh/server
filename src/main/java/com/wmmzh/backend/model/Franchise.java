package com.wmmzh.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal gesamt = BigDecimal.ZERO;
    private BigDecimal bezahlt = BigDecimal.ZERO;

    public Franchise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGesamt() {
        return gesamt;
    }

    public void setGesamt(BigDecimal gesamt) {
        this.gesamt = gesamt;
    }

    public BigDecimal getBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(BigDecimal bezahlt) {
        this.bezahlt = bezahlt;
    }

    @JsonProperty("offen")
    public BigDecimal getOffen() {
        return gesamt.subtract(bezahlt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Franchise franchise = (Franchise) o;
        return Objects.equals(id, franchise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}