package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Captor;
import com.training.spring.model.Site;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaptorDaoImpl implements CaptorDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public CaptorDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Captor> findBySiteId(String siteId) {
        return null;
    }

    @Override
    public void create(Captor element) {
        jdbcTemplate.update("insert into CAPTOR values (:id, :name)",
                new MapSqlParameterSource()
                        .addValue("id", element.getId())
                        .addValue("name", element.getName()));
    }

    @Override
    public Captor findById(String s) {
        return jdbcTemplate.queryForObject("select * from CAPTOR where id =:id",
                new MapSqlParameterSource().addValue("id", s), Captor.class);
    }

    @Override
    public List<Captor> findAll() {
        return null;
    }

    @Override
    public void update(Captor element) {
        jdbcTemplate.update("update CAPTOR set name = :name where id =:id",
                new MapSqlParameterSource()
                        .addValue("id", element.getId())
                        .addValue("name", element.getName()));
    }

    @Override
    public void deleteById(String s) {
        jdbcTemplate.update("delete from CAPTOR where id =:id",
                new MapSqlParameterSource().addValue("id", s));
    }
}
