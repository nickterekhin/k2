package terekhin.Services.Mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    @JsonProperty("baseCurrency")
    public String base;
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("saleRateNB")
    public double salesNB;
    @JsonProperty("purchaseRateNB")
    public double purchaseNB;
    @JsonProperty("salesRate")
    public double sales;
    @JsonProperty("purchaseRate")
    public double purchase;
}