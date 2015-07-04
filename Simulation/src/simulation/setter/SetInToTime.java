package simulation.setter;

import simulation.core.Node;
import simulation.core.SimulationContext;

public class SetInToTime implements SetValue {
	private Node inNode;
	private int inNr;
	private SimulationContext system;

	public SetInToTime(Node inNode, int parNr, SimulationContext system) {
		super();
		this.inNode = inNode;
		this.inNr = parNr;
		this.system = system;
	}

	@Override
	public void setValue() {
		inNode.setIn(inNr, system.getTime());
	}

}
