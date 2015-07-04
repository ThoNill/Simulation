package simulation.doubles;

import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import allgemein.Check;
import simulation.core.GUIFactory;

/**********
 * 
 * Erzeugt einen Slider für Double Werte
 * 
 * Object Interface
 * 
 * @author Thomas Nill
 *
 */
public class DoubleViewFactory extends Check implements
		GUIFactory<DoubleValue, DoubleValueDescription> {

	@Override
	public JPanel getGUI(DoubleValue value, DoubleValueDescription description) {
		checkNulls(value, description);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		TitledBorder titel = new TitledBorder(description.getTitle());
		
		panel.setBorder(titel);

		Hashtable<Integer,JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel(description.getLower()
				.toString()));
		labelTable.put(new Integer(100), new JLabel(description.getUpper()
				.toString()));

		JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);

		value.setDescription(description);
		value.setSlider(slider);

		panel.add(slider);
		return panel;
	}

}
