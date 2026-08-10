package com.telecom.aiops.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.DashboardResponseDto;
import com.telecom.aiops.dto.DashboardTowerRowDto;
import com.telecom.aiops.dto.IncidentResponseDto;
import com.telecom.aiops.dto.RiskResponseDto;
import com.telecom.aiops.model.Tower;
import com.telecom.aiops.model.TowerStatus;
import com.telecom.aiops.utils.DataGeneratorUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TowerService towerService;
    private final IncidentService incidentService;
    private final RiskService riskService;

    public DashboardResponseDto getDashboardData() {
        List<Tower> towers = towerService.getAllTowers();
        IncidentResponseDto incidentData = incidentService.getIncidentData();
        RiskResponseDto riskData = riskService.getRiskData();

        int totalTowers = towers.size();

        long activeCount = towers.stream()
                .filter(t -> t.getStatus() == TowerStatus.ACTIVE)
                .count();
        double avgNetworkHealthPercent = DataGeneratorUtils.round(
                (activeCount * 100.0) / totalTowers);

        int activeIncidents = incidentData.getSummary().getOpenIncidents();
        int predictedRiskAlerts = riskData.getSummary().getHighRiskTowers();

        Map<String, Integer> towerStatusCounts = new HashMap<>();
        for (TowerStatus status : TowerStatus.values()) {
            long count = towers.stream().filter(t -> t.getStatus() == status).count();
            towerStatusCounts.put(status.name(), (int) count);
        }

        List<DashboardTowerRowDto> towersNeedingAttention = towers.stream()
                .filter(t -> t.getStatus() == TowerStatus.DEGRADED || t.getStatus() == TowerStatus.DOWN)
                .map(t -> DashboardTowerRowDto.builder()
                        .towerId(t.getTowerId())
                        .towerName(t.getTowerName())
                        .region(t.getRegion())
                        .status(t.getStatus().name())
                        .signalStrengthDbm(t.getSignalStrengthDbm())
                        .build())
                .collect(Collectors.toList());

        return DashboardResponseDto.builder()
                .totalTowers(totalTowers)
                .activeIncidents(activeIncidents)
                .avgNetworkHealthPercent(avgNetworkHealthPercent)
                .predictedRiskAlerts(predictedRiskAlerts)
                .towerStatusCounts(towerStatusCounts)
                .towersNeedingAttention(towersNeedingAttention)
                .build();
    }
}