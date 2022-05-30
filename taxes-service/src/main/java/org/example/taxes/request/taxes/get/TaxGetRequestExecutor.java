package org.example.taxes.request.taxes.get;

import org.example.taxes.model.Tax;
import org.example.taxes.service.TaxService;
import org.example.taxes.utils.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxGetRequestExecutor implements Executable<TaxGetRequest, Tax> {

    @Autowired
    private TaxService service;

    @Override
    public Tax execute(TaxGetRequest parameter) {
        return service.getTax(parameter);
    }
}
