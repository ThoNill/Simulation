package simulation.setter;

import simulation.core.Node;
import simulation.core.SimulationContext;

public class SetParameterToTime implements SetValue {
    private Node inNode;
    private int parNr;
    private SimulationContext system;

    public SetParameterToTime(Node inNode, int parNr, SimulationContext system) {
        super();
        this.inNode = inNode;
        this.parNr = parNr;
        this.system = system;
    }

    @Override
    public void setValue() {
        inNode.setParameter(parNr, system.getTime());
    }

}
