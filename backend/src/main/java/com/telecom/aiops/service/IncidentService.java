package com.telecom.aiops.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.dto.IncidentResponseDto;
import com.telecom.aiops.dto.IncidentRowDto;
import com.telecom.aiops.dto.IncidentSeverityDto;
import com.telecom.aiops.dto.IncidentSummaryDto;
import com.telecom.aiops.dto.IncidentTrendPointDto;
import com.telecom.aiops.utils.DataGeneratorUtils;

@Service
public class IncidentService {

    private final Random random = new Random();

    private static final String[] SEVERITIES = {"Critical", "High", "Medium", "Low"};
    private static final String[] STATUSES = {"Open", "In Progress", "Resolved"};
    private static final String[] INCIDENT_TITLES = {
            "High latency detected", "Packet loss spike", "Tower signal degradation",
            "Unexpected downtime", "Call drop rate exceeded threshold",
            "Throughput below SLA", "Hardware fault reported", "Congestion alert"
    };

    public IncidentResponseDto getIncidentData() {
        List<IncidentRowDto> incidents = buildIncidents();

        return IncidentResponseDto.builder()
                .summary(buildSummary(incidents))
                .trend(buildTrend())
                .severityBreakdown(buildSeverityBreakdown(incidents))
                .incidents(incidents)
                .build();
    }

    private IncidentSummaryDto buildSummary(List<IncidentRowDto> incidents) {
        long open = incidents.stream().filter(i -> !i.getStatus().equals("Resolved")).count();
        long resolved = incidents.size() - open;

        return IncidentSummaryDto.builder()
                .totalIncidents(incidents.size())
                .openIncidents((int) open)
                .resolvedIncidents((int) resolved)
                .avgResolutionTimeMinutes(DataGeneratorUtils.round(20 + random.nextDouble() * 100))
                .build();
    }

    private List<IncidentTrendPointDto> buildTrend() {
        List<IncidentTrendPointDto> points = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            points.add(IncidentTrendPointDto.builder()
                    .timestamp(DataGeneratorUtils.hourlyTimestamp(i))
                    .incidentCount(random.nextInt(10))
                    .build());
        }
        return points;
    }

    private List<IncidentSeverityDto> buildSeverityBreakdown(List<IncidentRowDto> incidents) {
        List<IncidentSeverityDto> breakdown = new ArrayList<>();
        for (String severity : SEVERITIES) {
            long count = incidents.stream().filter(i -> i.getSeverity().equals(severity)).count();
            breakdown.add(IncidentSeverityDto.builder()
                    .severity(severity)
                    .count((int) count)
                    .build());
        }
        return breakdown;
    }

    private List<IncidentRowDto> buildIncidents() {
        List<IncidentRowDto> rows = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String incidentId = "INC-" + String.format("%04d", i);
            String towerId = DataGeneratorUtils.formatTowerId(random.nextInt(20) + 1);

            rows.add(IncidentRowDto.builder()
                    .incidentId(incidentId)
                    .towerId(towerId)
                    .title(INCIDENT_TITLES[random.nextInt(INCIDENT_TITLES.length)])
                    .severity(SEVERITIES[random.nextInt(SEVERITIES.length)])
                    .status(STATUSES[random.nextInt(STATUSES.length)])
                    .createdAt(DataGeneratorUtils.randomRecentTimestamp(random, 720))
                    .region(DataGeneratorUtils.randomRegion(random))
                    .build());
        }
        return rows;
    }
}