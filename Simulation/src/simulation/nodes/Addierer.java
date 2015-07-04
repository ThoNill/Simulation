package simulation.nodes;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

/**********
 * 
 * Addierer
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class Addierer extends Node {

	public Addierer(String name) {
		super(name);
		addInDescription(new DoubleValueDescription("a",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addInDescription(new DoubleValueDescription("b",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
		addOutDescription(new DoubleValueDescription("add",
				DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
	}

	@Override
	public void calculate() {
		out[0] = in[0] + in[1];
	}
}
