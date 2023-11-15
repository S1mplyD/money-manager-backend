package com.lucabennati.moneymanagerbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "analyticsdatas")
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Analytic {
    @Id
    private String id;
    private LocalDate date;
    private String type;
    private double value;

    public Analytic(String id, LocalDate date, String type, double value) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.value = value;
    }
}
