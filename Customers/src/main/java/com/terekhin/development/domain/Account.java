package com.terekhin.development.domain;

import javax.persistence.*;
import java.util.Optional;
import java.util.Random;

@Entity
@Table(name="Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Access(AccessType.PROPERTY)
    private String number;
    private long customerId;
    private long currencyId;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false,updatable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "currencyId", insertable = false,updatable = false)
    private Currency currency;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getNumber() {
        return number==null?this.buildAccountNumber(Optional.empty()):number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String buildAccountNumber(Optional<Integer> numbers)
    {
        numbers = numbers.isPresent()? numbers :Optional.of(7);
        char[] c = {'A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9'};
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCustomerId());
        sb.append("-");
        sb.append(this.getCurrencyId());
        sb.append("-");
        Random r = new Random();
        for(int i=0;i<numbers.get();i++)
            sb.append(c[r.nextInt(c.length-1)]);
        return sb.toString();
    }
}
