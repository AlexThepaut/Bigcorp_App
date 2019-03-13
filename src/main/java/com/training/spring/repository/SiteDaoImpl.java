package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Site;
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
public class SiteDaoImpl implements SiteDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Site element) {
        em.persist(element);
    }

    @Override
    public Site findById(String s) {
        return em.find(Site.class, s);
    }

    @Override
    public List<Site> findAll() {
        return em.createQuery("select s from Site s", Site.class)
                .getResultList();
    }

    @Override
    public void delete(Site id) {
        em.remove(id);
    }

}
