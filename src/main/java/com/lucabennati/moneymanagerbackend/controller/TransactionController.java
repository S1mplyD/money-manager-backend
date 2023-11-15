package com.lucabennati.moneymanagerbackend.controller;

import com.lucabennati.moneymanagerbackend.model.Transaction;
import com.lucabennati.moneymanagerbackend.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public String save(@RequestBody Transaction transaction){
        return transactionService.save(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transaction/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable String id){
       return transactionService.getTransactionById(id);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody Transaction transaction){
        return transactionService.updateTransaction(id,transaction);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String id){
        return transactionService.deleteTransaction(id);
    }

    @GetMapping("/cash")
    public ResponseEntity<?> getAllCashTransactions(){
        return transactionService.getAllCashTransactions();
    }

    @GetMapping("/bank")
    public ResponseEntity<?> getAllBankTransactions(){
        return transactionService.getAllBankTransactions();
    }

    @GetMapping("/method/{method}")
    public ResponseEntity<?> getTransactionsByMethod(@PathVariable String method){
        return transactionService.getTransactionsByMethod(method);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getTransactionsByType(@PathVariable String type){
        return transactionService.getTransactionsByType(type);
    }

    @PostMapping("/salary")
    public ResponseEntity<?> addSalary(@RequestBody Transaction transaction){
        return transactionService.addSalary(transaction);
    }

    public List<Transaction> getAllTransactionsByDate(LocalDate date){
        return transactionService.getTransactionsByDate(date);
    }
}
