package com.homework2.controller;

import com.homework2.dto.WeatherDTO;
import com.homework2.exeptions.EntityNotFoundException;
import com.homework2.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeather_Positive() throws Exception {
        // Arrange
        double latitude = 55.7558;
        double longitude = 37.6173;

        WeatherDTO weatherDTO = new WeatherDTO("Moscow", 20.0, "Sunny", 10.0, 5.0, "Теплая погода, рекомендуется надеть легкую одежду и не забывать пить воду.");

        when(weatherService.getCurrentWeather(latitude, longitude)).thenReturn(weatherDTO);

        // Act & Assert
        mockMvc.perform(get("/weather/{latitude}/{longitude}", latitude, longitude)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                            "locationName": "Moscow",
                            "temperature": 20.0,
                            "condition": "Sunny",
                            "windSpeed": 10.0,
                            "uvIndex": 5.0,
                            "recommendation": "Теплая погода, рекомендуется надеть легкую одежду и не забывать пить воду."
                        }
                        """));
        verify(weatherService, times(1)).getCurrentWeather(latitude, longitude);
    }

    @Test
    public void testGetWeather_Negative_NotFound() throws Exception {
        double latitude = 55.7558;
        double longitude = 37.6173;

        when(weatherService.getCurrentWeather(latitude, longitude)).thenThrow(new EntityNotFoundException("Weather data not found"));

        mockMvc.perform(get("/weather/{latitude}/{longitude}", latitude, longitude)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(weatherService, times(1)).getCurrentWeather(latitude, longitude);
    }

    @Test
    public void testGetWeather_Negative_InvalidInput() throws Exception {
        String invalidLatitude = "invalid";
        double longitude = 37.6173;

        mockMvc.perform(get("/weather/{latitude}/{longitude}", invalidLatitude, longitude)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(weatherService, times(0)).getCurrentWeather(anyDouble(), anyDouble());
    }

}