package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * One row in the per-tower KPI breakdown table on the
 * KPI Intelligence page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiTowerRowDto {

    private String towerId;
    private String towerName;
    private String region;
    private double latencyMs;
    private double packetLossPercent;
    private double throughputMbps;
    private double callDropRatePercent;
}