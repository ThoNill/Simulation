package simulation.main;

import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JPanel;

import simulation.doubles.DoubleValue;

public class ParameterPanel extends JPanel {
	HashMap<String, DoubleValue> values = new HashMap<>();
	
	public ParameterPanel() {
	}

	public ParameterPanel(LayoutManager layout) {
		super(layout);
	}

	public ParameterPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public ParameterPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}
	
	public void add(DoubleValue value) {
		values.put(value.getName(), value);
	}
	
	public void setValue(String name,double value) {
		values.get(name).setValue(value);
	}

	public double getValue(String name) {
		return values.get(name).getValue();
	}

	
	

}
