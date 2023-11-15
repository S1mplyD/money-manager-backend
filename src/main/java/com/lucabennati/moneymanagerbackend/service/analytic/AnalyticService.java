package com.lucabennati.moneymanagerbackend.service.analytic;

import com.lucabennati.moneymanagerbackend.model.Analytic;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnalyticService {
    String save(Analytic analytic);

    List<Analytic> getAnalyticsByType(String type);

    List<Analytic> getAllAnalytics();


    Analytic getLastAnalyticByType(String type);
}
