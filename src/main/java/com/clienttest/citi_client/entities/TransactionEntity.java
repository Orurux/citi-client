package com.clienttest.citi_client.entities;

import java.time.LocalDate;

public class TransactionEntity {

    private Long id;

    private Double amount;

    private LocalDate date;

    private TaxEntity taxEntity;

    private Double calculatedTax; // Guardamos el impuesto calculado para trazabilidad

    public TransactionEntity() {}

    public TransactionEntity(Long id, Double amount, LocalDate date, TaxEntity taxEntity, Double calculatedTax) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.taxEntity = taxEntity;
        this.calculatedTax = calculatedTax;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public TaxEntity getTaxEntity() { return taxEntity; }
    public void setTaxEntity(TaxEntity taxEntity) { this.taxEntity = taxEntity; }

    public Double getCalculatedTax() { return calculatedTax; }
    public void setCalculatedTax(Double calculatedTax) { this.calculatedTax = calculatedTax; }
}
