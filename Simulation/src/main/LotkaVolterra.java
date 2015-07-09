package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simulation.core.ShowResultsDescription;
import simulation.main.BindDescription;
import simulation.main.ParameterPanel;
import simulation.main.Simulation;
import simulation.main.SimulationFabric;

public class LotkaVolterra {

	public LotkaVolterra() {
	}

	public static void main(String[] args) {
	
		
	//		SimulationFabric description =integralX();
			SimulationFabric description = lotkaVolterra();
			
			Simulation sys;
			try {
				sys = description.createSystem();
				
				
				JComponent comp = (JComponent)description.createShowResults(sys);
				
				sys.simulate();
			
				ParameterPanel parameterPanel = description.createParameterPanel(sys);
				JPanel legende = description.resultLegende();
				parameterPanel.add(legende);
				parameterPanel.add(new JPanel());
				
				parameterPanel.setValue("cmr.c",1.0);
				parameterPanel.setValue("cmb.c",1.0);
				parameterPanel.setValue("mr.c",1.0);
				parameterPanel.setValue("mb.c",1.0);
				parameterPanel.setValue("raeuber.start",0.5);
				parameterPanel.setValue("beute.start",1.2);
				
				JPanel panel = new JPanel(new BorderLayout());
				panel.add(BorderLayout.EAST,parameterPanel);
				panel.add(BorderLayout.CENTER,comp);
				panel.add(BorderLayout.NORTH, new JLabel(description.getTitle()));
				
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BoxLayout(frame, BoxLayout.X_AXIS));
				frame.setContentPane(panel);
				
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				dim.width /= 2;
				dim.height /= 2;
				frame.setBounds( dim.width/2, dim.height/2, dim.width, dim.height);
				frame.setVisible(true);
				
				comp.invalidate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	
	protected static SimulationFabric integralX() {
		SimulationFabric description = new SimulationFabric("Integral von f(x) := x");
		description.setEndTime(3.0);
		description.addNodeDescription("id","simulation.nodes.Integral");
		description.addBindDescription(new BindDescription("id", "f"));
		description.addShowResults(new ShowResultsDescription("Result","simulation.results.ShowInPanel","id", "integral","Integral"));
		return description;
	}
	
	protected static SimulationFabric  lotkaVolterra() {
		SimulationFabric description = new SimulationFabric("Lotka/Volterra");
		description.setEndTime(100.0);
		description.setStartTime(0.0);
		description.setTimeStep(0.005);
		description.addNodeDescription("beute","simulation.nodes.IntegralWithStartValue").setTitle("Beute");
		description.addNodeDescription("raeuber","simulation.nodes.IntegralWithStartValue").setTitle("Räuber");
				
		description.addNodeDescription("cmr","simulation.nodes.CXYMult").setTitle("Vermehrung Räuber");
		description.addNodeDescription("cmb","simulation.nodes.CXYMult").setTitle("Getötete Beute");
		
		description.addNodeDescription("mr","simulation.nodes.CXMult").setTitle("Todesrate Räuber");
		description.addNodeDescription("mb","simulation.nodes.CXMult").setTitle("Vermehrung Beute");
		
		description.addNodeDescription("sr","simulation.nodes.Subtrahierer");
		description.addNodeDescription("sb","simulation.nodes.Subtrahierer");
		
		description.connect("cmr.x","raeuber.integral");
		description.connect("cmr.y","beute.integral");

		description.connect("cmb.x","raeuber.integral");
		description.connect("cmb.y","beute.integral");
		
		description.connect("mb.x","beute.integral");
		description.connect("mr.x","raeuber.integral");
		
		description.connect("sb.a","mb.prod");
		description.connect("sb.b","cmb.prod");
		
		description.connect("sr.a","cmr.prod");
		description.connect("sr.b","mr.prod");
		
		description.connect("beute.f","sb.diff");
		description.connect("raeuber.f","sr.diff");
		
		ShowResultsDescription showDescription = new ShowResultsDescription("Räuber&Beute","simulation.results.PositivAxes");
		showDescription.add("beute", "integral",Color.BLACK,"Beute");
		showDescription.add("raeuber", "integral",Color.BLUE,"Räuber");
		description.addShowResults(showDescription);
		return description;
	}

}
