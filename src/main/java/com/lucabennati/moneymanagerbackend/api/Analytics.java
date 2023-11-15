package com.lucabennati.moneymanagerbackend.api;

import com.lucabennati.moneymanagerbackend.controller.AnalyticController;
import com.lucabennati.moneymanagerbackend.controller.TransactionController;
import com.lucabennati.moneymanagerbackend.model.Analytic;
import com.lucabennati.moneymanagerbackend.model.Transaction;
import org.bson.codecs.ObjectIdGenerator;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Analytics {
    public void createAnalytic(String type) {
        try {

            System.out.printf("[CREATING ANALYTIC FOR TYPE %s]", type.toUpperCase());
            LocalDate date = LocalDate.now();
            TransactionController transactionController = new TransactionController();
            AnalyticController analyticController = new AnalyticController();
            List<Transaction> transactions = transactionController.getAllTransactionsByDate(date);
            if (!transactions.isEmpty()) {
                double value = 0;
                for (Transaction transaction : transactions) {
                    if (Objects.equals(transaction.getType(), type)) {
                        value = value + transaction.getAmount();
                    }
                }
                Analytic previousAnalytic = analyticController.getLastAnalyticByType(type);
                Analytic newAnalytic = new Analytic(new ObjectIdGenerator().generate().toString(), date, type, !Objects.equals(previousAnalytic.getId(), "") ? (value + previousAnalytic.getValue()) : value);
                ResponseEntity<String> newAnalyticId = analyticController.save(newAnalytic);
                if(!Objects.equals(newAnalyticId.getBody(), "")){
                    throw new Exception(String.format("Cannot create new analytic for type %s in date %s",type,date));
                }
            } else {
                throw new Exception("Nothing found");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void createAnalytics(){
        createAnalytic("Pocket Money");
        createAnalytic("Bank Money");
        createAnalytic("Investments");
        createAnalytic("Personal Budget");
    }

}
