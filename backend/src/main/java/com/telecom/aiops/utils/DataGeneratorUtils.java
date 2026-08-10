package com.telecom.aiops.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Shared helpers for generating realistic dummy telecom data
 * across all services (Tower, KPI, Incident, Risk, Forecast,
 * Automation, Executive).
 */
public class DataGeneratorUtils {

    public static final String[] REGIONS = {"North", "South", "East", "West", "Central"};

    public static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm");

    private DataGeneratorUtils() {
        // utility class, no instances
    }

    /** Rounds a double to 2 decimal places. */
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /** Formats a tower ID like "TWR-001". */
    public static String formatTowerId(int index) {
        return "TWR-" + String.format("%03d", index);
    }

    /** Picks a random region from REGIONS. */
    public static String randomRegion(Random random) {
        return REGIONS[random.nextInt(REGIONS.length)];
    }

    /** Returns a formatted "HH:mm" timestamp offset backward from now by a random number of minutes (0 to maxMinutesAgo). */
    public static String randomRecentTimestamp(Random random, int maxMinutesAgo) {
        return LocalTime.now().minusMinutes(random.nextInt(maxMinutesAgo)).format(TIME_FMT);
    }

    /** Returns a list of 12 hourly "HH:mm" timestamps ending near now, starting 11 hours ago. */
    public static String hourlyTimestamp(int hourOffset) {
        return LocalTime.now().minusHours(11).plusHours(hourOffset).format(TIME_FMT);
    }
}