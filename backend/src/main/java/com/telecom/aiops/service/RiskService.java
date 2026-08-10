package com.telecom.aiops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.RiskCategoryDto;
import com.telecom.aiops.dto.RiskResponseDto;
import com.telecom.aiops.dto.RiskSummaryDto;
import com.telecom.aiops.dto.RiskTowerRowDto;
import com.telecom.aiops.dto.RiskTrendPointDto;
import com.telecom.aiops.utils.DataGeneratorUtils;

@Service
public class RiskService {

    private final Random random = new Random();

    private static final String[] CATEGORIES = {"Hardware", "Weather", "Congestion", "Power"};
    private static final String[] PREDICTED_ISSUES = {
            "Possible outage in 6-12 hrs", "Elevated latency expected", "Signal degradation trend",
            "Hardware failure risk rising", "Congestion likely during peak hours",
            "Power instability detected", "No immediate risk", "Stable — monitor only"
    };

    public RiskResponseDto getRiskData() {
        List<RiskTowerRowDto> towers = buildTowerRisks();

        return RiskResponseDto.builder()
                .summary(buildSummary(towers))
                .trend(buildTrend())
                .categoryBreakdown(buildCategoryBreakdown())
                .towers(towers)
                .build();
    }

    private RiskSummaryDto buildSummary(List<RiskTowerRowDto> towers) {
        long high = towers.stream().filter(t -> t.getRiskLevel().equals("High")).count();
        long medium = towers.stream().filter(t -> t.getRiskLevel().equals("Medium")).count();
        long low = towers.size() - high - medium;

        double avgScore = towers.stream()
                .mapToDouble(RiskTowerRowDto::getRiskScore)
                .average()
                .orElse(0.0);

        return RiskSummaryDto.builder()
                .highRiskTowers((int) high)
                .mediumRiskTowers((int) medium)
                .lowRiskTowers((int) low)
                .avgRiskScore(DataGeneratorUtils.round(avgScore))
                .build();
    }

    private List<RiskTrendPointDto> buildTrend() {
        List<RiskTrendPointDto> points = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            points.add(RiskTrendPointDto.builder()
                    .timestamp(DataGeneratorUtils.hourlyTimestamp(i))
                    .avgRiskScore(DataGeneratorUtils.round(random.nextDouble() * 100))
                    .build());
        }
        return points;
    }

    private List<RiskCategoryDto> buildCategoryBreakdown() {
        List<RiskCategoryDto> list = new ArrayList<>();
        for (String category : CATEGORIES) {
            list.add(RiskCategoryDto.builder()
                    .category(category)
                    .count(random.nextInt(10))
                    .build());
        }
        return list;
    }

    private List<RiskTowerRowDto> buildTowerRisks() {
        List<RiskTowerRowDto> rows = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            double score = DataGeneratorUtils.round(random.nextDouble() * 100);
            String level = score >= 70 ? "High" : score >= 40 ? "Medium" : "Low";

            rows.add(RiskTowerRowDto.builder()
                    .towerId(DataGeneratorUtils.formatTowerId(i))
                    .towerName("Tower " + i)
                    .region(DataGeneratorUtils.randomRegion(random))
                    .riskScore(score)
                    .riskLevel(level)
                    .predictedIssue(PREDICTED_ISSUES[random.nextInt(PREDICTED_ISSUES.length)])
                    .build());
        }
        return rows;
    }
}