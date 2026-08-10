package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveSummaryDto {
    private int totalTowers;
    private double networkUptimePercent;
    private int activeIncidents;
    private double overallRiskScore;
}