package terekhin.DB.Impl;

import terekhin.DB.Domain.FinStat;
import terekhin.DB.IFinStats;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinStatsImpl extends Repository<FinStat> implements IFinStats {



    @Override
    protected void setId(FinStat type, int id) {

    }

    @Override
    protected void setInsertArguments(PreparedStatement statement, FinStat type) throws SQLException {
    }

    @Override
    public List<FinStat> getByYear(int year) throws Exception {
        try{
            connect();
            PreparedStatement prepStat = connection.prepareStatement("select b.`Name` as BankName, c.`Name` as CurrencyCode, COUNT(er.id) as RatesQty," +
                    "SUM(er.SaleNB) / CASE WHEN COUNT(er.id)=0 THEN 1 ELSE COUNT(er.id) END as AveSaleNB," +
                    "SUM(er.PurchaseNB) / CASE WHEN COUNT(er.id)=0 THEN 1 ELSE COUNT(er.id) END as AvePurchaseNB," +
                    "MIN(er.SaleNB) as MinSaleNB," +
                    "MIN(er.PurchaseNB) as MinPurchaseNB," +
                    "MAX(er.SaleNB) as MaxSaleNB," +
                    "MAX(er.PurchaseNB) as MaxPurchaseNB," +
                    "er.Currency  from ExchangeRates er " +
                    "INNER JOIN Currencies c ON er.CurrencyId = c.id " +
                    "INNER JOIN banks b ON  c.BankId = b.id " +
                    "WHERE er.ExchRateYear = ? " +
                    "GROUP BY er.Currency,er.CurrencyId");
            prepStat.setInt(1, year);
            ResultSet rs = prepStat.executeQuery();
            List<FinStat> _finStatList = new ArrayList<FinStat>();
            while(rs.next())
            {
                FinStat finStat = new FinStat();
                finStat.setBankName(rs.getString("BankName"));
                finStat.setCurrencyCode(rs.getString("CurrencyCode"));
                finStat.setRatesQty(rs.getInt("RatesQty"));
                finStat.setAveSaleNB(rs.getDouble("AveSaleNB"));
                finStat.setAvePurchaseNB(rs.getDouble("AvePurchaseNB"));
                finStat.setMinSaleNB(rs.getDouble("MinSaleNB"));
                finStat.setMinPurchaseNB(rs.getDouble("MinPurchaseNB"));
                finStat.setMaxSaleNB(rs.getDouble("MaxSaleNB"));
                finStat.setMaxPurchaseNB(rs.getDouble("MaxPurchaseNB"));
                finStat.setCurrency(rs.getString("Currency"));
                _finStatList.add(finStat);
            }
            return _finStatList;

        }catch(SQLException e)
        {
            throw new Exception(e);
        }finally {
            disconnect();
        }
    }
}
