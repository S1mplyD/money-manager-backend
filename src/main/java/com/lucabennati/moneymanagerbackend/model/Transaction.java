package com.lucabennati.moneymanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "transactiondatas")
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {
    @Id
    private String id;
    private double amount;
    private LocalDate date;
    private String motivation;
    private String notes;
    private String category;
    private String type;
    private String method;

    public Transaction(String id, double amount, LocalDate date, String motivation, String notes, String category, String type, String method) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.motivation = motivation;
        this.notes = notes;
        this.category = category;
        this.type = type;
        this.method = method;
    }
}


