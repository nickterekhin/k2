package terekhin.Actions;


import terekhin.DB.DBContext;
import terekhin.DB.Domain.FinStat;
import terekhin.States.BaseState;
import terekhin.States.StartState;

import java.util.List;
import java.util.Scanner;

public class ShowFinStatAction extends BaseAction {

    private int finYear;
    public ShowFinStatAction(int finYear) {
        this.finYear = finYear;
    }

    @Override
    public void execute(Scanner scanner) {

        try {
            List<FinStat> finStatList = DBContext.getInstance().FinStat().getByYear(this.finYear);
            if(!finStatList.isEmpty()) System.out.println("Stat for "+this.finYear+" Currency: "+finStatList.get(0).getCurrencyCode());
            System.out.format("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
            System.out.format("| %-6s | %-12s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n","Bank","Currency","Ave Sale","Ave Purchase","Min Sale","Min Purchase","Max Sale","Max Purchase");

            System.out.format("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
            if(finStatList.isEmpty()) System.out.println("No data to show");
            for(FinStat finStat:finStatList)
            {
                System.out.format("| %-6s | %-12s | %-20f | %-20f | %-20f | %-20f | %-20f | %-20f |%n",finStat.getBankName(),
                        finStat.getCurrency(),
                        finStat.getAveSaleNB(),
                        finStat.getAvePurchaseNB(),
                        finStat.getMinSaleNB(),
                        finStat.getMinPurchaseNB(),
                        finStat.getMaxSaleNB(),
                        finStat.getMaxPurchaseNB() );
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.format("+------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
    }

    @Override
    public BaseState getNextState() {
        return new StartState();
    }

    @Override
    public String getItemDescript() {
        return "Show Stat for "+this.finYear+" Year";
    }
}
