package simulation.setter;

import simulation.core.Node;

public class SetInToConstant implements SetValue {
    private Node inNode;
    private int inNr;
    private double value;

    public SetInToConstant(Node inNode, int inNr, double value) {
        super();
        this.inNode = inNode;
        this.inNr = inNr;
        this.value = value;
    }

    @Override
    public void setValue() {
        inNode.setIn(inNr, value);
    }

}
