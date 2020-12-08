package com.terekhin.development.domain;

import sun.jvm.hotspot.jdi.ArrayReferenceImpl;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="FirstName",nullable = false)
    private String firstName;
    @Column(name="LastName",nullable = false)
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Customer() {
        accounts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
