package com.datasoft.spring_demo.controller;

import com.datasoft.spring_demo.model.User;
import com.datasoft.spring_demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestHomeController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Test
    public void loadDefaultEndPoint() {
        String response = restTemplate.getForObject("http://localhost:8085/", String.class);
        log.info(response);
    }

    @Test
    public void usingResponseEntity() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8085/", String.class);
        log.info("Response status code: {}", String.valueOf(responseEntity.getStatusCode()));
        log.info("Response body: {}", String.valueOf(responseEntity.getBody()));
    }

    @Test
    public void getUserList() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8085/getUserList", String.class);
        log.info("Response status code: {}", String.valueOf(responseEntity.getStatusCode()));
        log.info("Response body: {}", String.valueOf(responseEntity.getBody()));

        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> userList = new Gson().fromJson(responseEntity.getBody(), listType);
        userList.stream().forEach(s -> log.info(s.toString()));
    }

    /**
     * There are a couple of things happening in the code below.
     * First, we use ResponseEntity as our return type, using it to wrap the list of objects we really want.
     * Second, we are calling RestTemplate.exchange() instead of getForObject().
     * This is the most generic way to use RestTemplate.
     * It requires us to specify the HTTP method, optional request body, and a response type.
     * In this case, we use an anonymous subclass of ParameterizedTypeReference for the response type.
     */
    @Test
    public void getUserListAdvanced() {
        //Here is another way to get list of objects
        ResponseEntity<List<User>> responseWithList = restTemplate.exchange(
                "http://localhost:8080/getUserList",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});

        responseWithList.getBody().stream().forEach(s -> log.info(s.toString()));
    }

    @Test
    public void testPost() {
        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8080/add/{id}",
                HttpMethod.POST,
                null,
                User.class,
                7L);
        log.info(String.valueOf(responseEntity.getStatusCode()));
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void testPostWithObject() {
        HttpEntity<User> entity = new HttpEntity<>(new User(2L, "some user"));
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange("http://localhost:8080/addUser",
                HttpMethod.POST,
                entity,
                Boolean.class);
        log.info(String.valueOf(responseEntity.getStatusCode()));
        log.info(responseEntity.getBody().toString());
    }

}
