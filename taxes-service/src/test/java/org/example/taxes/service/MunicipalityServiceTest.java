package org.example.taxes.service;

import org.example.taxes.exception.MissingParameterException;
import org.example.taxes.model.Municipality;
import org.example.taxes.repository.MunicipalityRepository;
import org.example.taxes.request.municipality.add.MunicipalityAddRequest;
import org.example.taxes.request.municipality.get.MunicipalityGetRequest;
import org.example.taxes.service.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = TestConfig.class)
class MunicipalityServiceTest {


    @SpyBean
    private MunicipalityRepository municipalityRepository;


    @Autowired
    private MunicipalityService service;

    @Test
    void workingTest() {
        assertTrue(true);
    }

    @Test
    void municipalityAddRequestCannotBeCreatedWithoutName() {
        assertThrows(MissingParameterException.class, () ->  {
            MunicipalityAddRequest.of(null);
        });

        assertThrows(MissingParameterException.class, () ->  {
            MunicipalityAddRequest.of("");
        });


        assertThrows(MissingParameterException.class, () ->  {
            MunicipalityAddRequest.of(" ");
        });
    }

    @Test
    void addMunicipalityTest() {

        reset(municipalityRepository);
        MunicipalityAddRequest request = MunicipalityAddRequest.of("test");
        service.addMunicipality(request);

        ArgumentCaptor<Municipality> argumentCaptor = ArgumentCaptor.forClass(Municipality.class);
        verify(municipalityRepository, times(1)).save(argumentCaptor.capture());
        assertEquals("test", argumentCaptor.getValue().getName());
    }

    @Test
    void getMunicipalityTest() {
        setup();
        // municipalityRepository.save(new Municipality().setName("test1"));
        MunicipalityGetRequest request = MunicipalityGetRequest.of(null);
        // doReturn(List.of(new Municipality().setName("test1"))).when(municipalityRepository).findAll();
        List<Municipality> result = service.getMunicipalities(request);
        assertEquals(2, result.size());
        tearDown();
    }


    @Test
    void getMunicipalityWithFilteringTest() {
        setup();
        // municipalityRepository.save(new Municipality().setName("test1"));
        MunicipalityGetRequest request = MunicipalityGetRequest.of("test1");
        // doReturn(List.of(new Municipality().setName("test1"))).when(municipalityRepository).findAll();
        List<Municipality> result = service.getMunicipalities(request);
        assertEquals(1, result.size());
        tearDown();
    }

    private void setup() {
        municipalityRepository.save(new Municipality().setName("test1"));
        municipalityRepository.save(new Municipality().setName("test2"));
    }

    private void tearDown() {
        municipalityRepository.deleteAll();
    }
}