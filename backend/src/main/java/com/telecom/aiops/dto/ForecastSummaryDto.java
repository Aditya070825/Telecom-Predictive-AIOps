package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastSummaryDto {
    private double predictedPeakLoadMbps;
    private double predictedAvgLatencyMs;
    private int towersLikelyToFail;
    private double forecastAccuracyPercent;
}
