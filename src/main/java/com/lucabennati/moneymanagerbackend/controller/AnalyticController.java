package com.lucabennati.moneymanagerbackend.controller;

import com.lucabennati.moneymanagerbackend.model.Analytic;
import com.lucabennati.moneymanagerbackend.service.analytic.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/analytics")
public class AnalyticController {

    @Autowired
    private AnalyticService analyticService;

    @PostMapping
    public String save(@RequestBody Analytic analytic){
        return analyticService.save(analytic);
    }

    @GetMapping
    public List<Analytic> getAllAnalytics(){
        return analyticService.getAllAnalytics();
    }

    @GetMapping("/type/{type}")
    public List<Analytic> getAnalyticsByType(@PathVariable String type){
        return analyticService.getAnalyticsByType(type);
    }

    public Analytic getLastAnalyticByType(String type){
        return analyticService.getLastAnalyticByType(type);
    }
}
