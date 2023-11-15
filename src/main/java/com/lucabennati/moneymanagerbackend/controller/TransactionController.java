package com.lucabennati.moneymanagerbackend.controller;

import com.lucabennati.moneymanagerbackend.model.Transaction;
import com.lucabennati.moneymanagerbackend.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Transaction transaction){
        return transactionService.save(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id){
       return transactionService.getTransactionById(id);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody Transaction transaction){
        return transactionService.updateTransaction(id,transaction);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id){
        return transactionService.deleteTransaction(id);
    }

    @GetMapping("/cash")
    public ResponseEntity<List<Transaction>> getAllCashTransactions(){
        return transactionService.getAllCashTransactions();
    }

    @GetMapping("/bank")
    public ResponseEntity<List<Transaction>> getAllBankTransactions(){
        return transactionService.getAllBankTransactions();
    }

    @GetMapping("/method/{method}")
    public ResponseEntity<List<Transaction>> getTransactionsByMethod(@PathVariable String method){
        return transactionService.getTransactionsByMethod(method);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Transaction>> getTransactionsByType(@PathVariable String type){
        return transactionService.getTransactionsByType(type);
    }

    @PostMapping("/salary")
    public ResponseEntity<String> addSalary(@RequestBody Transaction transaction){
        return transactionService.addSalary(transaction);
    }

    public List<Transaction> getAllTransactionsByDate(LocalDate date){
        return transactionService.getTransactionsByDate(date);
    }
}
