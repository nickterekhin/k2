package terekhin.States;

import terekhin.Actions.BaseAction;

import java.util.Scanner;

public class ExitState extends BaseState {
    @Override
    public void showState() {
        System.out.println("Goodbye!");
    }

    @Override
    public void errMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public BaseAction getAction(Scanner scanner) {
        return null;
    }
}
