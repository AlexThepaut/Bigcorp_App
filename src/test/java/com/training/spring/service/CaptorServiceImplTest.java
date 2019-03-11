package com.training.spring.service;

import com.training.spring.model.Captor;
import com.training.spring.service.measure.FixedMeasureService;
import com.training.spring.service.measure.RealMeasureService;
import com.training.spring.service.measure.SimulatedMeasureService;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Set;


public class CaptorServiceImplTest {



    private CaptorServiceImpl captorService = new CaptorServiceImpl(new FixedMeasureService(), new SimulatedMeasureService(), new RealMeasureService());

    @Test
    public void findBySiteShouldReturnNullWhenIdIsNull() {
        // Initialisation
        String siteId = null;

        // Appel du SUT
        Set<Captor> captors = captorService.findBySite(siteId);

        // Vérification
        Assertions.assertThat(captors).isEmpty();
    }

    @Test
    public void findBySite() {
        // Initialisation
        String siteId = "siteId";

        // Appel du SUT
        Set<Captor> captors = captorService.findBySite(siteId);

        // Vérification
        Assertions.assertThat(captors).hasSize(1);
        Assertions.assertThat(captors).extracting(Captor::getName).contains("Capteur A");
    }
}