package com.telecom.aiops.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Top-level response for the /api/kpi endpoint.
 * Bundles the summary cards, trend chart, region comparison chart,
 * and per-tower table into a single response consumed by the
 * KPI Intelligence page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiResponseDto {

    private KpiSummaryDto summary;
    private List<KpiTrendPointDto> trend;
    private List<KpiRegionDto> regionComparison;
    private List<KpiTowerRowDto> towerBreakdown;
}
