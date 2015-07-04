package simulation.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;

/**
 * 
 * Implementierung der Swing UI Klasse zu SkitzeComponent
 * 
 * @author Thomas Nill
 *
 */
public class DefaultShowResultComponentUI extends ShowResultComponentUI {

	DefaultShowResultComponentUI() {
		super();
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		ShowResultComponent sc = (ShowResultComponent) c;
		Graphics2D g2 = (Graphics2D) g;

		ArrayList<GraphicalElement> elements = sc.getModel();
		synchronized (elements) {
			for (GraphicalElement t : elements) {
				t.paint(g2, sc);
			}
		}

	}

	public static ComponentUI createUI(JComponent c) {
		return new DefaultShowResultComponentUI();
	}

	@Override
	public void installUI(JComponent c) {

	}

	@Override
	public void uninstallUI(JComponent c) {

	}

}
