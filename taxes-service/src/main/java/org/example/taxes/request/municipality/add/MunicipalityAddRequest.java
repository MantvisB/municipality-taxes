package org.example.taxes.request.municipality.add;

import org.example.taxes.exception.MissingParameterException;

public class MunicipalityAddRequest {

    private final String name;

    private MunicipalityAddRequest(String name) {
        this.name = name;
    }

    public static MunicipalityAddRequest of(String name) {
        if(name == null || name.isBlank()) {
            throw new MissingParameterException("Municipality name not provided.");
        }
        return new MunicipalityAddRequest(name);
    }

    public String getName() {
        return name;
    }
}
