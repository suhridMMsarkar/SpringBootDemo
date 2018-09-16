package com.datasoft.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringDemoApplication {

    @Bean
    public RestTemplate restTemplate() {
//        return new RestTemplate();
        return new RestTemplateBuilder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }
}
