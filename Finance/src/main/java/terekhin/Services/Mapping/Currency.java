package terekhin.Services.Mapping;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonProperty("date")
    public LocalDate date;
    @JsonProperty("bank")
    public String bank;
    @JsonProperty("baseCurrency")
    public int baseCode;
    @JsonProperty("baseCurrencyLit")
    public String litCode;
    @JsonProperty("exchangeRate")
    public List<Rate> rates;
}
