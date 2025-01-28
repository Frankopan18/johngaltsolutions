package com.johngalt.solutions.first.task.frankopan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CityInfo {
    @Id
    private String city;
    private double temperature;
    private String countryCode;
    private String language;
    private List<String> borderingCountries = new ArrayList<>();
    private List<String> topNews = new ArrayList<>();

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    private Timestamp lastUpdated;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getBorderingCountries() {
        return borderingCountries;
    }

    public void setBorderingCountries(List<String> borderingCountries) {
        this.borderingCountries = borderingCountries;
    }

    public List<String> getTopNews() {
        return topNews;
    }

    public void setTopNews(List<String> topNews) {
        this.topNews = topNews;
    }
}
