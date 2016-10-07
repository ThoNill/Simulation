package test;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import simulation.core.ShowResultsDescription;
import simulation.main.BindDescription;
import simulation.main.Simulation;
import simulation.main.SimulationFabric;

public class SimulationTest {
    private static final Logger LOG = LogManager
            .getLogger(SimulationTest.class);
    private static final String UNERWARTETE_AUSNAHME = "unerwartete Ausnahme";

    @Test
    public void testIdentity() {
        SimulationFabric description = new SimulationFabric("Integral");

        description.addNodeDescription("id", "simulation.nodes.Identity");
        description.addBindDescription(new BindDescription("id", "x"));
        description.addResultDescription("Result",
                "simulation.results.ShowOnSystemOut", "id", "f", Color.BLACK,
                "Result");

        Simulation sys;
        try {
            sys = description.createSystem();
            description.createShowResults(sys);
            sys.simulate();
        } catch (Exception e) {
            LOG.error(UNERWARTETE_AUSNAHME, e);
        }
    }

    @Test
    public void testIntegral() {
        SimulationFabric description = new SimulationFabric("Integral");

        description.addNodeDescription("id", "simulation.nodes.Integral");
        description.addBindDescription(new BindDescription("id", "f"));
        description.addResultDescription("Result",
                "simulation.results.ShowOnSystemOut", "id", "integral",
                Color.BLACK, "Result");

        Simulation sys;
        try {
            sys = description.createSystem();
            description.createShowResults(sys);
            sys.simulate();
        } catch (Exception e) {
            LOG.error(UNERWARTETE_AUSNAHME, e);
        }
    }

    @Test
    public void testIntegral2() {
        SimulationFabric description = new SimulationFabric("Integral");

        description.addNodeDescription("id", "simulation.nodes.Integral");
        description.addBindDescription(new BindDescription("id", "f"));
        description.addShowResults(new ShowResultsDescription("Result",
                "simulation.results.ShowInPanel", "id", "integral", "Result"));
        Simulation sys;
        try {
            sys = description.createSystem();
            JComponent panel = (JComponent) description.createShowResults(sys);

            JFrame frame = new JFrame();
            frame.setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
            frame.setContentPane(panel);
            frame.setSize(100, 100);
            frame.setVisible(true);

            sys.simulate();
        } catch (Exception e) {
            LOG.error(UNERWARTETE_AUSNAHME, e);
        }
    }

}
