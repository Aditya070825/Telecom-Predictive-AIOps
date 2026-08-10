package com.telecom.aiops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.aiops.dto.RiskResponseDto;
import com.telecom.aiops.service.RiskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RiskController {

    private final RiskService riskService;

    @GetMapping("/api/risk")
    public RiskResponseDto getRisk() {
        return riskService.getRiskData();
    }
}