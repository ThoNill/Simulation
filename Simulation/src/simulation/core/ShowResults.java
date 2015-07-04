package simulation.core;

import java.awt.Color;

/**********
 * 
 * Zeigt Daten einer Simulation an
 * 
 * Object Interface
 * 
 * @author Thomas Nill
 *
 */
public interface ShowResults {
	public void addViewData(SimulationContext system);

	public void init(String name);
	
	public void addOutputFor(String name,Node outNode, int outNr,Color color);

	public void clearView();

	public void updateView();

}
