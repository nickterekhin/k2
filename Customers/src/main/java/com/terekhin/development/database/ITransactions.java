package com.terekhin.development.database;

import com.terekhin.development.domain.Transaction;
import com.terekhin.development.helpers.NotificationService;

public interface ITransactions extends IRepository<Transaction,Long>{
    Object getAllByAccountId(long account_id) throws NotificationService;
}
