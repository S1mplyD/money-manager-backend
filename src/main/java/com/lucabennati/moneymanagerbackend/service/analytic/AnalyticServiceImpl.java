package com.lucabennati.moneymanagerbackend.service.analytic;

import com.lucabennati.moneymanagerbackend.model.Analytic;
import com.lucabennati.moneymanagerbackend.repository.AnalyticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticServiceImpl implements AnalyticService{

    @Autowired
    private AnalyticRepository analyticRepository;


    @Override
    public String save(Analytic analytic) {
        return analyticRepository.save(analytic).getId();
    }

    @Override
    public List<Analytic> getAnalyticsByType(String type) {
        return analyticRepository.findAnalyticsByTypeIs(type);
    }

    @Override
    public List<Analytic> getAllAnalytics() {
        return analyticRepository.findAll();
    }

    @Override
    public Analytic getLastAnalyticByType(String type) {
        return analyticRepository.findFirstByTypeOrderByDateDesc(type);
    }
}
