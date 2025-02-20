package com.clienttest.citi_client.entities;

import java.util.List;

public class TaxEntity {
    
    private Long id;

    private Double taxRate; // Tax rule as 0.1 for 10%

    private String description;

    private List<TransactionEntity> transactions;

    public TaxEntity() {}

    public TaxEntity(Long id, Double taxRate, String description) {
        this.id = id;
        this.taxRate = taxRate;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getTaxRate() { return taxRate; }
    public void setTaxRate(Double taxRate) { this.taxRate = taxRate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<TransactionEntity> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionEntity> transactions) { this.transactions = transactions; }
}
