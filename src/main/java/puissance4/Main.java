package puissance4;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        State.SelectionStrategy strategy = null;
        while (strategy == null){
            System.out.println("Choix du critère de selection : ");
            System.out.println("Max : 0 ");
            System.out.println("robuste : 1 ");
            System.out.print("saisir votre choix :  ");
            int choice =  scanner.nextInt();
            if ( choice == 0 )
                strategy = State.SelectionStrategy.MAX;
            if ( choice == 1 )
                strategy = State.SelectionStrategy.ROBUSTE;
        }

        int indexPlayer = -1;
        while (indexPlayer != Constant.HUMAN_INDEX && indexPlayer != Constant.MACHINE_INDEX)
        {
            System.out.print("Qui commence ("+Constant.HUMAN_INDEX +" : humain"+" , "+Constant.MACHINE_INDEX+ " : machine) ?");
            indexPlayer =  scanner.nextInt();  // Read user input
        }

        State state = new State(indexPlayer,strategy);

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
                state.playWithMCTS(40);

                //System.out.print("Entrez le numero de la colonne : " );
                //int indexColum = scanner.nextInt();  // Read user input
                //state.playAnAction(new Action(indexColum));

            }
            state.displayGame();
        }
        System.out.println("final "+ state.isAFinalState()+ "   :    "+ state.isAFinalState().getValue());

    }
}