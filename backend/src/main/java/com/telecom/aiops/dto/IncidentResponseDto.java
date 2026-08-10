package com.telecom.aiops.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentResponseDto {
    private IncidentSummaryDto summary;
    private List<IncidentTrendPointDto> trend;
    private List<IncidentSeverityDto> severityBreakdown;
    private List<IncidentRowDto> incidents;
}