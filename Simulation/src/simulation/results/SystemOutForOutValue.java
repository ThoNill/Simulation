package simulation.results;

import simulation.core.Node;
import simulation.graphics.LineGraph;

public class SystemOutForOutValue extends LineGraph {

	
	private String name;
	private Node outNode;
	private int outNr;

	public SystemOutForOutValue(String name,Node outNode, int outNr) {
		super();
		checkNulls(name, outNode);
		check(outNr >= 0, "Index muss >= 0 sein");
		
		this.name = name;
		this.outNode = outNode;
		this.outNr = outNr;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return outNode.getOut(outNr);
	}



}
