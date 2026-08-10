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
public class RiskResponseDto {
    private RiskSummaryDto summary;
    private List<RiskTrendPointDto> trend;
    private List<RiskCategoryDto> categoryBreakdown;
    private List<RiskTowerRowDto> towers;
}