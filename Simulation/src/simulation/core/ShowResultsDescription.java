package simulation.core;

import java.awt.Color;
import java.util.Vector;

import allgemein.Check;

/**********
 * 
 * Beschreibung einer Anzeige für Ergebnisse
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class ShowResultsDescription extends Check {
	private String name;
	private String className;
	private Vector<ShowResultsItem> items = new Vector<>();
	

	public ShowResultsDescription(String name, String className) {
		super();
		checkNulls(name, className);

		this.name = name;
		this.className = className;
	}
	
	public ShowResultsDescription(String name, String className,String nodeName, String outName, String title) {
		this(name,className);
		add(nodeName,outName,Color.BLACK,title);
	}
		
	public void add(String nodeName, String outName,Color color, String title) {
		add(new ShowResultsItem(nodeName, nodeName, outName,color,title));
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public void add(ShowResultsItem item) {
		items.add(item);
	}

	public Vector<ShowResultsItem> getItems() {
		return items;
	}
}

