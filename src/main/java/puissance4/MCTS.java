package puissance4;

public class MCTS {
    private Node racineNode;
    private static final double C = Math.sqrt(2);

    public Node selection(){
        return null;
    }

    public void expansion(){

    }

    public void simulation(){

    }

    public void rollout(){

    }

    private double UCBValue(Node node){
        Node parent = node.getParent();
        if (node.getNbSimulations() == 0 )
            return Double.MAX_VALUE;

        double exploitation = node.getNbVictories()/node.getNbSimulations();
        double exploration  = C*Math.sqrt(parent.getNbSimulations()/node.getNbVictories());
        return exploitation+exploration;
    }
}
