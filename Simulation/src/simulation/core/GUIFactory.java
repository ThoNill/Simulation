package simulation.core;

import javax.swing.JPanel;

/**********
 * 
 * Erzeugt Oberflächenelements
 * 
 * Object Interface
 * 
 * @author Thomas Nill
 *
 */
public interface GUIFactory<V, D> {
	public abstract JPanel getGUI(V value, D description);
}
