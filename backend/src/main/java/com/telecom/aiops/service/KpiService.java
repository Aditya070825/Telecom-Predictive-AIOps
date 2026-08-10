package com.telecom.aiops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.KpiRegionDto;
import com.telecom.aiops.dto.KpiResponseDto;
import com.telecom.aiops.dto.KpiSummaryDto;
import com.telecom.aiops.dto.KpiTowerRowDto;
import com.telecom.aiops.dto.KpiTrendPointDto;
import com.telecom.aiops.utils.DataGeneratorUtils;

@Service
public class KpiService {

    private final Random random = new Random();

    public KpiResponseDto getKpiData() {
        KpiSummaryDto summary = buildSummary();
        List<KpiTrendPointDto> trend = buildTrend();
        List<KpiRegionDto> regions = buildRegions();
        List<KpiTowerRowDto> towers = buildTowerRows();

        return KpiResponseDto.builder()
                .summary(summary)
                .trend(trend)
                .regionComparison(regions)
                .towerBreakdown(towers)
                .build();
    }

    private KpiSummaryDto buildSummary() {
        return KpiSummaryDto.builder()
                .avgLatencyMs(DataGeneratorUtils.round(30 + random.nextDouble() * 40))
                .avgPacketLossPercent(DataGeneratorUtils.round(random.nextDouble() * 3))
                .avgThroughputMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                .avgCallDropRatePercent(DataGeneratorUtils.round(random.nextDouble() * 2))
                .build();
    }

    private List<KpiTrendPointDto> buildTrend() {
        List<KpiTrendPointDto> points = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            points.add(KpiTrendPointDto.builder()
                    .timestamp(DataGeneratorUtils.hourlyTimestamp(i))
                    .latencyMs(DataGeneratorUtils.round(30 + random.nextDouble() * 40))
                    .packetLossPercent(DataGeneratorUtils.round(random.nextDouble() * 3))
                    .throughputMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                    .callDropRatePercent(DataGeneratorUtils.round(random.nextDouble() * 2))
                    .build());
        }
        return points;
    }

    private List<KpiRegionDto> buildRegions() {
        List<KpiRegionDto> list = new ArrayList<>();
        for (String region : DataGeneratorUtils.REGIONS) {
            list.add(KpiRegionDto.builder()
                    .region(region)
                    .avgLatencyMs(DataGeneratorUtils.round(30 + random.nextDouble() * 40))
                    .avgThroughputMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                    .build());
        }
        return list;
    }

    private List<KpiTowerRowDto> buildTowerRows() {
        List<KpiTowerRowDto> rows = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            rows.add(KpiTowerRowDto.builder()
                    .towerId(DataGeneratorUtils.formatTowerId(i))
                    .towerName("Tower " + i)
                    .region(DataGeneratorUtils.randomRegion(random))
                    .latencyMs(DataGeneratorUtils.round(30 + random.nextDouble() * 40))
                    .packetLossPercent(DataGeneratorUtils.round(random.nextDouble() * 3))
                    .throughputMbps(DataGeneratorUtils.round(200 + random.nextDouble() * 300))
                    .callDropRatePercent(DataGeneratorUtils.round(random.nextDouble() * 2))
                    .build());
        }
        return rows;
    }
}