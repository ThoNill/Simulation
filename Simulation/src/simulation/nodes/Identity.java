package simulation.nodes;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Identity extends Node {

    public Identity(String name) {
        super(name);
        addInDescription(new DoubleValueDescription("x",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
        addOutDescription(new DoubleValueDescription("f",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
    }

    @Override
    public void calculate() {
        out[0] = in[0];
    }
}
