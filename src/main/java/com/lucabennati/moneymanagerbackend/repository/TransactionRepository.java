package com.lucabennati.moneymanagerbackend.repository;

import com.lucabennati.moneymanagerbackend.model.Transaction;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findTransactionsByMethodIs(String method);

    List<Transaction> findTransactionsByTypeIs(String type);

    List<Transaction> findTransactionsByDateIs(LocalDate date);
}
