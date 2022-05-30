package org.example.taxes.request.taxes.add;

import org.example.taxes.model.Tax;
import org.example.taxes.service.TaxService;
import org.example.taxes.utils.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxAddRequestExecutor implements Executable<TaxAddRequest, Tax> {

    @Autowired
    private TaxService service;

    @Override
    public Tax execute(TaxAddRequest parameter) {
        return service.addTax(parameter);
    }
}
