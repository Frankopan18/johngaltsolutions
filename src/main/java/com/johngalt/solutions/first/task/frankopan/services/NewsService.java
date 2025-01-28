package com.johngalt.solutions.first.task.frankopan.services;

import com.johngalt.solutions.first.task.frankopan.entity.NewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    @Value("${news.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public NewsResponse getTopNews(String country) {
        String url = String.format("https://newsapi.org/v2/top-headlines?country=%s&apiKey=%s", country, apiKey);
        return restTemplate.getForObject(url, NewsResponse.class);
    }
}