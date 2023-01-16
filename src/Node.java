import java.util.ArrayList;

public class Node {
    private int player ; // joueur qui a joué pour arriver ici
    private Action action ; // coup joué par ce joueur pour arriver ici

    private State state ; // // etat du jeu actuel
    private Node parent;
    private ArrayList<Node> children = new ArrayList<>();

    private int nbVictories;
    private int nbSimulations;


    public Node(Node parent, Action action) {

        if(parent != null && action != null){
            this.state = parent.getState().cloneState();
            this.action = action;
            this.state.playAnAction(action);
            this.player = parent.otherPlayer();
        }else {
            this.state = null;
            this.action = action;
            this.player = 0;
        }

        this.parent = parent;
        this.nbVictories = 0;
        this.nbSimulations = 0;
    }


    public Node addChild(Action action){
        Node child = new Node(this.parent,action);
        this.children.add(child);
        return child;
    }

    public int getNbChildren(){
        return this.children.size();
    }

    public State getState() {
        return state;
    }

    public int  otherPlayer(){
        return  1-this.player;
    }

}
