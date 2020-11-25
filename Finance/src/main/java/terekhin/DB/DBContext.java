package terekhin.DB;

import terekhin.DB.Impl.BankImpl;
import terekhin.DB.Impl.CurrencyImpl;
import terekhin.DB.Impl.ExchangeRateImpl;
import terekhin.DB.Impl.FinStatsImpl;

public class DBContext {
    private static DBContext instance;
    public static DBContext getInstance(){
        if(instance==null)
            instance = new DBContext();

        return instance;
    }
    private DBContext(){}

    public IExchangeRates Rates(){return new ExchangeRateImpl();}
    public IBanks Banks(){return new BankImpl();}
    public ICurrencies Currency(){return  new CurrencyImpl();}
    public IFinStats FinStat(){return new FinStatsImpl();}


}
