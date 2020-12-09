package com.terekhin.development.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromCurrency",orphanRemoval = true,cascade = CascadeType.DETACH)
    private List<CrossCourse> crossCoursesFrom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency",orphanRemoval = true)
    private List<Account> accounts;

    public Currency() {

        crossCoursesFrom = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<CrossCourse> getCrossCoursesFrom() {
        return crossCoursesFrom;
    }

    public void setCrossCoursesFrom(List<CrossCourse> crossCoursesFrom) {
        this.crossCoursesFrom = crossCoursesFrom;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
