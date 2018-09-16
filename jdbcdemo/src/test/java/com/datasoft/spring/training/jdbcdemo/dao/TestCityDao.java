package com.datasoft.spring.training.jdbcdemo.dao;

import com.datasoft.spring.training.jdbcdemo.model.City;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestCityDao {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ConstructorAutowiring constructorAutowiring;

    @Test
    @Ignore
    public void add() {
        City city = new City(null, "Jinjira", "BGD", "Dhaka", 40000000);
        City savedCity = cityDao.add(city);
        log.info(savedCity.toString());
    }

    @Test
    public void delete() {
        int count = cityDao.delete(4081);
        log.info("{} number of rows was deleted", count);
    }

    @Test
    public void findAll() {
        List<City> cities = cityDao.findAll();
        cities.stream().forEach(city -> log.info(city.toString()));
    }

    @Test
    public void findById() {
        City city = cityDao.findById(81);
        if (city != null) {
            log.info(city.toString());
        }
    }

    @Test
    public void isValid() {
        log.info(String.valueOf(constructorAutowiring.getJdbcTemplate() != null));
        log.info(String.valueOf(constructorAutowiring.getCityDao() != null));
    }
}
