package com.telecom.aiops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.aiops.dto.IncidentResponseDto;
import com.telecom.aiops.service.IncidentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping("/api/incidents")
    public IncidentResponseDto getIncidents() {
        return incidentService.getIncidentData();
    }
}