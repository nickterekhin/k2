package terekhin.States;

import org.joda.time.DateTime;
import terekhin.Actions.ExitAction;
import terekhin.Actions.GetFinDataAction;
import terekhin.Actions.ShowFinStatAction;
import terekhin.DB.DBContext;

import java.util.ArrayList;
import java.util.List;

public class StartState extends BaseState {

    private List<Integer> yearsList;
    public StartState() {

        try {
            yearsList = DBContext.getInstance().Rates().getFinYearsList();
        } catch (Exception e) {
            yearsList = new ArrayList<>();
        }


    }

    @Override
    public void showState() {
        actionMap.clear();
        actionMap.add(new GetFinDataAction());

        if(!yearsList.isEmpty())
            for (Integer year: this.yearsList) {
                actionMap.add(new ShowFinStatAction(year));
            }
        actionMap.add(new ExitAction());
    }

    @Override
    public void errMessage(String msg) {

    }
}
