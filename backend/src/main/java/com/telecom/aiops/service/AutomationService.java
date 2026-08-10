package com.telecom.aiops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.AutomationLogRowDto;
import com.telecom.aiops.dto.AutomationResponseDto;
import com.telecom.aiops.dto.AutomationSummaryDto;
import com.telecom.aiops.dto.AutomationTrendPointDto;
import com.telecom.aiops.dto.AutomationTypeDto;
import com.telecom.aiops.utils.DataGeneratorUtils;

@Service
public class AutomationService {

    private final Random random = new Random();

    private static final String[] ACTION_TYPES = {
            "Auto-restart", "Traffic reroute", "Load balancing", "Config rollback"
    };
    private static final String[] STATUSES = {"Success", "Failed", "In Progress"};
    private static final String[] TRIGGERED_BY = {"AI Prediction", "Threshold Alert", "Manual Override"};

    public AutomationResponseDto getAutomationData() {
        List<AutomationLogRowDto> logs = buildLogs();

        return AutomationResponseDto.builder()
                .summary(buildSummary(logs))
                .trend(buildTrend())
                .actionTypeBreakdown(buildActionTypeBreakdown(logs))
                .logs(logs)
                .build();
    }

    private AutomationSummaryDto buildSummary(List<AutomationLogRowDto> logs) {
        long successful = logs.stream().filter(l -> l.getStatus().equals("Success")).count();
        long failed = logs.stream().filter(l -> l.getStatus().equals("Failed")).count();

        return AutomationSummaryDto.builder()
                .totalAutomatedActions(logs.size())
                .successfulActions((int) successful)
                .failedActions((int) failed)
                .avgResolutionTimeSeconds(DataGeneratorUtils.round(5 + random.nextDouble() * 55))
                .build();
    }

    private List<AutomationTrendPointDto> buildTrend() {
        List<AutomationTrendPointDto> points = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            points.add(AutomationTrendPointDto.builder()
                    .timestamp(DataGeneratorUtils.hourlyTimestamp(i))
                    .actionsTriggered(random.nextInt(8))
                    .build());
        }
        return points;
    }

    private List<AutomationTypeDto> buildActionTypeBreakdown(List<AutomationLogRowDto> logs) {
        List<AutomationTypeDto> breakdown = new ArrayList<>();
        for (String actionType : ACTION_TYPES) {
            long count = logs.stream().filter(l -> l.getActionType().equals(actionType)).count();
            breakdown.add(AutomationTypeDto.builder()
                    .actionType(actionType)
                    .count((int) count)
                    .build());
        }
        return breakdown;
    }

    private List<AutomationLogRowDto> buildLogs() {
        List<AutomationLogRowDto> rows = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String actionId = "ACT-" + String.format("%04d", i);
            String towerId = DataGeneratorUtils.formatTowerId(random.nextInt(20) + 1);

            rows.add(AutomationLogRowDto.builder()
                    .actionId(actionId)
                    .towerId(towerId)
                    .actionType(ACTION_TYPES[random.nextInt(ACTION_TYPES.length)])
                    .status(STATUSES[random.nextInt(STATUSES.length)])
                    .triggeredBy(TRIGGERED_BY[random.nextInt(TRIGGERED_BY.length)])
                    .timestamp(DataGeneratorUtils.randomRecentTimestamp(random, 720))
                    .build());
        }
        return rows;
    }
}