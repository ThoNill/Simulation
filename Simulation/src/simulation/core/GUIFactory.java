package simulation.core;

import javax.swing.JPanel;

/**********
 * 
 * Erzeugt Oberfl�chenelements
 * 
 * Object Interface
 * 
 * @author Thomas Nill
 *
 */
public interface GUIFactory<V, D> {
	public abstract JPanel getGUI(V value, D description);
}
