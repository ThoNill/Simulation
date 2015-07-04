package simulation.core;

import allgemein.Check;

/**********
 * 
 * Beschreibt einen DatenWert in einer Node
 * 
 * Datenstruktur
 * 
 * @author Thomas Nill
 *
 */
public abstract class ValueDescription<V> extends Check {

	public static enum Type {
		IN, OUT, PARAMETER
	}

	private Type type;
	private int number;
	private String name;
	private Node node;
	private String title;

	public abstract Class getValueClass();

	public ValueDescription(String name) {
		super();
		checkNull(name);

		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		check(number >= 0, "Index muss >= 0 sein");

		this.number = number;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		checkNull(node);

		this.node = node;
	}

	public String getName() {
		return name;
	}

	public String getLongName() {
		return node.getName() + "." + name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		checkNull(type);

		this.type = type;
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
