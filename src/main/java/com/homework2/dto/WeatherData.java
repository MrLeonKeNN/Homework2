package com.homework2.dto;

import lombok.Data;

@Data
public class WeatherData {
    private Location location;
    private Current current;
}
