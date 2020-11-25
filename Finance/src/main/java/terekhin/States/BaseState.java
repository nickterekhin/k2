package terekhin.States;

import terekhin.Actions.BaseAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class BaseState {
    protected List<BaseAction> actionMap = new ArrayList<BaseAction>();

    public abstract void showState();

    public abstract void errMessage(String msg);

    protected void printMenu(){

        for(int i=0;i<actionMap.size();i++)
        {
            BaseAction action = actionMap.get(i);
            System.out.println(i+" - "+action.getItemDescript());
        }
    }

    public BaseAction getAction(Scanner scanner) {
        while (true) {
            printMenu();
            System.out.print("Please select action number: ");
            int actionNumber = scanner.nextInt();
            BaseAction action = actionMap.get(actionNumber);
            if(action != null) {
                return action;
            }
        }
    }
}
