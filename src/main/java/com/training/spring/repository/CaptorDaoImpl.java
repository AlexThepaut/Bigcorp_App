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
public class CaptorDaoImpl implements CaptorDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private static String SELECT_WITH_JOIN = "SELECT c.id, c.name, c.site_id, s.name as site_name " +
                    "FROM Captor c inner join Site s on c.site_id = s.id ";

    public CaptorDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Captor> findBySiteId(String siteId) {
        return null;
    }

    @Override
    public void create(Captor element) {
        jdbcTemplate.update("insert into CAPTOR(id, name, site_id) values (:id, :name, :siteId)",
                new MapSqlParameterSource()
                        .addValue("id", element.getId())
                        .addValue("name", element.getName())
                        .addValue("siteId", element.getSite().getId()));
    }

    @Override
    public Captor findById(String s) {
        try{
            return jdbcTemplate.queryForObject(SELECT_WITH_JOIN + "where c.id =:id",
                    new MapSqlParameterSource().addValue("id", s), this::captorMapper);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Captor> findAll() {
        return jdbcTemplate.query(SELECT_WITH_JOIN, this::captorMapper);
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
        jdbcTemplate.update("delete from CAPTOR c where c.id =:id",
                new MapSqlParameterSource().addValue("id", s));
    }

    private Captor captorMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("site_name"));
        site.setId(rs.getString("site_id"));
        Captor captor = new Captor(rs.getString("name"), site);
        captor.setId(rs.getString("id"));
        return captor;
    }
}
