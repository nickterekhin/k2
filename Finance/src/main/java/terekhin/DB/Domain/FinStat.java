package terekhin.DB.Domain;

public class FinStat {
    private String BankName;
    private String CurrencyCode;
    private int ratesQty;
    private double aveSaleNB;
    private double avePurchaseNB;
    private double minSaleNB;
    private double minPurchaseNB;
    private double maxSaleNB;
    private double maxPurchaseNB;
    private String Currency;

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public int getRatesQty() {
        return ratesQty;
    }

    public void setRatesQty(int ratesQty) {
        this.ratesQty = ratesQty;
    }

    public double getAveSaleNB() {
        return aveSaleNB;
    }

    public void setAveSaleNB(double aveSaleNB) {
        this.aveSaleNB = aveSaleNB;
    }

    public double getAvePurchaseNB() {
        return avePurchaseNB;
    }

    public void setAvePurchaseNB(double avePurchaseNB) {
        this.avePurchaseNB = avePurchaseNB;
    }

    public double getMinSaleNB() {
        return minSaleNB;
    }

    public void setMinSaleNB(double minSaleNB) {
        this.minSaleNB = minSaleNB;
    }

    public double getMinPurchaseNB() {
        return minPurchaseNB;
    }

    public void setMinPurchaseNB(double minPurchaseNB) {
        this.minPurchaseNB = minPurchaseNB;
    }

    public double getMaxSaleNB() {
        return maxSaleNB;
    }

    public void setMaxSaleNB(double maxSaleNB) {
        this.maxSaleNB = maxSaleNB;
    }

    public double getMaxPurchaseNB() {
        return maxPurchaseNB;
    }

    public void setMaxPurchaseNB(double maxPurchaseNB) {
        this.maxPurchaseNB = maxPurchaseNB;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
