package com.telecom.aiops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutomationLogRowDto {
    private String actionId;
    private String towerId;
    private String actionType;
    private String status;
    private String triggeredBy;
    private String timestamp;
}