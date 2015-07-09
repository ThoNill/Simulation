package test;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import simulation.core.ShowResultsDescription;
import simulation.core.SimulationContext;
import simulation.main.BindDescription;
import simulation.main.Simulation;
import simulation.main.SimulationFabric;
import simulation.results.ShowInPanel;

public class SimulationTest {

	@Test
	public void testIdentity() {
		SimulationFabric description = new SimulationFabric("Integral");
		
		description.addNodeDescription("id","simulation.nodes.Identity");
		description.addBindDescription(new BindDescription("id", "x"));
		description.addResultDescription("Result","simulation.results.ShowOnSystemOut", "id", "f",Color.BLACK,"Result");
		
		Simulation sys;
		try {
			sys = description.createSystem();
			description.createShowResults(sys);
			sys.simulate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIntegral() {
		SimulationFabric description = new SimulationFabric("Integral");
		
		description.addNodeDescription("id","simulation.nodes.Integral");
		description.addBindDescription(new BindDescription("id", "f"));
		description.addResultDescription("Result","simulation.results.ShowOnSystemOut", "id", "integral",Color.BLACK,"Result");
		
		Simulation sys;
		try {
			sys = description.createSystem();
			description.createShowResults(sys);
			sys.simulate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIntegral2() {
		SimulationFabric description = new SimulationFabric("Integral");
	
		description.addNodeDescription("id","simulation.nodes.Integral");
		description.addBindDescription(new BindDescription("id", "f"));
		description.addShowResults(new ShowResultsDescription("Result","simulation.results.ShowInPanel", "id", "integral","Result"));
		Simulation sys;
		try {
			sys = description.createSystem();
			JComponent panel = (JComponent)description.createShowResults(sys);
			
			JFrame frame = new JFrame();
			frame.setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
			frame.setContentPane(panel);
			frame.setSize(100, 100);
			frame.setVisible(true);
			
			sys.simulate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
