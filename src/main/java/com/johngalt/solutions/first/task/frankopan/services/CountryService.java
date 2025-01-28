package com.johngalt.solutions.first.task.frankopan.services;

import com.johngalt.solutions.first.task.frankopan.entity.CountryResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CountryService {

    private final RestTemplate restTemplate = new RestTemplate();

    public CountryResponse getCountryInfo(String countryCode) {
        String url = String.format("https://restcountries.com/v3.1/alpha/%s", countryCode);

        try {
            // Log the raw response
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("API Response: " + response.getBody());

            // Deserialize the response
            List<CountryResponse> responses = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CountryResponse>>() {}
            ).getBody();

            if (responses != null && !responses.isEmpty()) {
                return responses.get(0);
            }
        } catch (Exception e) {
            System.err.println("Error fetching country info: " + e.getMessage());
        }

        return null;
    }
}