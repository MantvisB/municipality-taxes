package org.example.taxes.request.taxes.add;

import org.example.taxes.exception.MissingParameterException;

import java.time.LocalDate;

public class TaxAddRequest {

    private final Long municipalityId;

    private final Double rate;

    private final LocalDate dateFrom;

    private final LocalDate dateTo;


    private TaxAddRequest(Long municipalityId, Double rate, LocalDate dateFrom, LocalDate dateTo) {
        this.municipalityId = municipalityId;
        this.rate = rate;

        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public static TaxAddRequest of(Long municipalityId, Double rate, LocalDate dateFrom, LocalDate dateTo) {

        if(municipalityId == null)
            throw new MissingParameterException("municipalityId not provided.");

        if(rate == null)
            throw new MissingParameterException("rate not provided.");

        if(dateFrom == null) {
            throw new MissingParameterException("dateFrom not provided.");
        }

        if(dateTo == null) {
            throw new MissingParameterException("dateTo not provided.");
        }

        if(dateTo.isBefore(dateFrom)) {
            throw new IllegalArgumentException("dateFrom is after dateTo.");
        }

        return new TaxAddRequest(municipalityId, rate, dateFrom, dateTo);
    }


    public Long getMunicipalityId() {
        return municipalityId;
    }

    public Double getRate() {
        return rate;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
