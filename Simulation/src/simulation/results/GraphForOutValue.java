package simulation.results;

import java.awt.Point;
import simulation.core.Node;
import simulation.core.SimulationContext;
import simulation.graphics.LineGraph;

public class GraphForOutValue extends LineGraph {

	
	String name;
	private Node outNode;
	private int outNr;
	private int lastX;

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
		int x = panel.scaleX(t);
		int y = panel.scaleY(value);
		
		if (size()==0 || Math.abs(lastX - x) > 3) {
			Point p = new Point(x,y);
			addElement(p);
			lastX = x;
		} 
	}



}
