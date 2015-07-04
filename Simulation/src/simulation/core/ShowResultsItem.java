package simulation.core;

import java.awt.Color;

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
public class ShowResultsItem extends Check {
	private String name;
	private String nodeName;
	private String outName;
	private Color color;

	public ShowResultsItem(String name, String nodeName, String outName,Color color) {
		super();
		checkNulls(name, nodeName, outName);

		this.name = name;
		this.nodeName = nodeName;
		this.outName = outName;
		this.color = color;
	}
	
	

	public String getName() {
		return name;
	}

	public String getNodeName() {
		return nodeName;
	}

	public String getOutName() {
		return outName;
	}



	public Color getColor() {
		return color;
	}

}
