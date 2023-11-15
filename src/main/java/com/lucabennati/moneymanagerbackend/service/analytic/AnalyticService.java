package com.lucabennati.moneymanagerbackend.service.analytic;

import com.lucabennati.moneymanagerbackend.model.Analytic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnalyticService {
    ResponseEntity<String> save(Analytic analytic);

    ResponseEntity<List<Analytic>> getAnalyticsByType(String type);

    ResponseEntity<List<Analytic>> getAllAnalytics();


    Analytic getLastAnalyticByType(String type);
}
