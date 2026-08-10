package com.telecom.aiops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.aiops.dto.AutomationResponseDto;
import com.telecom.aiops.service.AutomationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AutomationController {

    private final AutomationService automationService;

    @GetMapping("/api/automation")
    public AutomationResponseDto getAutomationDashboard() {
        return automationService.getAutomationData();
    }
}