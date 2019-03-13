package com.training.spring.repository;

import com.training.spring.model.Captor;
import com.training.spring.model.Site;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class SiteDaoImpl implements SiteDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static String SELECT = "SELECT s.id, s.name FROM Site s ";

    public SiteDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Site element) {
        jdbcTemplate.update("insert into SITE(id, name) values (:id, :name)",
                new MapSqlParameterSource()
                        .addValue("id", element.getId())
                        .addValue("name", element.getName()));
    }

    @Override
    public Site findById(String s) {
        try{
            return jdbcTemplate.queryForObject(SELECT + " where s.id =:id",
                    new MapSqlParameterSource("id", s), this::siteMapper);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Site> findAll() {
        return jdbcTemplate.query(SELECT, this::siteMapper);
    }

    @Override
    public void update(Site site) {
        jdbcTemplate.update("update SITE s set s.name = :name where s.id =:id",
                new MapSqlParameterSource()
                        .addValue("id", site.getId())
                        .addValue("name", site.getName()));
    }

    @Override
    public void deleteById(String s) {
        jdbcTemplate.update("delete from SITE s where s.id =:id",
                new MapSqlParameterSource().addValue("id", s));
    }

    private Site siteMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("name"));
        site.setId(rs.getString("id"));
        return site;
    }
}
