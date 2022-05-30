package org.example.taxes.service;


import org.example.taxes.exception.ResourceNotFoundException;
import org.example.taxes.model.Municipality;
import org.example.taxes.model.Tax;
import org.example.taxes.repository.TaxesRepository;
import org.example.taxes.request.taxes.add.TaxAddRequest;
import org.example.taxes.request.taxes.get.TaxGetRequest;
import org.example.taxes.search.TaxesSearch;
import org.example.taxes.specification.TaxesSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

@Service
public class TaxService {
    @Autowired
    private TaxesRepository taxesRepository;

    @Autowired
    private MunicipalityService municipalityService;

    public Tax addTax(TaxAddRequest request) {
        Municipality municipality = municipalityService.getMunicipalityById(request.getMunicipalityId());
        Tax taxes = new Tax().setMunicipality(municipality)
                .setRate(request.getRate())
                .setDateFrom(request.getDateFrom())
                .setDateTo(request.getDateTo());
        return taxesRepository.save(taxes);
    }


    public Tax getTax(TaxGetRequest request) {
        return filterTaxes(taxesRepository.findAll(TaxesSpecification
                .build(new TaxesSearch()
                        .setMunicipalityId(request.getMunicipalityId())
                        .setDate(request.getDate()))));
    }

    private Tax filterTaxes(List<Tax> taxesList) {
        if(taxesList.size() == 1)
            return taxesList.get(0);
        return taxesList.stream().min(comparator).stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("Taxes cannot be found"));
    }

    Comparator<Tax> comparator = new Comparator<Tax>() {
        @Override
        public int compare(final Tax o1, final Tax o2) {
            SimpleDateFormat sdf
                    = new SimpleDateFormat(
                    "yyyy-MM-dd");
            try {
                long d1 = sdf.parse(o1.getDateTo().toString()).getTime() - sdf.parse(o1.getDateFrom().toString()).getTime();
                long d2 = sdf.parse(o2.getDateTo().toString()).getTime() - sdf.parse(o2.getDateFrom().toString()).getTime();
                return Integer.compare((int)d1, (int)d2);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    };
}
