package simulation.setter;

import simulation.core.Node;
import simulation.doubles.DoubleValue;

public class SetParameterToValue implements SetValue {
    private Node inNode;
    private int parNr;
    private DoubleValue value;

    public SetParameterToValue(Node inNode, int parNr, DoubleValue value) {
        super();
        this.inNode = inNode;
        this.parNr = parNr;
        this.value = value;
    }

    @Override
    public void setValue() {
        inNode.setParameter(parNr, value.getValue());
    }

}
