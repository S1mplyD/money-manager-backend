package com.lucabennati.moneymanagerbackend.service.transaction;

import com.lucabennati.moneymanagerbackend.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    ResponseEntity<String> save(Transaction transaction);

    ResponseEntity<List<Transaction>> getAllTransactions();

    ResponseEntity<Transaction> getTransactionById(String id);

    ResponseEntity<?> updateTransaction(String id, Transaction transaction);

    ResponseEntity<String> deleteTransaction(String id);

    ResponseEntity<List<Transaction>> getAllCashTransactions();

    ResponseEntity<List<Transaction>> getAllBankTransactions();

    ResponseEntity<List<Transaction>> getTransactionsByMethod(String method);

    ResponseEntity<List<Transaction>> getTransactionsByType(String type);

    ResponseEntity<String> addSalary(Transaction transaction);

    List<Transaction> getTransactionsByDate(LocalDate date);

}
