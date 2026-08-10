package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskTowerRowDto {
    private String towerId;
    private String towerName;
    private String region;
    private double riskScore;
    private String riskLevel;
    private String predictedIssue;
}