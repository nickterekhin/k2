package terekhin.Actions;

import terekhin.States.BaseState;

import java.util.Scanner;

public abstract class BaseAction {
    public abstract void execute(Scanner scanner);

    public abstract BaseState getNextState();
    public abstract String getItemDescript();
}
