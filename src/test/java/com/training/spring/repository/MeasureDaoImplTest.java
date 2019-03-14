package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Measure;
import com.training.spring.model.RealCaptor;
import com.training.spring.model.Site;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class MeasureDaoImplTest {
    @Autowired
    private MeasureDao measureDao;
    @Autowired
    private EntityManager entityManager;
    private Measure measure;
    private Captor captor;
    @Test
    public void findById() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure)
                .get()
                .extracting("valueInWatt")
                .containsExactly(1000000);
    }
    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Measure> measure = measureDao.findById(-1000L);
        Assertions.assertThat(measure).isEmpty();
    }
    @Test
    public void findAll() {
        List<Measure> measures = measureDao.findAll();
        Assertions.assertThat(measures)
                .hasSize(10)
                .extracting("id")
                .contains(-10L, -9L, -8L, -7L, -6L, -5L, -4L, -3L, -2L, -1L);
    }
    @Test
    public void create() {
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
        Site site = new Site("New site");
        entityManager.persist(site);
        captor = new RealCaptor("New captor", site);
        entityManager.persist(captor);
        measure = new Measure(Instant.now(), 2000000, captor);
        measureDao.save(measure);
        Assertions.assertThat(measureDao.findAll())
                .hasSize(11)
                .extracting("id")
                .contains(-10L, -9L, -8L, -7L, -6L, -5L, -4L, -3L, -2L, -1L, 1L);
    }
    @Test
    public void update() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure).get().extracting("valueInWatt").containsExactly(1000000);
        measure.ifPresent(s -> {
            s.setValueInWatt(2000000);
            measureDao.save(s);
        });

        measure = measureDao.findById(-1L);
        Assertions.assertThat(measure).get().extracting("valueInWatt").containsExactly(2000000);
    }
    @Test
    public void deleteById() {
        Site site = new Site("New site");
        entityManager.persist(site);
        captor = new RealCaptor("New captor", site);
        entityManager.persist(captor);
        Measure newmeasure = new Measure(Instant.now(), 2000000, captor);
        measureDao.save(newmeasure);
        Assertions.assertThat(measureDao.findById(newmeasure.getId())).isNotEmpty();
        measureDao.delete(newmeasure);
        Assertions.assertThat(measureDao.findById(newmeasure.getId())).isEmpty();
    }
}