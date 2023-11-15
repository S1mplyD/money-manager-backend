package com.lucabennati.moneymanagerbackend.service.transaction;

import com.lucabennati.moneymanagerbackend.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    String save(Transaction transaction);

    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(String id);

    ResponseEntity<?> updateTransaction(String id, Transaction transaction);

    ResponseEntity<?> deleteTransaction(String id);

    ResponseEntity<?> getAllCashTransactions();

    ResponseEntity<?> getAllBankTransactions();

    ResponseEntity<?> getTransactionsByMethod(String method);

    ResponseEntity<?> getTransactionsByType(String type);

    ResponseEntity<?> addSalary(Transaction transaction);

    List<Transaction> getTransactionsByDate(LocalDate date);

}
