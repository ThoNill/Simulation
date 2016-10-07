package simulation.results;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import simulation.core.Node;
import simulation.core.ShowResults;
import simulation.core.SimulationContext;
import allgemein.Check;

/**********
 * 
 * Einfache Ausgabe einer Liste mit Datenwerten
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class ShowOnSystemOut extends Check implements ShowResults {

    String name;

    private List<SystemOutForOutValue> values = new ArrayList<>();

    public ShowOnSystemOut() {
        super();
    }

    @Override
    public void init(String name) {
        checkNull(name);
        this.name = name;
    }

    @Override
    public void addOutputFor(String name, Node outNode, int outNr, Color color) {
        checkNulls(name, outNode);
        check(outNr >= 0, "Index muss >= 0 sein");
        values.add(new SystemOutForOutValue(name, outNode, outNr));

    }

    @Override
    public void addViewData(SimulationContext system) {

        double t = system.getTime();

        System.out.format(" %10.2f ", t);
        for (SystemOutForOutValue value : values) {
            System.out.format(" %10.2f ", value.getValue());
        }
        System.out.println();
    }

    @Override
    public void updateView() {
    }

    @Override
    public void clearView() {
        System.out.println(name);
        System.out.format(" %12s ", "Zeit");
        for (SystemOutForOutValue value : values) {
            System.out.format(" %12s ", value.getName());
        }
        System.out.println();
    }

}
