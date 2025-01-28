package com.johngalt.solutions.first.task.frankopan.services;

import com.johngalt.solutions.first.task.frankopan.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(String city) {
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apiKey);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}