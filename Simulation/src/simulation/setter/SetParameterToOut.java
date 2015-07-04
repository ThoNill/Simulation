package simulation.setter;

import simulation.core.Node;

public class SetParameterToOut implements SetValue {
	private Node inNode;
	private int inNr;
	private Node quellNode;
	private int quellNr;

	public SetParameterToOut(Node inNode, int inNr, Node quellNode, int quellNr) {
		super();
		this.inNode = inNode;
		this.inNr = inNr;
		this.quellNode = quellNode;
		this.quellNr = quellNr;
	}

	@Override
	public void setValue() {
		inNode.setParameter(inNr, quellNode.getOut(quellNr));
	}

}
