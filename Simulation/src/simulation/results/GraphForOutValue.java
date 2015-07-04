package simulation.results;

import java.awt.Point;
import simulation.core.Node;
import simulation.core.SimulationContext;
import simulation.graphics.LineGraph;

public class GraphForOutValue extends LineGraph {

	
	String name;
	private Node outNode;
	private int outNr;

	public GraphForOutValue(String name,Node outNode, int outNr) {
		super();
		checkNulls(name, outNode);
		check(outNr >= 0, "Index muss >= 0 sein");
		
		this.name = name;
		this.outNode = outNode;
		this.outNr = outNr;
	}


	public void addViewData(SimulationContext system,ShowInPanel panel) {
		checkNull(system);
		
		double t = system.getTime();

		double value = outNode.getOut(outNr);
		Point p = new Point(panel.scaleX(t), panel.scaleY(value));
		addElement(p);
	}



}
