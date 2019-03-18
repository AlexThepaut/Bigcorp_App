package com.training.spring.service.measure;

import com.training.spring.config.properties.BigCorpApplicationProperties;
import com.training.spring.model.Measure;
import com.training.spring.model.MeasureStep;
import com.training.spring.model.RealCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class RealMeasureService implements MeasureService<RealCaptor> {
    @Autowired
    private BigCorpApplicationProperties properties;
    @Override
    public List<Measure> readMeasures(RealCaptor captor, Instant start, Instant end,
                                      MeasureStep step) {
        checkReadMeasuresAgrs(captor, start, end, step);
        List<Measure> measures = new ArrayList<>();
        Instant current = start;
        while(current.isBefore(end)){
            measures.add(new Measure(current,
                    properties.getMeasure().getDefaultReal(), captor));
            current = current.plusSeconds(step.getDurationInSecondes());
        }
        return measures;
    }
}