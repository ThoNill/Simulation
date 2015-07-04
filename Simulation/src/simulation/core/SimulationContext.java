package simulation.core;

import allgemein.Check;

/**********
 * 
 * Umgebung einer Simulation
 * 
 * Datenstruktur
 * 
 * @author Thomas Nill
 *
 */
public class SimulationContext extends Check {
	protected Zeitgeber time;

	public SimulationContext(double startTime, double timeStep, double endTime) {
		time = new LineareZeit(startTime, timeStep, endTime);
	}

	public double getTime() {
		return time.getCurrentTime();
	}

	public double getTimeStep() {
		return time.getStep();
	}

}
