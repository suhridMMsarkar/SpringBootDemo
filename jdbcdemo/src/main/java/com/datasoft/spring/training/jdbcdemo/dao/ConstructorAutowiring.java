package com.datasoft.spring.training.jdbcdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConstructorAutowiring {
    private JdbcTemplate jdbcTemplate;
    private CityDao cityDao;

    @Autowired
    public ConstructorAutowiring(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public CityDao getCityDao() {
        return this.cityDao;
    }
}
