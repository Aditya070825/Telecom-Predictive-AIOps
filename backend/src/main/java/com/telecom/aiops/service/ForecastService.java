package com.telecom.aiops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.ForecastCategoryDto;
import com.telecom.aiops.dto.ForecastPointDto;
import com.telecom.aiops.dto.ForecastResponseDto;
import com.telecom.aiops.dto.ForecastSummaryDto;
import com.telecom.aiops.dto.ForecastTowerRowDto;
import com.telecom.aiops.utils.DataGeneratorUtils;

@Service
public class ForecastService {

    private final Random random = new Random();

    private static final String[] FORECAST_WINDOWS = {"Next 6 hrs", "Next 12 hrs", "Next 24 hrs"};

    public ForecastResponseDto getForecastData() {
        List<ForecastTowerRowDto> towers = buildTowerForecasts();

        return ForecastResponseDto.builder()
                .summary(buildSummary(towers))
                .forecast(buildForecastPoints())
                .regionForecast(buildRegionForecast())
                .towers(towers)
                .build();
    }

    private ForecastSummaryDto buildSummary(List<ForecastTowerRowDto> towers) {
        long likelyToFail = towers.stream()
                .filter(t -> t.getConfidencePercent() >= 70 && t.getPredictedLoadMbps() > 450)
                .count();

        return ForecastSummaryDto.builder()
                .predictedPeakLoadMbps(DataGeneratorUtils.round(400 + random.nextDouble() * 200))
                .predictedAvgLatencyMs(DataGeneratorUtils.round(30 + random.nextDouble() * 40))
                .towersLikelyToFail((int) likelyToFail)
                .forecastAccuracyPercent(DataGeneratorUtils.round(80 + random.nextDouble() * 15))
                .build();
    }

    private List<ForecastPointDto> buildForecastPoints() {
        List<ForecastPointDto> points = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            boolean isFuture = i >= 6;
            points.add(ForecastPointDto.builder()
                    .timestamp(DataGeneratorUtils.hourlyTimestamp(i))
                    .actualLoadMbps(isFuture ? 0.0 : DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                    .predictedLoadMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                    .build());
        }
        return points;
    }

    private List<ForecastCategoryDto> buildRegionForecast() {
        List<ForecastCategoryDto> list = new ArrayList<>();
        for (String region : DataGeneratorUtils.REGIONS) {
            list.add(ForecastCategoryDto.builder()
                    .region(region)
                    .predictedLoadMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                    .build());
        }
        return list;
    }

    private List<ForecastTowerRowDto> buildTowerForecasts() {
        List<ForecastTowerRowDto> rows = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            rows.add(ForecastTowerRowDto.builder()
                    .towerId(DataGeneratorUtils.formatTowerId(i))
                    .towerName("Tower " + i)
                    .region(DataGeneratorUtils.randomRegion(random))
                    .predictedLoadMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 350))
                    .confidencePercent(DataGeneratorUtils.round(60 + random.nextDouble() * 40))
                    .forecastWindow(FORECAST_WINDOWS[random.nextInt(FORECAST_WINDOWS.length)])
                    .build());
        }
        return rows;
    }
}