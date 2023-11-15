package com.lucabennati.moneymanagerbackend.utils;

import com.lucabennati.moneymanagerbackend.api.Analytics;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    Analytics analytics = new Analytics();
    @Scheduled(cron = "59 23 ? * * *")
    public void createAnalyticsCron(){
        analytics.createAnalytics();
    }
}
