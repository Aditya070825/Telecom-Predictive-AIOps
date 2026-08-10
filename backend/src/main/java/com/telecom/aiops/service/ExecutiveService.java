package com.telecom.aiops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.ExecutiveHighlightDto;
import com.telecom.aiops.dto.ExecutiveRegionSummaryDto;
import com.telecom.aiops.dto.ExecutiveResponseDto;
import com.telecom.aiops.dto.ExecutiveSummaryDto;
import com.telecom.aiops.dto.ExecutiveTrendPointDto;
import com.telecom.aiops.utils.DataGeneratorUtils;

@Service
public class ExecutiveService {

    private final Random random = new Random();

    private static final String[] CATEGORIES = {"Automation", "Risk", "Incident"};
    private static final String[] HIGHLIGHT_TITLES = {
            "Multiple towers auto-healed after congestion alert",
            "Risk score improved following config rollback",
            "New high-severity incident reported",
            "Network uptime hit weekly high",
            "Forecast flags potential load spike",
            "Automation success rate trending upward"
    };

    public ExecutiveResponseDto getExecutiveData() {
        List<ExecutiveRegionSummaryDto> regionSummary = buildRegionSummary();

        return ExecutiveResponseDto.builder()
                .summary(buildSummary(regionSummary))
                .trend(buildTrend())
                .regionSummary(regionSummary)
                .highlights(buildHighlights())
                .build();
    }

    private ExecutiveSummaryDto buildSummary(List<ExecutiveRegionSummaryDto> regionSummary) {
        double avgUptime = regionSummary.stream()
                .mapToDouble(ExecutiveRegionSummaryDto::getUptimePercent)
                .average()
                .orElse(0.0);

        int totalIncidents = regionSummary.stream()
                .mapToInt(ExecutiveRegionSummaryDto::getIncidentCount)
                .sum();

        double avgRisk = regionSummary.stream()
                .mapToDouble(ExecutiveRegionSummaryDto::getRiskScore)
                .average()
                .orElse(0.0);

        return ExecutiveSummaryDto.builder()
                .totalTowers(20)
                .networkUptimePercent(DataGeneratorUtils.round(avgUptime))
                .activeIncidents(totalIncidents)
                .overallRiskScore(DataGeneratorUtils.round(avgRisk))
                .build();
    }

    private List<ExecutiveTrendPointDto> buildTrend() {
        List<ExecutiveTrendPointDto> points = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            points.add(ExecutiveTrendPointDto.builder()
                    .timestamp(DataGeneratorUtils.hourlyTimestamp(i))
                    .networkHealthScore(DataGeneratorUtils.round(70 + random.nextDouble() * 30))
                    .build());
        }
        return points;
    }

    private List<ExecutiveRegionSummaryDto> buildRegionSummary() {
        List<ExecutiveRegionSummaryDto> list = new ArrayList<>();
        for (String region : DataGeneratorUtils.REGIONS) {
            list.add(ExecutiveRegionSummaryDto.builder()
                    .region(region)
                    .uptimePercent(DataGeneratorUtils.round(90 + random.nextDouble() * 10))
                    .incidentCount(random.nextInt(6))
                    .riskScore(DataGeneratorUtils.round(random.nextDouble() * 100))
                    .build());
        }
        return list;
    }

    private List<ExecutiveHighlightDto> buildHighlights() {
        List<ExecutiveHighlightDto> highlights = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            highlights.add(ExecutiveHighlightDto.builder()
                    .title(HIGHLIGHT_TITLES[random.nextInt(HIGHLIGHT_TITLES.length)])
                    .category(CATEGORIES[random.nextInt(CATEGORIES.length)])
                    .timestamp(DataGeneratorUtils.randomRecentTimestamp(random, 720))
                    .build());
        }
        return highlights;
    }
}