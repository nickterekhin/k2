package terekhin.Services.HttpTools.Core;

import org.joda.time.LocalDate;
import terekhin.Services.Mapping.Currency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

public class FinEngineThread implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private LocalDate date;
    private CopyOnWriteArrayList<Currency> currencyMapping;
    private HttpFinClient client;

    public FinEngineThread(CyclicBarrier cyclicBarrier, CopyOnWriteArrayList<Currency> _currencyMapping, LocalDate _date, HttpFinClient client) {
        this.cyclicBarrier = cyclicBarrier;
        this.date = _date;
        this.currencyMapping = _currencyMapping;
        this.client = client;
    }

    @Override
    public void run() {

        try {
            this.currencyMapping.add(this.client.getExchangeRates(this.date));
        } catch (Exception e) {
            System.out.println("Error: "+this.date.toString("dd.MM.YYYY")+" Skipped -> "+e.getMessage());
        }
        try {
            this.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
