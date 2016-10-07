package simulation.results;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import simulation.core.Node;
import simulation.core.ShowResults;
import simulation.core.SimulationContext;
import simulation.graphics.KoordinatenKreuz;
import simulation.graphics.ShowResultComponent;
import simulation.graphics.Skala;

public class ShowInPanel extends ShowResultComponent implements ShowResults {

    private static final long serialVersionUID = 996599095126068084L;
    String name;
    Art art;

    public enum Art {
        WITH_CROSS, POSITIV_AXES
    }

    private List<GraphForOutValue> graphs = new ArrayList<>();

    public ShowInPanel(Art art) {
        super();
        this.art = art;
    }

    @Override
    public void init(String name) {
        this.name = name;
        switch (art) {
        case WITH_CROSS:
            addElement(new Skala(true));
            addElement(new Skala(false));
            addElement(new KoordinatenKreuz());
            setUrsprung(new Point(getWidth() / 2, getHeight() / 2));
            break;
        case POSITIV_AXES:
            Skala s = new Skala(true);
            s.setBeidseitig(false);
            s.setPositiv(true);
            addElement(s);
            s = new Skala(false);
            s.setBeidseitig(false);
            s.setPositiv(true);
            addElement(s);
            setUrsprung(new Point(10, 10));
            break;
        }
    }

    @Override
    public void addOutputFor(String name, Node outNode, int outNr, Color color) {
        check.checkNulls(name, outNode);
        check.check(outNr >= 0, "Index muss >= 0 sein");

        GraphForOutValue graph = new GraphForOutValue(name, outNode, outNr);
        graph.setCurrentColor(color);
        graphs.add(graph);
        addElement(graph);

    }

    @Override
    public void addViewData(SimulationContext system) {
        check.checkNull(system);
        double t = system.getTime();

        for (GraphForOutValue graph : graphs) {
            graph.addViewData(system, this);
        }
    }

    public int scaleX(double x) {
        return (int) (100.0 * x);
    }

    public int scaleY(double x) {
        return (int) (100.0 * x);
    }

    @Override
    public void updateView() {
        updateUI();

    }

    @Override
    public void clearView() {
        for (GraphForOutValue graph : graphs) {
            graph.clear();
        }
        updateUI();
    }

}
