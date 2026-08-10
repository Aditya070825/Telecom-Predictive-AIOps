package com.telecom.aiops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a single telecom cell tower and its current operational status.
 * This is a core domain entity used across KPI, Incident, and Risk modules.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tower {

    private String towerId;
    private String towerName;
    private String region;
    private String city;

    private TowerStatus status;

    private double signalStrengthDbm;
    private int connectedUsers;
    private double bandwidthUsageMbps;

    private LocalDateTime lastUpdated;
}