package org.example.taxes.service;

import org.example.taxes.exception.MissingParameterException;
import org.example.taxes.exception.ResourceNotFoundException;
import org.example.taxes.model.Municipality;
import org.example.taxes.model.Tax;
import org.example.taxes.repository.MunicipalityRepository;
import org.example.taxes.repository.TaxesRepository;
import org.example.taxes.request.taxes.add.TaxAddRequest;
import org.example.taxes.request.taxes.get.TaxGetRequest;
import org.example.taxes.service.config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = TestConfig.class)
class TaxServiceTest {


    @SpyBean
    private TaxesRepository taxesRepository;
    @SpyBean
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private TaxService service;

    @BeforeEach
    private void setup() {

        Municipality template = new Municipality().setName("test1");

        Municipality municipality = municipalityRepository.findById(1L).orElseGet(() -> municipalityRepository.save(template));

        List<Tax> taxesList = new ArrayList<>();

        taxesList.add(new Tax()
                .setMunicipality(municipality)
                .setRate(0.2)
                .setDateFrom(LocalDate.of(2020, 1, 1))
                .setDateTo(LocalDate.of(2020, 12, 31)));

        taxesList.add(new Tax()
                .setMunicipality(municipality)
                .setRate(0.4)
                .setDateFrom(LocalDate.of(2020, 5, 1))
                .setDateTo(LocalDate.of(2020, 5, 31)));

        taxesList.add(new Tax()
                .setMunicipality(municipality)
                .setRate(0.1)
                .setDateFrom(LocalDate.of(2020, 1, 1))
                .setDateTo(LocalDate.of(2020, 1, 1)));

        taxesList.add(new Tax()
                .setMunicipality(municipality)
                .setRate(0.1)
                .setDateFrom(LocalDate.of(2020, 12, 25))
                .setDateTo(LocalDate.of(2020, 12, 25)));

        taxesRepository.saveAll(taxesList);
    }

    @Test
    void taxesGetRequestCannotBeCreatedWithoutParameters() {
        assertThrows(MissingParameterException.class, () -> {
            TaxGetRequest.of(null, null);
        });
    }

    @Test
    void taxesGetRequestCannotBeCreatedWithoutDate() {
        assertThrows(MissingParameterException.class, () -> {
            TaxGetRequest.of(1L, null);
        });
    }

    @Test
    void taxesGetRequestCannotBeCreatedWithoutMunicipalityId() {
        assertThrows(MissingParameterException.class, () ->  {
            TaxGetRequest.of(null, LocalDate.now());
        });
    }

    @Test
    void getTaxesExample1() {
        TaxGetRequest request = TaxGetRequest.of(1L, LocalDate.of(2020, 1, 1));
        Tax result = service.getTax(request);
        assertEquals(0.1, result.getRate());
    }

    @Test
    void getTaxesExample2() {
        TaxGetRequest request = TaxGetRequest.of(1L, LocalDate.of(2020, 5, 2));
        Tax result = service.getTax(request);
        assertEquals(0.4, result.getRate());
    }

    @Test
    void getTaxesExample3() {
        TaxGetRequest request = TaxGetRequest.of(1L, LocalDate.of(2020, 7, 10));
        Tax result = service.getTax(request);
        assertEquals(0.2, result.getRate());
    }

    @Test
    void getTaxesExample4() {
        TaxGetRequest request = TaxGetRequest.of(1L, LocalDate.of(2020, 3, 16));
        Tax result = service.getTax(request);
        assertEquals(0.2, result.getRate());
    }

    @Test
    void taxesAddRequestCannotBeCreatedWithoutParameters() {
        assertThrows(MissingParameterException.class, () -> {
            TaxAddRequest.of(null, null, null, null);
        });
    }

    @Test
    void taxesAddRequestCannotBeCreatedWithoutRate() {
        assertThrows(MissingParameterException.class, () -> {
            TaxAddRequest.of(1L, null, LocalDate.now(), LocalDate.now());
        });
    }

    @Test
    void taxesAddRequestCannotBeCreatedWithoutMunicipalityId() {
        assertThrows(MissingParameterException.class, () ->  {
            TaxAddRequest.of(null, 1.3, LocalDate.now(), LocalDate.now());
        });
    }

    @Test
    void taxesAddRequestCannotBeCreatedWithoutDates() {
        assertThrows(MissingParameterException.class, () ->  {
            TaxAddRequest.of(1L, 1.3, LocalDate.now(), null);
        });

        assertThrows(MissingParameterException.class, () ->  {
            TaxAddRequest.of(1L, 1.3, null, LocalDate.now());
        });
    }

    @Test
    void taxesAddRequestCannotBeCreatedWithDateToEarlierThanDateFrom() {
        assertThrows(IllegalArgumentException.class, () ->  {
            TaxAddRequest.of(1L, 1.3, LocalDate.now().plus(1 , ChronoUnit.DAYS), LocalDate.now());
        });
    }

    @Test
    void taxesAddRequestCannotBeCreatedWithSameDates() {
        LocalDate date = LocalDate.now();
        Tax newTax = service.addTax(TaxAddRequest.of(1L, 1.3, date, date));

        assertEquals(date, newTax.getDateFrom());
        assertEquals(date, newTax.getDateTo());
    }

    @Test
    void taxCannotBeAddedToNonExistingMunicipality() {
        assertThrows(ResourceNotFoundException.class, () ->  {
            service.addTax(TaxAddRequest.of(100L, 1.3, LocalDate.now(), LocalDate.now()));
        });
    }

    @AfterEach
    private void tearDown() {
        taxesRepository.deleteAll();
    }

}