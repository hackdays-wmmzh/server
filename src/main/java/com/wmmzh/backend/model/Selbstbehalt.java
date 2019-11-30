package com.wmmzh.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Selbstbehalt {

    public Selbstbehalt() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal bezahlt = BigDecimal.ZERO;
    private BigDecimal max = BigDecimal.ZERO;

    public BigDecimal getBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(BigDecimal bezahlt) {
        this.bezahlt = bezahlt;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selbstbehalt that = (Selbstbehalt) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
