package test;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import simulation.core.ShowResultsDescription;
import simulation.main.BindDescription;
import simulation.main.Simulation;
import simulation.main.SimulationFabric;

public class WithParameters {
    private static final Logger LOG = LogManager
            .getLogger(WithParameters.class);
    private static final String UNERWARTETE_AUSNAHME = "unerwartete Ausnahme";

    public WithParameters() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        SimulationFabric description = new SimulationFabric("Integral");

        description.addNodeDescription("id",
                "simulation.nodes.IntegralWithStartValue");
        description.addBindDescription(new BindDescription("id", "f"));
        description.addShowResults(new ShowResultsDescription("Result",
                "simulation.results.ShowInPanel", "id", "integral", "Result"));

        Simulation sys;
        try {

            sys = description.createSystem();
            JComponent comp = (JComponent) description.createShowResults(sys);
            sys.simulate();

            JPanel parameterPanel = description.createParameterPanel(sys);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(BorderLayout.CENTER, comp);
            panel.add(BorderLayout.EAST, parameterPanel);
            panel.add(BorderLayout.NORTH, new JLabel("Label"));

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
            frame.setContentPane(panel);
            frame.setSize(100, 100);
            frame.setVisible(true);
            comp.invalidate();

        } catch (Exception e) {
            LOG.error(UNERWARTETE_AUSNAHME, e);
        }

    }

}
