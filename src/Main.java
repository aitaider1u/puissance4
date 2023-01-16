import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        State state = new State(Constant.HUMAN_INDEX);
        state.initialState();
        State clone = state.cloneState();
        state.playAnAction(new Action(3));

        Node node = Node.racineNode(state);
        System.out.println(node);


        for (Action action: node.getState().possibleAction()) {
            System.out.println(action.getColumn());
            Node child = node.addChild(action);
            System.out.println(child);

        }
        //System.out.println("\n\n\n\n apres");
        //System.out.println(node);

        /*
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Random random = new Random();
        while (state.isAFinalState() == State.FinalState.NON){
            if(state.getPlayer() == Constant.HUMAN_INDEX){
                System.out.println(state.isAFinalState());
                System.out.println("l'humain joue ---> ");
                System.out.print("Enter numero de la colonne : " );
                int indexColum = myObj.nextInt();  // Read user input
                state.playAnAction(new Action(indexColum));
            }else if (state.getPlayer() == Constant.MACHINE_INDEX){
                System.out.println("La Machine joue ---> ");
                ArrayList<Action> actions = state.possibleAction();
                int randChoice = random.nextInt(actions.size());
                state.playAnAction(actions.get(randChoice));
            }
            state.displayGame();
        }*/

    }
}