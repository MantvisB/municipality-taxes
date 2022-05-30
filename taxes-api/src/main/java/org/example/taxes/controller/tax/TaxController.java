package org.example.taxes.controller.tax;

import org.example.taxes.model.Tax;
import org.example.taxes.request.taxes.add.TaxAddRequest;
import org.example.taxes.request.taxes.add.TaxAddRequestExecutor;
import org.example.taxes.request.taxes.get.TaxGetRequest;
import org.example.taxes.request.taxes.get.TaxGetRequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    private TaxAddRequestExecutor taxAddRequestExecutor;

    @Autowired
    private TaxGetRequestExecutor taxGetRequestExecutor;

    @GetMapping
    public Tax getTaxByMunicipalityIdAndDate(@RequestParam(value = "municipality_id") Long municipalityId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taxGetRequestExecutor.execute(TaxGetRequest.of(municipalityId, date));
    }

    @PostMapping
    public Tax addTax(@RequestBody TaxCreationView view) {
        return taxAddRequestExecutor.execute(TaxAddRequest.of(view.getMunicipalityId(), view.getRate(), view.getDateFrom(), view.getDateTo()));
    }
}
