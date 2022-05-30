package org.example.taxes.search;

import java.time.LocalDate;

public class TaxesSearch {

    private Long municipalityId;

    private LocalDate date;

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public TaxesSearch setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public TaxesSearch setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
