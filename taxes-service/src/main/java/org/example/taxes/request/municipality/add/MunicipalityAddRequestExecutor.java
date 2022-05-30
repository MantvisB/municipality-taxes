package org.example.taxes.request.municipality.add;

import org.example.taxes.model.Municipality;
import org.example.taxes.service.MunicipalityService;
import org.example.taxes.utils.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipalityAddRequestExecutor implements Executable<MunicipalityAddRequest, Municipality> {

    @Autowired
    private MunicipalityService service;

    @Override
    public Municipality execute(MunicipalityAddRequest parameter) {
        return service.addMunicipality(parameter);
    }
}
