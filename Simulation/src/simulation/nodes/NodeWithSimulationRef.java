package simulation.nodes;

import simulation.core.Node;
import simulation.core.SimulationContext;

public class NodeWithSimulationRef extends Node {
	private SimulationContext system;

	public NodeWithSimulationRef(String name) {
		super(name);
	}

	public SimulationContext getSystem() {
		return system;
	}

	public void setSystem(SimulationContext system) {
		this.system = system;
	}

}
