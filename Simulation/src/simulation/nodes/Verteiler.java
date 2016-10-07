package simulation.nodes;

import simulation.core.Node;
import simulation.doubles.DoubleBound;
import simulation.doubles.DoubleValueDescription;

public class Verteiler extends Node {
    private double anteil;

    public Verteiler(String name, String title) {
        this(name, 0.5, title);
    }

    public Verteiler(String name, double anteil, String title) {
        super(name);
        this.anteil = anteil;
        addInDescription(new DoubleValueDescription("x",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
        addOutDescription(new DoubleValueDescription("a",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
        addOutDescription(new DoubleValueDescription("b",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED));
        addParamDescription(new DoubleValueDescription("anteil",
                DoubleBound.UNRESTRICTED, DoubleBound.UNRESTRICTED), title);
    }

    @Override
    public void calculate() {
        out[0] = param[0] * in[0];
        out[1] = (1.0 - param[0]) * in[0];
    }

    @Override
    public void initDatastructures() {
        super.initDatastructures();
        param[0] = anteil;
    }
}
