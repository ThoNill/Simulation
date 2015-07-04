package simulation.doubles;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import allgemein.Check;

/**********
 * 
 * Double Value
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class DoubleValue extends Check implements ChangeListener {

	private String name;
	private double value;
	private JSlider slider;
	private DoubleValueDescription description;
	private double min = 0.0;
	private double max = 1.0;
	private double span = 1.0;
	private PropertyChangeSupport changeSupport;

	public DoubleValue(String name, DoubleValueDescription description) {
		super();
		checkNulls(name, description);

		changeSupport = new PropertyChangeSupport(this);
		this.name = name;
		setDescription(description);
	}

	public DoubleValue(String name) {
		super();
		checkNull(name);

		min = 0.0;
		max = 1.0;
		span = 1.0;
		this.name = name;
		value = 0.0;
		changeSupport = new PropertyChangeSupport(this);
	}

	public String getName() {
		return name;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		checkNull(listener);
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		checkNull(listener);
		changeSupport.removePropertyChangeListener(listener);
	}

	private void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	public DoubleValueDescription getDescription() {
		return description;
	}

	public void setDescription(DoubleValueDescription description) {
		checkNull(description);

		value = description.getLower().getLimit();
		if (description.getLower() != DoubleBound.UNRESTRICTED) {
			min = description.getLower().getLimit();
		}
		if (description.getUpper() != DoubleBound.UNRESTRICTED) {
			max = description.getUpper().getLimit();
		}
		span = max - min;
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		if (slider != null) {
			this.value = value;
			int ivalue = (int) ((value - min) * 100.0 / span);
			if (slider != null) {
				slider.setValue(ivalue);
			}
		}
	}

	public void setSlider(JSlider slider) {
		checkNull(slider);

		this.slider = slider;
		slider.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		checkNull(slider);

		double oldValue = value;
		value = min + (slider.getValue() * span) / 100.0;
		if (description.getUpper() == DoubleBound.UNRESTRICTED) {
			value = 10 * value / (1.0 - value);
		}
		firePropertyChange(name, oldValue, value);
	}

}
