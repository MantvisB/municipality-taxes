package org.example.taxes.controller.municipality;

import org.example.taxes.model.Municipality;
import org.example.taxes.request.municipality.add.MunicipalityAddRequest;
import org.example.taxes.request.municipality.add.MunicipalityAddRequestExecutor;
import org.example.taxes.request.municipality.get.MunicipalityGetRequest;
import org.example.taxes.request.municipality.get.MunicipalityGetRequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipality")
public class MunicipalityController {

    @Autowired
    private MunicipalityAddRequestExecutor municipalityAddRequestExecutor;

    @Autowired
    private MunicipalityGetRequestExecutor municipalityGetRequestExecutor;

    @GetMapping
    public List<Municipality> getMunicipalities(@PathVariable(value = "name", required = false) String name) {
        return municipalityGetRequestExecutor.execute(MunicipalityGetRequest.of(name));
    }

    @PostMapping
    public Municipality addMunicipality(@RequestBody String name) {
        return municipalityAddRequestExecutor.execute(MunicipalityAddRequest.of(name));
    }
}
