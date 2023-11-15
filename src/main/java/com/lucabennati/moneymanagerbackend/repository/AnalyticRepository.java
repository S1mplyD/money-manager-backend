package com.lucabennati.moneymanagerbackend.repository;

import com.lucabennati.moneymanagerbackend.model.Analytic;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@Repository
public interface AnalyticRepository extends MongoRepository<Analytic, String> {

    List<Analytic> findAnalyticsByTypeIs(String type);

    Analytic findFirstByTypeOrderByDateDesc(String type);
}

