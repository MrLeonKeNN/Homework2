package com.homework2.service.api;

import com.homework2.dto.WeatherData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-client", url = "http://api.weatherapi.com/v1")
public interface WeatherClient {

    @GetMapping("/current.json")
    WeatherData getCurrentWeather(
            @RequestParam("key") String apiKey,
            @RequestParam("q") String coordinates,
            @RequestParam("aqi") String aqi);
}
