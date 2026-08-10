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
public class AutomationResponseDto {
    private AutomationSummaryDto summary;
    private List<AutomationTrendPointDto> trend;
    private List<AutomationTypeDto> actionTypeBreakdown;
    private List<AutomationLogRowDto> logs;
}