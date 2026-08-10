package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskSummaryDto {
    private int highRiskTowers;
    private int mediumRiskTowers;
    private int lowRiskTowers;
    private double avgRiskScore;
}