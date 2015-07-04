package simulation.nodes;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class CXMult extends Node {

	public CXMult(String name,String title) {
		super(name);
		addParamDescription(new DoubleValueDescription("c",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED),title);
		addInDescription(new DoubleValueDescription("x",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addOutDescription(new DoubleValueDescription("prod",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
	}

	@Override
	public void calculate() {
		out[0] = in[0] * param[0];
	}
}
