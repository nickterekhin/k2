package terekhin.DB;

import terekhin.DB.Domain.FinStat;

import java.util.List;

public interface IFinStats extends IRepository<FinStat> {

    List<FinStat> getByYear(int year) throws Exception;

}
