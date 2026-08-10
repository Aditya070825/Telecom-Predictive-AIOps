package com.telecom.aiops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.aiops.dto.ForecastResponseDto;
import com.telecom.aiops.service.ForecastService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping("/api/forecast")
    public ForecastResponseDto getForecast() {
        return forecastService.getForecastData();
    }
}