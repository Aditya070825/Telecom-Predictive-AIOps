package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 4 summary cards shown at the top of the KPI Intelligence page.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KpiSummaryDto {

    private double avgLatencyMs;
    private double avgPacketLossPercent;
    private double avgThroughputMbps;
    private double avgCallDropRatePercent;
}