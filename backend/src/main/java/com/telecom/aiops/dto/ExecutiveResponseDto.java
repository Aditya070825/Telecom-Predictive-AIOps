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
public class ExecutiveResponseDto {
    private ExecutiveSummaryDto summary;
    private List<ExecutiveTrendPointDto> trend;
    private List<ExecutiveRegionSummaryDto> regionSummary;
    private List<ExecutiveHighlightDto> highlights;
}