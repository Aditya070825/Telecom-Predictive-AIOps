package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Average KPI values for one region — powers the region-wise
 * bar chart comparison on the KPI Intelligence page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiRegionDto {

    private String region;
    private double avgLatencyMs;
    private double avgThroughputMbps;
}
