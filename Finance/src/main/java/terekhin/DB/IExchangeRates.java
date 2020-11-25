package terekhin.DB;

import terekhin.DB.Domain.ExchangeRate;
import terekhin.Services.Mapping.Rate;

import java.util.List;

public interface IExchangeRates extends IRepository<ExchangeRate> {

    List<Integer> getFinYearsList() throws Exception;

    void createFromMapping(int Id,Rate rateMapping);


}
