package terekhin.DB.Impl;

import terekhin.DB.Domain.ExchangeRate;
import terekhin.DB.IExchangeRates;
import terekhin.Services.Mapping.Currency;
import terekhin.Services.Mapping.Rate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExchangeRateImpl extends Repository<ExchangeRate> implements IExchangeRates {


    public ExchangeRateImpl() {
        INSERT_LINE = "insert into ExchangeRates " +
                "(CurrencyId,SaleNB,PurchaseNB,Sale,Purchase,Currency,ExchRateDay,ExchRateMonth,ExchRateYear,ExchDate) values (?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE id = LAST_INSERT_ID(id)";
    }

    @Override
    public List<Integer> getFinYearsList() throws Exception {
        try{
            connect();
            PreparedStatement prepStat = connection.prepareStatement("select er.ExchRateYear from ExchangeRates er GROUP BY er.ExchRateYear");
            ResultSet rs = prepStat.executeQuery();
            List<Integer> _lots = new ArrayList<Integer>();
            while(rs.next())
            {
                _lots.add(rs.getInt(1));
            }
            return _lots;
        }catch(SQLException e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());

        }finally {
            disconnect();
        }
    }

    @Override
    public void createFromMapping(int Id, Rate rateMapping) {

    }


    @Override
    protected void setId(ExchangeRate type, int id) {
        type.setId(id);
    }

    @Override
    protected void setInsertArguments(PreparedStatement statement, ExchangeRate type) throws SQLException {
        statement.setInt(1, type.getCurrencyId());
        statement.setDouble(2, type.getSaleNB());
        statement.setDouble(3, type.getPurchaseNB());
        statement.setDouble(4, type.getSale());
        statement.setDouble(5, type.getPurrchase());
        statement.setString(6, type.getCurrency());
        statement.setInt(7, type.getDay());
        statement.setInt(8, type.getMonth());
        statement.setInt(9, type.getYear());
        statement.setObject(10,type.getExchDate());
    }
}
