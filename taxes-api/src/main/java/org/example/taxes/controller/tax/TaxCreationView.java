package org.example.taxes.controller.tax;

import java.time.LocalDate;

public class TaxCreationView {

    private Long municipalityId;
    private Double rate;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public TaxCreationView setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
        return this;
    }

    public Double getRate() {
        return rate;
    }

    public TaxCreationView setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public TaxCreationView setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public TaxCreationView setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
