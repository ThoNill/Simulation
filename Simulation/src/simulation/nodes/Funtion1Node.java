package simulation.nodes;

import java.util.function.DoubleUnaryOperator;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Funtion1Node extends Node {
    private DoubleUnaryOperator f;

    public Funtion1Node(String name, DoubleUnaryOperator f, double lower,
            double upper) {
        super(name);
        this.f = f;
        addInDescription(new DoubleValueDescription("x",
                new DoubleBound(lower), new DoubleBound((upper))));
        addOutDescription(new DoubleValueDescription("f",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
    }

    @Override
    public void calculate() {
        out[0] = f.applyAsDouble(in[0]);
    }
}
