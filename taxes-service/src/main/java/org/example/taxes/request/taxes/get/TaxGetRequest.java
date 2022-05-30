package org.example.taxes.request.taxes.get;

import org.example.taxes.exception.MissingParameterException;

import java.time.LocalDate;

public class TaxGetRequest {

    private final Long municipalityId;

    private final LocalDate date;

    private TaxGetRequest(Long municipalityId, LocalDate date) {

        this.municipalityId = municipalityId;
        this.date = date;
    }

    public static TaxGetRequest of(Long municipalityId, LocalDate date) {

        if(municipalityId == null)
            throw new MissingParameterException("municipalityId not provided.");

        if(date == null)
            throw new MissingParameterException("date not provided.");

        return new TaxGetRequest(municipalityId, date);
    }

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public LocalDate getDate() {
        return date;
    }
}
