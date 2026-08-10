package com.telecom.aiops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.aiops.dto.KpiResponseDto;
import com.telecom.aiops.service.KpiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KpiController {

    private final KpiService kpiService;

    @GetMapping("/api/kpi")
    public KpiResponseDto getKpi() {
        return kpiService.getKpiData();
    }
}