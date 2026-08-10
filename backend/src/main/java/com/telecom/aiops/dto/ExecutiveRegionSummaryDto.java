package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveRegionSummaryDto {
    private String region;
    private double uptimePercent;
    private int incidentCount;
    private double riskScore;
}