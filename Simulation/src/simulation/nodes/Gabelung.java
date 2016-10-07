package simulation.nodes;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Gabelung extends Node {

    public Gabelung(String name) {
        super(name);
        addInDescription(new DoubleValueDescription("x",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
        addOutDescription(new DoubleValueDescription("a",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
        addOutDescription(new DoubleValueDescription("b",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
    }

    @Override
    public void calculate() {
        out[0] = in[0];
        out[1] = in[0];
    }
}
