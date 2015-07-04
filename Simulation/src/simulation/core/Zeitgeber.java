package simulation.core;

/**********
 * 
 * Der Zeitgeber einer Simulation
 * 
 * Object Interface
 * 
 * @author Thomas Nill
 *
 */
public interface Zeitgeber {
	public double getCurrentTime();

	public abstract void start();

	public abstract boolean next();

	public double getStep();

}