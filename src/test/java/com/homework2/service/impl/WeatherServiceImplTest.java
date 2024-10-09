package com.homework2.service.impl;

import com.homework2.dto.*;
import com.homework2.service.api.WeatherClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class WeatherServiceImplTest {
    @Mock
    private WeatherClient weatherClient;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheManager = new ConcurrentMapCacheManager("weather");
    }

    @Test
    public void testGetCurrentWeather_FirstCall_FetchesFromClient() {
        // Arrange
        double latitude = 55.7558;
        double longitude = 37.6173;
        String locationKey = latitude + "," + longitude;

        WeatherData weatherData = new WeatherData();
        Location location = new Location();
        location.setName("Moscow");
        weatherData.setLocation(location);

        Current current = new Current();
        current.setTempC(20.0);
        current.setCondition(Condition.builder().text(("Sunny")).build());
        current.setWind_kph(15.0);
        current.setUv(6.0);
        weatherData.setCurrent(current);

        when(weatherClient.getCurrentWeather(anyString(), eq(locationKey), eq("no"))).thenReturn(weatherData);

        // Act
        WeatherDTO result = weatherService.getCurrentWeather(latitude, longitude);

        // Assert
        assertEquals("Moscow", result.getLocationName());
        assertEquals(20.0, result.getTemperature());
        assertEquals("Sunny", result.getCondition());
        assertEquals(15.0, result.getWindSpeed());
        assertEquals(6.0, result.getUvIndex());
        assertEquals("The weather is quite comfortable, light sweaters and trousers are suitable. It is sunny, do not forget to wear sunglasses and use sunscreen. Light wind, it may be good to take a windbreaker. Moderate UV level, sunscreen may be used for safety.", result.getRecommendation());

        // Verify client method was called once
        verify(weatherClient, times(1)).getCurrentWeather(anyString(), eq(locationKey), eq("no"));
    }

}