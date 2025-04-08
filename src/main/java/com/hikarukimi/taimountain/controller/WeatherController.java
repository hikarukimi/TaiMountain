package com.hikarukimi.taimountain.controller;

import com.hikarukimi.taimountain.Response;
import com.hikarukimi.taimountain.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hikarukimi
 */
@RestController
@RequestMapping("/weathers")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        if (weatherService == null) {
            throw new IllegalArgumentException("weatherService must not be null");
        }
        this.weatherService = weatherService;
    }

    @GetMapping("/basic")
    public ResponseEntity<Response> getWeather() {
        try {
            Response response = weatherService.getWeather();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Response.error("Failed to retrieve basic weather information."));
        }
    }

    @GetMapping("/forecast")
    public ResponseEntity<Response> forecast(@RequestParam("location") String location) {
        if (location == null) {
            throw new IllegalArgumentException("location must not be null");
        }

        try {
            Response response = weatherService.getForecast(location);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Response.error("Failed to retrieve weather forecast."));
        }
    }

    @GetMapping("/gate")
    public ResponseEntity<Response> getGateTime() {
        try {
            Response response = weatherService.getGateTime();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Response.error("Failed to retrieve gate time."));
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<Response> getWeatherByLocation(@PathVariable("location") String location) {
        if (location == null) {
            throw new IllegalArgumentException("location must not be null");
        }

        try {
            Response response = weatherService.getWeatherByLocation(location);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Response.error("Failed to retrieve weather information."));
        }
    }
}