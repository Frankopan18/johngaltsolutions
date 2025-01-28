package com.johngalt.solutions.first.task.frankopan.controllers;

import com.johngalt.solutions.first.task.frankopan.UpdateConfig;
import com.johngalt.solutions.first.task.frankopan.entity.CityInfo;
import com.johngalt.solutions.first.task.frankopan.entity.CountryResponse;
import com.johngalt.solutions.first.task.frankopan.entity.NewsResponse;
import com.johngalt.solutions.first.task.frankopan.entity.WeatherResponse;
import com.johngalt.solutions.first.task.frankopan.repository.CityInfoRepository;
import com.johngalt.solutions.first.task.frankopan.services.CountryService;
import com.johngalt.solutions.first.task.frankopan.services.NewsService;
import com.johngalt.solutions.first.task.frankopan.services.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CityInfoController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CityInfoRepository cityInfoRepository;

    @Autowired
    private UpdateConfig updateConfig;

    @Scheduled(fixedRateString = "${update.interval.minutes}", timeUnit = TimeUnit.MINUTES)
    public void updateDatabase() {
        int updateIntervalMinutes = updateConfig.getUpdateIntervalMinutes();
        System.out.println("Updating database every " + updateIntervalMinutes + " minutes.");

        List<String> cities = cityInfoRepository.findAll().stream().map(CityInfo::getCity).toList();

        // Fetch new data and perform updates
        List<CityInfo> updatedData = retrieveCitiesData(cities);
    }

    @GetMapping("/city-info")
    @Operation(summary = "Retrieves City Informations", description = "Returns city data from various APIs")
    public ResponseEntity<?> getCityInfo(@RequestParam List<String> cities, @RequestParam(defaultValue = "false") boolean export) {
        List<CityInfo> cityInfos = retrieveCitiesData(cities);
        if (export) {
            exportToCsv(cityInfos);
            return ResponseEntity.ok("Data successfully exported");
        }

        return ResponseEntity.ok(cityInfos);
    }

    private List<CityInfo> retrieveCitiesData(List<String> cities) {
        List<CityInfo> cityInfos = new ArrayList<>();

        for (String city : cities) {
            WeatherResponse weatherResponse = weatherService.getWeather(city);
            if (weatherResponse != null) {
                String countryCode = weatherResponse.getSys().getCountry();
                CountryResponse countryResponse = countryService.getCountryInfo(countryCode);
                NewsResponse newsResponse = newsService.getTopNews(countryCode);

                CityInfo cityInfo = new CityInfo();
                cityInfo.setCity(city);
                cityInfo.setTemperature(weatherResponse.getMain().getTemp());
                cityInfo.setCountryCode(countryCode);
                cityInfo.setLanguage(countryResponse.getLanguages().values().stream().findFirst().get().toString());
                cityInfo.setBorderingCountries(countryResponse.getBorders());
                cityInfo.setTopNews(newsResponse.getArticles().stream().map(NewsResponse.Article::getTitle).collect(Collectors.toList()));
                cityInfo.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));


                cityInfoRepository.save(cityInfo);
                cityInfos.add(cityInfo);
            }
        }
        return cityInfos;
    }

    private void exportToCsv(List<CityInfo> cityInfos) {
        try (FileWriter writer = new FileWriter("country_language_data.csv");
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("City", "Temperature", "Country Code", "Language", "Bordering Countries", "Top News"))) {

            for (CityInfo cityInfo : cityInfos) {
                csvPrinter.printRecord(
                        cityInfo.getCity(),
                        cityInfo.getTemperature(),
                        cityInfo.getCountryCode(),
                        cityInfo.getLanguage(),
                        String.join(", ", cityInfo.getBorderingCountries()),
                        String.join(", ", cityInfo.getTopNews())
                );
            }

            csvPrinter.flush();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}