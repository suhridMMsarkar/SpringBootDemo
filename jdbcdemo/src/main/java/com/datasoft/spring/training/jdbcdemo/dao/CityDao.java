package com.datasoft.spring.training.jdbcdemo.dao;


import com.datasoft.spring.training.jdbcdemo.model.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public City add(City city) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("city")
                .usingGeneratedKeyColumns("ID");
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("Name", city.getName());
        parameterMap.put("CountryCode", city.getCountryCode());
        parameterMap.put("District", city.getDistrict());
        parameterMap.put("Population", city.getPopulation());
        try {
            Number generatedId = jdbcInsert.executeAndReturnKey(parameterMap);
            if (generatedId != null) {
                city.setId(generatedId.intValue());
            }
        } catch (DataAccessException dae) {
            log.error(dae.getLocalizedMessage());
        }
        return city;
    }

    public List<City> findAll() {
        try {
            return jdbcTemplate.query("select * from city", new CityRowMapper());
        } catch (DataAccessException dae) {
            log.error(dae.getLocalizedMessage());
            return null;
        }
    }

    public City findById(int i) {
        try {
            return jdbcTemplate.queryForObject("select * from city where ID = ?", new Object[]{i}, new CityRowMapper());
        } catch (DataAccessException dae) {
            log.error(dae.getLocalizedMessage());
            return null;
        }
    }

    public int delete(int id) {
        try {
            return jdbcTemplate.update("delete from city where ID = ?", id);
        } catch (DataAccessException dae) {
            log.error(dae.getLocalizedMessage());
            return 0;
        }
    }

    class CityRowMapper implements RowMapper<City> {

        @Override
        public City mapRow(ResultSet resultSet, int i) throws SQLException {
            City city = new City();
            city.setId(resultSet.getInt("ID"));
            city.setName(resultSet.getString("Name"));
            city.setCountryCode(resultSet.getString("CountryCode"));
            city.setDistrict(resultSet.getString("District"));
            city.setPopulation(resultSet.getInt("Population"));
            return city;
        }
    }
}
