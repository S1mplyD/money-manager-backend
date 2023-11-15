package com.lucabennati.moneymanagerbackend.service.analytic;

import com.lucabennati.moneymanagerbackend.model.Analytic;
import com.lucabennati.moneymanagerbackend.repository.AnalyticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnalyticServiceImpl implements AnalyticService {

    @Autowired
    private AnalyticRepository analyticRepository;


    @Override
    public ResponseEntity<String> save(Analytic analytic) {
        String id = analyticRepository.save(analytic).getId();
        return new ResponseEntity<>(id, !Objects.equals(id, "") ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Analytic>> getAnalyticsByType(String type) {
        List<Analytic> analyticList = analyticRepository.findAnalyticsByTypeIs(type);
        return new ResponseEntity<>(analyticList,analyticList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Analytic>> getAllAnalytics() {
        List<Analytic> analyticList = analyticRepository.findAll();
        return new ResponseEntity<>(analyticList, analyticList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public Analytic getLastAnalyticByType(String type) {
        return analyticRepository.findFirstByTypeOrderByDateDesc(type);
    }
}
