package simulation.core;

import allgemein.Check;

/**********
 * 
 * Linear vergehende Zeit
 * 
 * Objekt
 * 
 * @author Thomas Nill
 *
 */
public class LineareZeit extends Check implements Zeitgeber {
	private double currentTime;
	private double startX;
	private double endX;
	private double xStep;

	public LineareZeit(double startX, double xStep, double endX) {
		super();
		check(startX < endX, "startX muss < als endX sein");
		check(xStep < (endX - startX), "xStep muss kleiner sein");
		this.startX = startX;
		this.xStep = xStep;
		this.endX = endX;
	}

	public double getStartX() {
		return startX;
	}

	@Override
	public double getCurrentTime() {
		return currentTime;
	}

	public double getEndX() {
		return endX;
	}

	@Override
	public double getStep() {
		return xStep;
	}

	@Override
	public void start() {
		currentTime = startX;
	}

	@Override
	public boolean next() {
		currentTime += xStep;
		return currentTime <= endX;
	}

}
