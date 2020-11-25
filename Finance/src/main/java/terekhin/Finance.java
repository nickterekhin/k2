package terekhin;

import terekhin.Actions.BaseAction;
import terekhin.States.BaseState;
import terekhin.States.ExitState;
import terekhin.States.StartState;

import java.util.Scanner;

public class Finance {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BaseState state = new StartState();

        while(!(state instanceof ExitState)) {
            System.out.println("-------------------------Info-------------------------");
            state.showState();
            System.out.println("------------------------------------------------------");
            BaseAction action = state.getAction(scanner);
            System.out.println("-------------------------Action------------------------");
            action.execute(scanner);

            state = action.getNextState();
            System.out.println("-------------------------------------------------------");

        }

        state.showState();
    }
}
