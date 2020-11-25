package terekhin.DB.Impl;

import terekhin.DB.Domain.Bank;
import terekhin.DB.IBanks;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankImpl extends Repository<Bank> implements IBanks {

    public BankImpl() {
        INSERT_LINE = "insert into Banks (Name) values (?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id),Name=VALUE(Name)";
    }

    @Override
    protected void setId(Bank type, int id) {
        type.setId(id);
    }

    @Override
    protected void setInsertArguments(PreparedStatement statement, Bank type) throws SQLException {
        statement.setString(1, type.getName());
    }
}
