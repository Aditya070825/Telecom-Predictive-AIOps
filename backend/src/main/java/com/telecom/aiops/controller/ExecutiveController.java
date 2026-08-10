package com.telecom.aiops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.aiops.dto.ExecutiveResponseDto;
import com.telecom.aiops.service.ExecutiveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExecutiveController {

    private final ExecutiveService executiveService;

    @GetMapping("/api/executive")
    public ExecutiveResponseDto getExecutive() {
        return executiveService.getExecutiveData();
    }
}