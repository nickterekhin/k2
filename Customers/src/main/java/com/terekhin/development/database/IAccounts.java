package com.terekhin.development.database;

import com.terekhin.development.domain.Account;
import com.terekhin.development.helpers.NotificationService;

import java.util.List;

public interface IAccounts extends IRepository<Account,Long>{
    List<Account> getAllByCustomerId(long cid) throws NotificationService;
}
