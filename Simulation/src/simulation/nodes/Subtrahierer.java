package simulation.nodes;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Subtrahierer extends Node {

	public Subtrahierer(String name) {
		super(name);
		addInDescription(new DoubleValueDescription("a",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addInDescription(new DoubleValueDescription("b",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addOutDescription(new DoubleValueDescription("diff",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
	}

	@Override
	public void calculate() {
		out[0] = in[0] - in[1];
	}
}
