package simulation.main;

import allgemein.Check;

/**********
 * 
 * Beschreibt die Verbindung zwiechen In, Out und Parameterwerten
 * 
 * Datenstruktur
 * 
 * @author Thomas Nill
 *
 */
public class BindDescription extends Check {

	public static enum DescriptionType {
		CONSTANT, NODE, VALUE, TIME
	};

	private String quellNode;
	private String quellValue;
	private String zielValue;
	private double value;
	private DescriptionType type = DescriptionType.TIME;
	private String zielNode;

	public BindDescription(String zielNode, String zielValue, String quellNode,
			String quellValue) {
		super();
		checkNulls(zielNode, zielValue, quellNode, quellValue);

		this.quellNode = quellNode;
		this.zielNode = zielNode;
		this.quellValue = quellValue;
		this.zielValue = zielValue;
		this.type = DescriptionType.NODE;
	}

	public BindDescription(String zielNode, String zielValue, String quellValue) {
		super();
		checkNulls(zielNode, zielValue, quellValue);
		this.zielNode = zielNode;
		this.quellValue = quellValue;
		this.zielValue = zielValue;
		this.type = DescriptionType.VALUE;
	}

	public BindDescription(String zielNode, String zielValue) {
		super();
		checkNulls(zielNode, zielValue);
		this.zielNode = zielNode;
		this.zielValue = zielValue;
		this.type = DescriptionType.TIME;
	}

	public BindDescription(String zielNode, String zielValue, double value) {
		super();
		checkNulls(zielNode, zielValue);

		this.zielValue = zielValue;
		this.value = value;
		this.zielNode = zielNode;
		this.type = DescriptionType.CONSTANT;
	}

	public String getQuellNode() {
		return quellNode;
	}

	public String getQuellValue() {
		return quellValue;
	}

	public String getZielValue() {
		return zielValue;
	}

	public double getValue() {
		return value;
	}

	public DescriptionType getType() {
		return type;
	}

	public String getZielNode() {
		return zielNode;
	}
}