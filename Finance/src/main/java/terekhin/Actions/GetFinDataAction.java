package terekhin.Actions;

import terekhin.DB.DBContext;
import terekhin.Services.HttpTools.HttpTools;
import terekhin.States.BaseState;
import terekhin.States.StartState;

import java.util.Scanner;

public class GetFinDataAction extends BaseAction {
    @Override
    public void execute(Scanner scanner) {
        HttpTools _tools = new HttpTools();
        try {
            long start = System.currentTimeMillis();
            System.out.println("Data Processing...");
            _tools.getFinData();
            System.out.println("Data Collected...");
            long stop = System.currentTimeMillis();
            long time_diff = stop - start;
            this.showElapsedTime(time_diff);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BaseState getNextState() { return new StartState(); }

    @Override
    public String getItemDescript() {
        return "Get/Update Fin Data";
    }

    private void showElapsedTime(long time_diff)
    {
        System.out.print(time_diff + " mills ");
        System.out.print(((time_diff) / (1000 * 3600 * 24)) + " days ");
        System.out.print(((time_diff) / (1000 * 3600)) + " hours ");
        System.out.println(((time_diff) / (1000 * 60)) + " min ");
    }
}
