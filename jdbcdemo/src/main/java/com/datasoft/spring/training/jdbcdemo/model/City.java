package com.datasoft.spring.training.jdbcdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
    private String countryCode;
    private String district;
    private Integer population;
}
