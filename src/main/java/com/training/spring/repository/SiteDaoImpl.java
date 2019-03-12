package com.training.spring.repository;

import com.training.spring.model.Site;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteDaoImpl implements SiteDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public SiteDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Site element) {
        jdbcTemplate.update("insert into SITE values (:id, :name)",
            new MapSqlParameterSource()
                .addValue("id", element.getId())
                .addValue("name", element.getName()));
    }

    @Override
    public Site findById(String s) {
        return jdbcTemplate.queryForObject("select * from SITE where id =:id",
                new MapSqlParameterSource().addValue("id", s), Site.class);
    }

    @Override
    public List<Site> findAll() {
        return null;
    }

    @Override
    public void update(Site site) {
        jdbcTemplate.update("update SITE set name = :name where id =:id",
                new MapSqlParameterSource()
                        .addValue("id", site.getId())
                        .addValue("name", site.getName()));
    }

    @Override
    public void deleteById(String s) {
        jdbcTemplate.update("delete from SITE where id =:id",
                new MapSqlParameterSource().addValue("id", s));
    }
}
