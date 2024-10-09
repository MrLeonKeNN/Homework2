package com.homework2.controller;

import com.homework2.dto.WeatherDTO;
import com.homework2.service.impl.WeatherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The {@link WeatherController} class is a REST controller that provides endpoints for retrieving weather information.
 * <p>
 * This class handles HTTP GET requests to fetch current weather data based on the specified latitude and longitude.
 * It uses the {@link WeatherServiceImpl} service to obtain the weather data.
 * </p>
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherServiceImpl weatherService;

    /**
     * Retrieves the current weather data for the specified latitude and longitude.
     *
     * @param latitude  the latitude of the location.
     * @param longitude the longitude of the location.
     * @return a {@link ResponseEntity} containing the {@link WeatherDTO} object with current weather data and an HTTP status of 200 (OK).
     */
    @GetMapping("/{latitude}/{longitude}")
    public ResponseEntity<WeatherDTO> getWeather(@PathVariable Double latitude, @PathVariable Double longitude) {
        WeatherDTO weatherDTO = weatherService.getCurrentWeather(latitude, longitude);
        return ResponseEntity.ok(weatherDTO);
    }
}
