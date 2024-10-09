package com.homework2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherDTO {
    private String locationName;
    private double temperature;
    private String condition;
    private double windSpeed;
    private double uvIndex;
    private String recommendation;
}
