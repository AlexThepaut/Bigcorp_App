package com.training.spring.service;

import com.training.spring.model.Captor;
import com.training.spring.service.measure.MeasureService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("captorService")
public class CaptorServiceImpl implements CaptorService{

    private MeasureService fixedMeasureService;
    private MeasureService simulatedMeasureService;
    private MeasureService realMeasureService;

    public CaptorServiceImpl(MeasureService fixedMeasureService, MeasureService simulatedMeasureService, MeasureService realMeasureService) {
        this.fixedMeasureService = fixedMeasureService;
        this.simulatedMeasureService = simulatedMeasureService;
        this.realMeasureService = realMeasureService;
    }

    @Override
    public Set<Captor> findBySite(String siteId) {

        Set<Captor> captors = new HashSet<>();
        if (siteId == null) {
            return captors;
        }
        captors.add(new Captor("Capteur A"));
        return captors;
    }
}
