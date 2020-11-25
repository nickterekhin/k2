package terekhin.Services.HttpTools;


import org.joda.time.Days;
import org.joda.time.LocalDate;
import terekhin.DB.DBContext;
import terekhin.DB.Domain.ExchangeRate;
import terekhin.Services.HttpTools.Core.FinEngineThread;
import terekhin.Services.HttpTools.Core.HttpFinClient;
import terekhin.Services.Mapping.Currency;
import terekhin.Services.Mapping.Rate;


import java.util.concurrent.*;

public class HttpTools {

    private CyclicBarrier cb;

    public HttpTools() {
         cb = new CyclicBarrier(6);
    }

    public void getFinData(){

        LocalDate _today = new LocalDate();


        HttpFinClient _client = new HttpFinClient();
        CopyOnWriteArrayList<Currency> _currencyMapping = new CopyOnWriteArrayList<>();
        try {
            for (int i = 0; i < 1; i++) {

                LocalDate _startDate = new LocalDate(_today.minusYears(i).getYear(), 1, 1);
                LocalDate _endDate = i == 0 ? _today.minusYears(i) : new LocalDate(_today.minusYears(i).getYear(), 12, 31);

                for (int m = _startDate.getMonthOfYear() - 1; m <= _endDate.getMonthOfYear(); m++) {

                    LocalDate _cyleDate = _startDate.plusMonths(m);
                    int days = (i == 0 && m==_endDate.getDayOfMonth()) ? _endDate.getDayOfMonth() : Days.daysBetween(_cyleDate, _cyleDate.plusMonths(1)).getDays();

                    _currencyMapping.clear();

                    ExecutorService ex = Executors.newFixedThreadPool(5);
                    System.out.println(_cyleDate.toString("MMMM")+" Started Days:"+days);
                    int cycles = (int) Math.floor((days/5));
                    for(int c=0;c<cycles;c++)
                    {
                        for(int c_idx=c*5;c_idx<(c*5+5);c_idx++)
                        {

                            ex.submit(new FinEngineThread(cb,_currencyMapping,new LocalDate(_startDate.getYear(),_cyleDate.getMonthOfYear(),c_idx+1),_client));
                            Thread.sleep(3000);
                        }

                    }
                    cb.await();
                    System.out.println(_cyleDate.toString("MMMM")+" Finished");
                    terekhin.DB.Domain.Currency dbCurrency = new terekhin.DB.Domain.Currency();
                    terekhin.DB.Domain.Bank bank = new terekhin.DB.Domain.Bank();

                    for(Currency c: _currencyMapping)
                    {
                        bank.setName(c.bank);
                        dbCurrency.setCode(c.baseCode);
                        dbCurrency.setName(c.litCode);

                        DBContext.getInstance().Banks().create(bank);
                        dbCurrency.setBankId(bank.getId());
                        DBContext.getInstance().Currency().create(dbCurrency);

                        for(Rate r: c.rates)
                        {
                            if(r.currency==null) continue;
                            ExchangeRate _rate = new ExchangeRate();
                            _rate.setCurrencyId(dbCurrency.getId());
                            _rate.setSaleNB(r.salesNB);
                            _rate.setPurchaseNB(r.purchaseNB);
                            _rate.setSale(r.sales);
                            _rate.setPurrchase(r.purchase);
                            _rate.setCurrency(r.currency);
                            _rate.setDay(c.date.getDayOfMonth());
                            _rate.setMonth(c.date.getMonthValue());
                            _rate.setYear(c.date.getYear());
                            _rate.setExchDate(c.date);

                            DBContext.getInstance().Rates().create(_rate);
                        }
                    }

                    ex.shutdownNow();
                    cb.reset();
                    Thread.sleep(3000);


                }
            }
        }
        catch(Exception e)
            {
            e.printStackTrace();
            }
    }
    public void updateFinDate() throws Exception {

       /* CyclicBarrier cb = new CyclicBarrier(2);

        HttpFinClient _client = new HttpFinClient();
        CopyOnWriteArrayList<Currency> _currencyMapping = new CopyOnWriteArrayList<>();

        ExecutorService ex = Executors.newFixedThreadPool(1);
        ex.submit(new FinEngineThread(cb,_currencyMapping,new LocalDate(2020,11,20),_client));
        terekhin.DB.Domain.Currency dbCurrency = new terekhin.DB.Domain.Currency();
        terekhin.DB.Domain.Bank bank = new terekhin.DB.Domain.Bank();
        cb.await();
        ex.shutdownNow();
        for(Currency c: _currencyMapping)
        {
            bank.setName(c.bank);
            dbCurrency.setCode(c.baseCode);
            dbCurrency.setName(c.litCode);

            DBContext.getInstance().Banks().create(bank);
            dbCurrency.setBankId(bank.getId());
            DBContext.getInstance().Currency().create(dbCurrency);

            for(Rate r: c.rates)
            {
                if(r.currency==null) continue;
                ExchangeRate _rate = new ExchangeRate();
                _rate.setCurrencyId(dbCurrency.getId());
                _rate.setSaleNB(r.salesNB);
                _rate.setPurchaseNB(r.purchaseNB);
                _rate.setSale(r.sales);
                _rate.setPurrchase(r.purchase);
                _rate.setCurrency(r.currency);
                _rate.setDay(c.date.getDayOfMonth());
                _rate.setMonth(c.date.getMonthValue());
                _rate.setYear(c.date.getYear());
                _rate.setExchDate(c.date);

                DBContext.getInstance().Rates().create(_rate);
            }
        }


        Thread.sleep(3000);*/
    }

}
