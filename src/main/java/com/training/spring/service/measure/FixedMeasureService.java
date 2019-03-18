package com.training.spring.service.measure;

import com.training.spring.config.properties.BigCorpApplicationProperties;
import com.training.spring.model.FixedCaptor;
import com.training.spring.model.Measure;
import com.training.spring.model.MeasureStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class FixedMeasureService implements MeasureService<FixedCaptor> {
    @Autowired
    private BigCorpApplicationProperties properties;
    @Override
    public List<Measure> readMeasures(FixedCaptor captor, Instant start, Instant end,
                                      MeasureStep step) {
        checkReadMeasuresAgrs(captor, start, end, step);
        List<Measure> measures = new ArrayList<>();
        Instant current = start;
        while(current.isBefore(end)){
            measures.add(new Measure(current,
                    properties.getMeasure().getDefaultFixed(), captor));
            current = current.plusSeconds(step.getDurationInSecondes());
        }
        return measures;
    }
}
