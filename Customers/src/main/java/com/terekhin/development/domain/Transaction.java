package com.terekhin.development.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Optional;
import java.util.Random;

@Entity
@Table(name="Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountId;
    @Access(AccessType.PROPERTY)
    private String number;
    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdAt;
    private double amount;
    private long typeId;
    private String Description;

    @ManyToOne
    @JoinColumn(name = "typeId", insertable = false,updatable = false)
    private TransactionType transactionType;
    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false,updatable = false)
    private Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getNumber() {
        return number==null?this.buildTransactionNumber(Optional.empty()):number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private String buildTransactionNumber(Optional<Integer> numbers)
    {
        numbers = numbers.isPresent()? numbers :Optional.of(7);
        char[] c = {'A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9'};
        StringBuilder sb = new StringBuilder();
        sb.append(this.getAccountId());
        sb.append("-");
        sb.append(this.getTypeId());
        sb.append("-");
        Random r = new Random();
        for(int i=0;i<numbers.get();i++)
            sb.append(c[r.nextInt(c.length-1)]);
        return sb.toString();
    }
}
