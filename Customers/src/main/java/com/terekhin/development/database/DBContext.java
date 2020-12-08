package com.terekhin.development.database;

import com.terekhin.development.database.impl.AccountsImpl;
import com.terekhin.development.database.impl.CrossCoursesImpl;
import com.terekhin.development.database.impl.CurrenciesImpl;
import com.terekhin.development.database.impl.CustomersImpl;

public class DBContext {
    public DBContext(){}

    public ICustomers Customers(){return new CustomersImpl();}
    public ICurrencies Currency(){return new CurrenciesImpl();}
    public IAccounts Accounts(){return new AccountsImpl();}
    public ICrossCourses Crosses(){return new CrossCoursesImpl();}


}
