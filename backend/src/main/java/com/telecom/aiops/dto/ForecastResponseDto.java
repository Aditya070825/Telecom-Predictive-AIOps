package com.telecom.aiops.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastResponseDto {
    private ForecastSummaryDto summary;
    private List<ForecastPointDto> forecast;
    private List<ForecastCategoryDto> regionForecast;
    private List<ForecastTowerRowDto> towers;
}