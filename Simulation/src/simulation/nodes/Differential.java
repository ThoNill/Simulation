package simulation.nodes;

import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Differential extends NodeWithSimulationRef {
	double last = 0.0;

	public Differential(String name) {
		super(name);
		addInDescription(new DoubleValueDescription("f",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addOutDescription(new DoubleValueDescription("differential",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
	}

	@Override
	public void calculate() {
		out[0] = (in[0] - last) / getSystem().getTimeStep();
		last = in[0];
	}
}
