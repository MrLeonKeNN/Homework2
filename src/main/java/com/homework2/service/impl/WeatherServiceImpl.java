package com.homework2.service.impl;

import com.homework2.dto.WeatherDTO;
import com.homework2.dto.WeatherData;
import com.homework2.service.api.WeatherClient;
import com.homework2.service.api.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link WeatherService} interface that provides methods to get current weather data.
 * <p>
 * This class uses {@link WeatherClient} to retrieve weather data and is annotated with {@link Service} to mark it as a Spring service.
 * It also uses {@link AllArgsConstructor} to generate a constructor with all required dependencies.
 * </p>
 */
@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;
    private static final String API_KEY = "c8d70ac73e2f4639b04132653231203";

    // Strings for recommendations
    private static final String VERY_HOT_RECOMMENDATION = "It is very hot outside, avoid long exposure to the sun, drink more water, and wear light, light-colored clothing. ";
    private static final String WARM_RECOMMENDATION = "The weather is warm, it is recommended to wear light clothing and remember to drink water. ";
    private static final String COMFORTABLE_RECOMMENDATION = "The weather is quite comfortable, light sweaters and trousers are suitable. ";
    private static final String COOL_RECOMMENDATION = "It is cool outside, it is recommended to wear a jacket. ";
    private static final String NEAR_ZERO_RECOMMENDATION = "The temperature is near zero, wear warm clothing. ";
    private static final String VERY_COLD_RECOMMENDATION = "It is very cold, wear warm clothing, a hat, gloves, and a scarf. ";
    private static final String RAINY_RECOMMENDATION = "It is raining, do not forget to take an umbrella and waterproof shoes. ";
    private static final String SNOWY_RECOMMENDATION = "It is snowing, wear warm boots and a waterproof jacket. Be careful, it may be slippery. ";
    private static final String SUNNY_RECOMMENDATION = "It is sunny, do not forget to wear sunglasses and use sunscreen. ";
    private static final String CLOUDY_RECOMMENDATION = "The weather is cloudy, the temperature may change, bring something in case it gets colder. ";
    private static final String STRONG_WIND_RECOMMENDATION = "Strong wind, try to avoid open areas and dress warmly. ";
    private static final String LIGHT_WIND_RECOMMENDATION = "Light wind, it may be good to take a windbreaker. ";
    private static final String HIGH_UV_RECOMMENDATION = "High UV level, it is recommended to use sunscreen and wear a hat. ";
    private static final String MODERATE_UV_RECOMMENDATION = "Moderate UV level, sunscreen may be used for safety. ";

    /**
     * Retrieves current weather data for the specified latitude and longitude.
     * <p>
     * The result is cached using the Spring Cache with the cache name "weather" and a key composed of latitude and longitude.
     * </p>
     *
     * @param latitude  the latitude of the location.
     * @param longitude the longitude of the location.
     * @return a {@link WeatherDTO} object containing the current weather data.
     */
    @Cacheable(value = "weather", key = "#latitude + ',' + #longitude")
    public WeatherDTO getCurrentWeather(double latitude, double longitude) {
        String location = latitude + "," + longitude;
        WeatherData response = weatherClient.getCurrentWeather(API_KEY, location, "no");

        double temperature = response.getCurrent().getTempC();
        String condition = response.getCurrent().getCondition().getText();
        double windSpeed = response.getCurrent().getWind_kph();
        double uvIndex = response.getCurrent().getUv();
        String recommendation = generateRecommendation(temperature, condition, windSpeed, uvIndex);

        return new WeatherDTO(response.getLocation().getName(), temperature, condition, windSpeed, uvIndex, recommendation);
    }

    /**
     * Generates a weather recommendation based on temperature, condition, wind speed, and UV index.
     *
     * @param temperature the current temperature in degrees Celsius.
     * @param condition   the current weather condition (e.g., sunny, rain).
     * @param windSpeed   the current wind speed in kph.
     * @param uvIndex     the current UV index.
     * @return a string containing the weather recommendation.
     */
    private String generateRecommendation(double temperature, String condition, double windSpeed, double uvIndex) {
        StringBuilder recommendation = new StringBuilder();

        if (temperature > 30) {
            recommendation.append(VERY_HOT_RECOMMENDATION);
        } else if (temperature > 25) {
            recommendation.append(WARM_RECOMMENDATION);
        } else if (temperature > 15) {
            recommendation.append(COMFORTABLE_RECOMMENDATION);
        } else if (temperature > 5) {
            recommendation.append(COOL_RECOMMENDATION);
        } else if (temperature > 0) {
            recommendation.append(NEAR_ZERO_RECOMMENDATION);
        } else {
            recommendation.append(VERY_COLD_RECOMMENDATION);
        }

        if (condition.toLowerCase().contains("rain")) {
            recommendation.append(RAINY_RECOMMENDATION);
        } else if (condition.toLowerCase().contains("snow")) {
            recommendation.append(SNOWY_RECOMMENDATION);
        } else if (condition.toLowerCase().contains("sunny")) {
            recommendation.append(SUNNY_RECOMMENDATION);
        } else if (condition.toLowerCase().contains("cloudy")) {
            recommendation.append(CLOUDY_RECOMMENDATION);
        }

        if (windSpeed > 20) {
            recommendation.append(STRONG_WIND_RECOMMENDATION);
        } else if (windSpeed > 10) {
            recommendation.append(LIGHT_WIND_RECOMMENDATION);
        }

        if (uvIndex > 7) {
            recommendation.append(HIGH_UV_RECOMMENDATION);
        } else if (uvIndex > 5) {
            recommendation.append(MODERATE_UV_RECOMMENDATION);
        }

        return recommendation.toString().trim();
    }
}
