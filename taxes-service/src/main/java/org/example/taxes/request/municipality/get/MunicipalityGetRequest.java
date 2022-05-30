package org.example.taxes.request.municipality.get;

public class MunicipalityGetRequest {

    private String name;


    private MunicipalityGetRequest(String name) {
        this.name = name;
    }

    public static MunicipalityGetRequest of(String name) {
        return new MunicipalityGetRequest(name);
    }

    public String getName() {
        return name;
    }
}
