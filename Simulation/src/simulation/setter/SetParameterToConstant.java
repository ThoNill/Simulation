package simulation.setter;

import simulation.core.Node;

public class SetParameterToConstant implements SetValue {
    private Node inNode;
    private int parNr;
    private double value;

    public SetParameterToConstant(Node inNode, int parNr, double value) {
        super();
        this.inNode = inNode;
        this.parNr = parNr;
        this.value = value;
    }

    @Override
    public void setValue() {
        inNode.setParameter(parNr, value);
    }

}
