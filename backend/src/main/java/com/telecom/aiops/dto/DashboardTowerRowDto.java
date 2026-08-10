package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardTowerRowDto {
    private String towerId;
    private String towerName;
    private String region;
    private String status;
    private double signalStrengthDbm;
}