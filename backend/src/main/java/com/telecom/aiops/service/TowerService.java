package com.telecom.aiops.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.telecom.aiops.model.Tower;
import com.telecom.aiops.model.TowerStatus;
import static com.telecom.aiops.utils.DataGeneratorUtils.formatTowerId;
import static com.telecom.aiops.utils.DataGeneratorUtils.randomRegion;
import static com.telecom.aiops.utils.DataGeneratorUtils.round;

/**
 * Generates realistic telecom tower data in memory.
 * This will later be replaced/backed by a PostgreSQL repository,
 * without requiring any change to the Controller layer.
 */
@Service
public class TowerService {

    private final Random random = new Random();

    private static final String[] CITIES = {"Chennai", "Bengaluru", "Hyderabad", "Mumbai", "Delhi", "Pune"};

    /**
     * Returns a freshly generated list of towers with realistic, randomized
     * operational data. Called on every request since we have no persistence yet.
     */
    public List<Tower> getAllTowers() {
        List<Tower> towers = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            towers.add(generateTower(i));
        }

        return towers;
    }

    private Tower generateTower(int index) {
        String city = CITIES[random.nextInt(CITIES.length)];

        return Tower.builder()
                .towerId(formatTowerId(index))
                .towerName(city + "-Tower-" + index)
                .region(randomRegion(random))
                .city(city)
                .status(generateWeightedStatus())
                .signalStrengthDbm(round(-50 - (random.nextDouble() * 60)))
                .connectedUsers(random.nextInt(500))
                .bandwidthUsageMbps(round(random.nextDouble() * 1000))
                .lastUpdated(LocalDateTime.now())
                .build();
    }

    /**
     * Weighted status generation so most towers are ACTIVE,
     * mirroring a realistic, mostly-healthy telecom network.
     */
    private TowerStatus generateWeightedStatus() {
        int chance = random.nextInt(100);
        if (chance < 75) return TowerStatus.ACTIVE;
        if (chance < 90) return TowerStatus.DEGRADED;
        if (chance < 97) return TowerStatus.MAINTENANCE;
        return TowerStatus.DOWN;
    }
}