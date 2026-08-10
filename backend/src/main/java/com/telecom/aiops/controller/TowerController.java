package com.telecom.aiops.controller;

import com.telecom.aiops.model.Tower;
import com.telecom.aiops.service.TowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Exposes tower status data over REST.
 * Delegates all business/data-generation logic to TowerService —
 * this class only handles HTTP concerns.
 */
@RestController
@RequestMapping("/api/towers")
@RequiredArgsConstructor
public class TowerController {

    private final TowerService towerService;

    @GetMapping
    public List<Tower> getAllTowers() {
        return towerService.getAllTowers();
    }
}