package com.telecom.aiops.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDto {
    private int totalTowers;
    private int activeIncidents;
    private double avgNetworkHealthPercent;
    private int predictedRiskAlerts;
    private Map<String, Integer> towerStatusCounts;
    private List<DashboardTowerRowDto> towersNeedingAttention;
}