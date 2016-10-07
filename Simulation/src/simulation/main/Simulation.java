package simulation.main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import simulation.core.Node;
import simulation.core.ShowResults;
import simulation.core.SimulationContext;
import simulation.nodes.NodeWithSimulationRef;
import simulation.setter.SetValue;

/**********
 * 
 * Eine Simulation eines Systems
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class Simulation extends SimulationContext implements
        PropertyChangeListener {

    List<Node> nodes = new ArrayList<>();
    List<SetValue> setter = new ArrayList<>();
    List<SetValue> startValues = new ArrayList<>();
    List<SetValue> setTime = new ArrayList<>();

    List<ShowResults> showResuts = new ArrayList<>();

    public Simulation(double startTime, double timeStep, double endTime) {
        super(startTime, timeStep, endTime);

    }

    public synchronized void simulate() {

        time.start();
        for (Node node : nodes) {
            node.initDatastructures();
        }
        for (SetValue s : startValues) {
            s.setValue();
        }
        for (Node node : nodes) {
            node.initValues();
        }

        for (ShowResults show : showResuts) {
            show.clearView();
        }

        do {
            for (SetValue s : setTime) {
                s.setValue();
            }
            for (Node node : nodes) {
                node.calculate();
            }

            for (ShowResults show : showResuts) {
                show.addViewData(this);
            }

            for (SetValue s : setter) {
                s.setValue();
            }
        } while (time.next());

        for (ShowResults show : showResuts) {
            show.updateView();
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        simulate();

    }

    void afterCalculation(SetValue setValue) {
        checkNull(setValue);
        setter.add(setValue);
    }

    void afterTimeStep(SetValue setValue) {
        checkNull(setValue);
        setTime.add(setValue);
    }

    void atStart(SetValue setValue) {
        checkNull(setValue);
        startValues.add(setValue);
    }

    void addNode(Node node) {
        checkNull(node);

        if (node instanceof NodeWithSimulationRef) {
            ((NodeWithSimulationRef) node).setSystem(this);
        }

        nodes.add(node);
    }

    void addShowResult(ShowResults show) {
        checkNull(show);

        showResuts.add(show);
    }

}
