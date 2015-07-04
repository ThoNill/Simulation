package simulation.nodes;

import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Integral extends NodeWithSimulationRef {
	double sum = 0.0;

	public Integral(String name) {
		super(name);
		addInDescription(new DoubleValueDescription("f",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addOutDescription(new DoubleValueDescription("integral",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
	}

	@Override
	public void calculate() {
		sum = in[0] * getSystem().getTimeStep() + sum;
		out[0] = sum;
	}
}
