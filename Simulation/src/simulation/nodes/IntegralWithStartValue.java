package simulation.nodes;

import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class IntegralWithStartValue extends NodeWithSimulationRef {
	double sum = 0.0;

	public IntegralWithStartValue(String name,String title) {
		super(name);
		addInDescription(new DoubleValueDescription("f",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addOutDescription(new DoubleValueDescription("integral",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addParamDescription(new DoubleValueDescription("start",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED),title);
	}

	@Override
	public void initValues() {
		sum = param[0];
	}

	@Override
	public void calculate() {
		sum = in[0] * getSystem().getTimeStep() + sum;
		out[0] = sum;
	}
}
