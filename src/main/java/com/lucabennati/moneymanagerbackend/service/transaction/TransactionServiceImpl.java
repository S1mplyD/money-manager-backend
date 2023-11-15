package com.lucabennati.moneymanagerbackend.service.transaction;

import com.lucabennati.moneymanagerbackend.model.Transaction;
import com.lucabennati.moneymanagerbackend.repository.TransactionRepository;
import org.bson.codecs.ObjectIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String save(Transaction transaction) {
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @Override
    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public ResponseEntity<?> updateTransaction(String id, Transaction transaction) {
        Optional<Transaction> transaction1 = transactionRepository.findById(id);
        if (transaction1.isPresent()) {
            Transaction toSave = transaction1.get();
            toSave.setAmount(transaction.getAmount() != 0 ? transaction.getAmount() : toSave.getAmount());
            toSave.setDate(transaction.getDate() != null ? transaction.getDate() : toSave.getDate());
            toSave.setMotivation(transaction.getMotivation() != null ? transaction.getMotivation() : toSave.getMotivation());
            toSave.setNotes(transaction.getNotes() != null ? transaction.getNotes() : toSave.getNotes());
            toSave.setCategory(transaction.getCategory() != null ? transaction.getCategory() : toSave.getCategory());
            toSave.setType(transaction.getType() != null ? transaction.getType() : toSave.getType());
            toSave.setMethod(transaction.getMethod() != null ? transaction.getMethod() : toSave.getMethod());
            transactionRepository.save(toSave);
            return new ResponseEntity<>(toSave, HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteTransaction(String id) {
        transactionRepository.deleteById(id);
        return new ResponseEntity<>("Transaction deleted", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllCashTransactions() {
        List<Transaction> transactions = transactionRepository.findTransactionsByMethodIs("Cash");
        return new ResponseEntity<>(transactionRepository.findTransactionsByMethodIs("Cash"), transactions.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllBankTransactions() {
        List<Transaction> transactions = transactionRepository.findTransactionsByMethodIs("Bank");
        return new ResponseEntity<>(transactionRepository.findTransactionsByMethodIs("Bank"), transactions.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTransactionsByMethod(String method) {
        List<Transaction> transactions = transactionRepository.findTransactionsByMethodIs(method);
        return new ResponseEntity<>(transactionRepository.findTransactionsByMethodIs(method), transactions.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTransactionsByType(String type) {
        List<Transaction> transactions = transactionRepository.findTransactionsByTypeIs(type);
        return new ResponseEntity<>(transactionRepository.findTransactionsByTypeIs(type), transactions.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addSalary(Transaction transaction) {
        double personalAmount = (transaction.getAmount() * 20) / 100;
        double salaryAmount = transaction.getAmount() - personalAmount;
        transaction.setAmount(salaryAmount);
        Transaction salary = transactionRepository.save(transaction);
        Transaction newPersonal = new Transaction(new ObjectIdGenerator().generate().toString(),personalAmount, transaction.getDate(), "Added from salary", "20% of total salary", "", "Personal Budget", "Bank");
        Transaction personal = transactionRepository.save(newPersonal);
        if (!(salary.getId().isEmpty() && personal.getId().isEmpty())) {
            return new ResponseEntity<>("Salary added correctly", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Cannot add salary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findTransactionsByDateIs(date);
    }

}
