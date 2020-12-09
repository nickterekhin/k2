package com.terekhin.development.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="TransactionTypes")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType",orphanRemoval = true)
    private List<Transaction> transactions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
