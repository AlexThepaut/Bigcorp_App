package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Measure;
import com.training.spring.model.Site;
import com.training.spring.utils.H2DateConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class MeasureDaoImpl implements MeasureDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Measure element) {
        em.persist(element);
    }

    @Override
    public Measure findById(Long aLong) {
        return em.find(Measure.class, aLong);
    }

    @Override
    public List<Measure> findAll() {
        return em.createQuery("select m from Measure m", Measure.class)
                .getResultList();
    }

    @Override
    public void delete(Measure id) {
        em.remove(id);
    }
}
