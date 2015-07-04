package simulation.core;

import allgemein.Check;

/**********
 * 
 * Beschreibt eine Node
 * 
 * Datenstruktur
 * 
 * @author Thomas Nill
 *
 */
public class NodeDescription extends Check {
	private String name;
	private String className;
	private String title;

	public NodeDescription(String name, String className) {
		super();
		checkNulls(name, className);

		this.name = name;
		this.className = className;

	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getTitle() {
		if (title==null) {
			return name;
		}
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
