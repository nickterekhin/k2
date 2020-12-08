package com.terekhin.development.domain;

import javax.persistence.*;

@Entity
@Table(name="Crosses")
public class CrossCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long fromCurrencyId;
    private long toCurrencyId;
    private double amount;

    @ManyToOne
    @JoinColumn(name="fromCurrencyId", nullable=false,insertable = false,updatable = false)
    private Currency fromCurrency;

    @ManyToOne
    @JoinColumn(name="toCurrencyId", nullable=false,insertable = false,updatable = false)
    private Currency toCurrency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromCurrencyId() {
        return fromCurrencyId;
    }

    public void setFromCurrencyId(long fromCurrencyId) {
        this.fromCurrencyId = fromCurrencyId;
    }

    public long getToCurrencyId() {
        return toCurrencyId;
    }

    public void setToCurrencyId(long toCurrencyId) {
        this.toCurrencyId = toCurrencyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }
}
