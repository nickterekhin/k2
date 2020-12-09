package com.terekhin.development.database;

import com.terekhin.development.database.impl.*;

public class DBContext {
    public DBContext(){}

    public ICustomers Customers(){return new CustomersImpl();}
    public ICurrencies Currency(){return new CurrenciesImpl();}
    public IAccounts Accounts(){return new AccountsImpl();}
    public ICrossCourses Crosses(){return new CrossCoursesImpl();}
    public ITransactions Transactions(){return new TransactionsImpl();}
    public ITransactionTypes TransactionTypes(){return new TransactionTypesImpl();}


}
