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
            System.out.print("Choix du critère de selection (Max : 0, Robuste : 1) ?");
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
                state.playWithMCTS(150);
            }
            state.displayGame();
        }

        System.out.println("\n\n*                                  *");
        System.out.println("*        Fin de partie             *");
        System.out.println("*                                  *");
        System.out.println();

        if(state.isAFinalState() == State.FinalState.HUMAN_WIN){
            System.out.println("   Bravo ! vous avez gagné...   ");
        }

        if(state.isAFinalState() == State.FinalState.MACHINE_WIN){
            System.out.println("   Perdu ! La machine a gagné...   ");
        }
        if(state.isAFinalState() == State.FinalState.DRAW_GAME){
            System.out.println("   Match nul   ");
        }
        System.out.println("*----------------------------------*");

    }
}