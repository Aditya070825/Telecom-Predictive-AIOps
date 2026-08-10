package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentRowDto {
    private String incidentId;
    private String towerId;
    private String title;
    private String severity;
    private String status;
    private String createdAt;
    private String region;
}