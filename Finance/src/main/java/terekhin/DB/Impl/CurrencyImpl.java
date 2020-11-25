package terekhin.DB.Impl;

import terekhin.DB.Domain.Currency;
import terekhin.DB.ICurrencies;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CurrencyImpl extends Repository<Currency> implements ICurrencies {

    public CurrencyImpl() {
        INSERT_LINE = "insert into Currencies (BankId,Code,Name) values (?,?,?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id),Code=VALUE(Code),Name=VALUE(Name)";
    }

    @Override
    protected void setId(Currency type, int id) {
        type.setId(id);
    }

    @Override
    protected void setInsertArguments(PreparedStatement statement, Currency type) throws SQLException {
        statement.setInt(1, type.getBankId());
        statement.setInt(2, type.getCode());
        statement.setString(3, type.getName());
    }
}
