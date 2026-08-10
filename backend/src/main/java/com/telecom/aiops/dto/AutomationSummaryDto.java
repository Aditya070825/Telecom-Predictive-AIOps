package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutomationSummaryDto {
    private int totalAutomatedActions;
    private int successfulActions;
    private int failedActions;
    private double avgResolutionTimeSeconds;
}