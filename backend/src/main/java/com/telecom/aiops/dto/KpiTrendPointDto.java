package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A single point on the KPI trend chart — one timestamp with values
 * for each tracked KPI at that moment. Used to build the multi-line
 * trend chart on the KPI Intelligence page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiTrendPointDto {

    private String timestamp;
    private double latencyMs;
    private double packetLossPercent;
    private double throughputMbps;
    private double callDropRatePercent;
}
