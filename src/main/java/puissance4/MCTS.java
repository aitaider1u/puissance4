package puissance4;

import java.util.ArrayList;
import java.util.Random;

public class MCTS {

    private Node racineNode;
    private static final double C = Math.sqrt(2);

    public MCTS(Node racineNode) {
        this.racineNode = racineNode;
    }


    public Node selection(){

        return selection_aux(this.racineNode);
    }

    private Node selection_aux (Node node){
        if (node.getState().isAFinalState() != State.FinalState.NON )
            return  node;

        if (node.getNbChildren() == 0 )
            return  node;

        Node selectedNode = null;
        double maxUCBValue = -Double.MIN_VALUE;
        for (Node n : node.getChildren()){
            double nUCBValue = getUCBValue(n);
            if (maxUCBValue <  nUCBValue){
                maxUCBValue = nUCBValue;
                selectedNode = n;
            }
        }

        if(selectedNode == null)
            return node;

        return this.selection_aux(selectedNode);
    }

    public Node expansion(Node node){
        Random random = new Random();
        if (node.getState().isAFinalState() != State.FinalState.NON )
            return  node;

        ArrayList<Action> actions = node.getState().possibleAction();
        for (Action a:actions) {
            node.addChild(a);
        }

        return node.getChildren().get(random.nextInt(node.getNbChildren()));   // qui sera similier
    }

    public double simulation(Node node){
        State currentState = node.getState().cloneState();

        Random random = new Random();
        while (currentState.isAFinalState() == State.FinalState.NON){
            ArrayList<Action> actions = currentState.possibleAction();
            int index = random.nextInt(actions.size());
            currentState.playAnAction(actions.get(index));
        }

        return currentState.isAFinalState().getValue();
    }

    public void rollout(Node node,double value){
        while (node != null){
            node.updateNbSimulations();
            node.updateNbVictories(value);
            node = node.getParent();
        }
    }

    private double getUCBValue(Node node){
        Node parent = node.getParent();
        if (node.getNbSimulations() == 0 )
            return Double.MAX_VALUE;
        double exploitation = ((node.getPlayer()==Constant.HUMAN_INDEX)? -1 : 1) *(node.getNbVictories()/node.getNbSimulations());
        double exploration  = C*Math.sqrt( Math.log(parent.getNbSimulations())/node.getNbSimulations());
        return exploitation+exploration;
    }


    public Action bestActionToPlay(){
        Node selectedNode = null;
        double valueMax = -Double.MIN_VALUE;
        for (Node n : racineNode.getChildren()){
            if (valueMax <   n.getNbVictories()/n.getNbSimulations()){
                valueMax = n.getNbVictories()/n.getNbSimulations();
                selectedNode = n;
            }
        }
        return selectedNode.getAction();
    }
}
