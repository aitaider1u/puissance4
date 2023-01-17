package puissance4;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        State state = new State(Constant.HUMAN_INDEX);
        state.initialState();

        //Node node = Node.racineNode(state);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Random random = new Random();
        while (state.isAFinalState() == State.FinalState.NON){
            if(state.getPlayer() == Constant.HUMAN_INDEX){
                System.out.println(state.isAFinalState());
                System.out.println("l'humain joue ---> ");
                System.out.print("Entrez le numero de la colonne : " );
                int indexColum = myObj.nextInt();  // Read user input
                state.playAnAction(new Action(indexColum));
            }else if (state.getPlayer() == Constant.MACHINE_INDEX){
                System.out.println("La Machine joue ---> ");
                ArrayList<Action> actions = state.possibleAction();
                int randChoice = random.nextInt(actions.size());
                state.playAnAction(actions.get(randChoice));
            }
            state.displayGame();
        }

    }
}