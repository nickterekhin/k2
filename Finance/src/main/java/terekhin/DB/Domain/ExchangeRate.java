package terekhin.DB.Domain;

import java.time.LocalDate;

public class ExchangeRate {

    private int id;
    private int CurrencyId;
    private double saleNB;
    private double purchaseNB;
    private double sale;
    private double purrchase;
    private String Currency;
    private int day;
    private int month;
    private int year;
    private LocalDate exchDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        CurrencyId = currencyId;
    }

    public double getSaleNB() {
        return saleNB;
    }

    public void setSaleNB(double saleNB) {
        this.saleNB = saleNB;
    }

    public double getPurchaseNB() {
        return purchaseNB;
    }

    public void setPurchaseNB(double purchaseNB) {
        this.purchaseNB = purchaseNB;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public double getPurrchase() {
        return purrchase;
    }

    public void setPurrchase(double purrchase) {
        this.purrchase = purrchase;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getExchDate() {
        return exchDate;
    }

    public void setExchDate(LocalDate exchDate) {
        this.exchDate = exchDate;
    }
}
