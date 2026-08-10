package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentSummaryDto {
    private int totalIncidents;
    private int openIncidents;
    private int resolvedIncidents;
    private double avgResolutionTimeMinutes;
}