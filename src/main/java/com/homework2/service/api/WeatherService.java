package com.homework2.service.api;

import com.homework2.dto.WeatherDTO;

public interface WeatherService {

    WeatherDTO getCurrentWeather(double latitude, double longitude);
}
