package org.example.taxes.request.municipality.get;

import org.example.taxes.model.Municipality;
import org.example.taxes.service.MunicipalityService;
import org.example.taxes.utils.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityGetRequestExecutor implements Executable<MunicipalityGetRequest, List<Municipality>> {

    @Autowired
    private MunicipalityService service;

    @Override
    public List<Municipality> execute(MunicipalityGetRequest parameter) {
        return service.getMunicipalities(parameter);
    }
}
