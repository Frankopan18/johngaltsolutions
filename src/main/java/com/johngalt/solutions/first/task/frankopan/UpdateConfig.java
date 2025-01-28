package com.johngalt.solutions.first.task.frankopan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UpdateConfig {

    @Value("${update.interval.minutes}")
    private int updateIntervalMinutes;

    public int getUpdateIntervalMinutes() {
        return updateIntervalMinutes;
    }
}
