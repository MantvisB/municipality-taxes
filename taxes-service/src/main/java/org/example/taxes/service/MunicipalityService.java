package org.example.taxes.service;

import org.example.taxes.exception.ResourceNotFoundException;
import org.example.taxes.model.Municipality;
import org.example.taxes.repository.MunicipalityRepository;
import org.example.taxes.request.municipality.add.MunicipalityAddRequest;
import org.example.taxes.request.municipality.get.MunicipalityGetRequest;
import org.example.taxes.search.MunicipalitySearch;
import org.example.taxes.specification.MunicipalitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipalityService {

    @Autowired
    private MunicipalityRepository municipalityRepository;

    public Municipality addMunicipality(MunicipalityAddRequest request) {
        Municipality municipality = new Municipality().setName(request.getName());

        return municipalityRepository.save(municipality);
    }


    public List<Municipality> getMunicipalities(MunicipalityGetRequest request) {
        return municipalityRepository.findAll(MunicipalitySpecification.build(new MunicipalitySearch().setName(request.getName())));
    }

    public Municipality getMunicipalityById(Long municipalityId) {
        return municipalityRepository.findById(municipalityId).orElseThrow(() -> new ResourceNotFoundException("Municipality not found. id=" + municipalityId));
    }
}
