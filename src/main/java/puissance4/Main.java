package puissance4;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        int indexPlayer = -1;
        while (indexPlayer != Constant.HUMAN_INDEX && indexPlayer != Constant.MACHINE_INDEX)
        {
            System.out.print("Qui commence ("+Constant.HUMAN_INDEX +" : humain"+" , "+Constant.MACHINE_INDEX+ " : machine) ?");
            indexPlayer =  scanner.nextInt();  // Read user input
        }

        State state = new State(indexPlayer);
        state.initialState();

        Random random = new Random();
        System.out.println(state);

        while (state.isAFinalState() == State.FinalState.NON){
            if(state.getPlayer() == Constant.HUMAN_INDEX){
                System.out.println("\n/*----------------HUMAIN----------------*/ \n" );
                System.out.print("Entrez le numero de la colonne : " );
                int indexColum = scanner.nextInt();  // Read user input
                state.playAnAction(new Action(indexColum));
            }else if (state.getPlayer() == Constant.MACHINE_INDEX){
                System.out.println("\n/*----------------MACHINE----------------*/ \n" );
                state.playWithMCTS(150);
            }
            state.displayGame();
        }
        System.out.println("final "+ state.isAFinalState()+ "   :    "+ state.isAFinalState().getValue());

    }
}