package terekhin.Actions;

import terekhin.States.BaseState;
import terekhin.States.ExitState;

import java.util.Scanner;

public class ExitAction extends BaseAction {
    @Override
    public void execute(Scanner scanner) {

    }

    @Override
    public BaseState getNextState() {
        return new ExitState();
    }

    @Override
    public String getItemDescript() {
        return "Exit";
    }
}
