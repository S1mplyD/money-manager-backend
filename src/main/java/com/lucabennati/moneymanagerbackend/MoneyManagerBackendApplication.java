package com.lucabennati.moneymanagerbackend;

import com.lucabennati.moneymanagerbackend.api.Analytics;
import com.lucabennati.moneymanagerbackend.controller.AnalyticController;
import com.lucabennati.moneymanagerbackend.repository.TransactionRepository;
import com.lucabennati.moneymanagerbackend.utils.ScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;

@SpringBootApplication
@EnableMongoRepositories
public class MoneyManagerBackendApplication  {
    @Autowired
    TransactionRepository transactionRepository;

    ScheduleTask scheduler = new ScheduleTask();

    public static void main(String[] args) {
        SpringApplication.run(MoneyManagerBackendApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setScheduler(){
        scheduler.createAnalyticsCron();
    }

}
