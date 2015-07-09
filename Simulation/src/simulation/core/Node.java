package simulation.core;

import java.util.Hashtable;
import java.util.Vector;

import allgemein.CheckAuftreten;
import simulation.core.ValueDescription.Type;

/**********
 * 
 * Knoten für eine Berechnung
 * 
 * Objekt
 * 
 * @author Thomas Nill
 *
 */
public class Node extends CheckAuftreten {

	private String name;
	private Vector<ValueDescription<?>> inDescriptions = new Vector<ValueDescription<?>>();
	private Vector<ValueDescription<?>> outDescriptions = new Vector<ValueDescription<?>>();
	private Vector<ValueDescription<?>> paramDescriptions = new Vector<ValueDescription<?>>();
	private Hashtable<String, ValueDescription<?>> descriptions = new Hashtable<>();

	protected double in[] = null;
	protected double out[] = null;
	protected double param[] = null;

	public Node(String name) {
		super();
		checkNull(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected void addInDescription(ValueDescription<?> d) {
		checkNull(d);
		d.setType(Type.IN);
		addToDecriptions(inDescriptions, d);
	}

	protected void addOutDescription(ValueDescription<?> d) {
		checkNull(d);
		d.setType(Type.OUT);
		addToDecriptions(outDescriptions, d);
	}

	protected void addParamDescription(ValueDescription<?> d,String title) {
		checkNull(d);
		d.setType(Type.PARAMETER);
		d.setTitle(title);
		addToDecriptions(paramDescriptions, d);
	}

	private void addToDecriptions(
			Vector<ValueDescription<?>> specificDescriptions,
			ValueDescription<?> d) {
		nurEinmal(d.getName(),
				"Value Description mit gleichem Namen geht nicht");

		d.setNumber(specificDescriptions.size());
		specificDescriptions.add(d);
		d.setNode(this);
		descriptions.put(d.getName(), d);
	}

	public double getOut(int nr) {
		check(nr >= 0, "Index uss >= 0 sein");

		if (out == null) {
			out = new double[outDescriptions.size()];
		}
		return out[nr];
	}

	public void setIn(int nr, double value) {
		check(nr >= 0, "Index uss >= 0 sein");

		if (in == null) {
			in = new double[inDescriptions.size()];
		}
		in[nr] = value;
	}

	public void setParameter(int nr, double value) {
		check(nr >= 0, "Index uss >= 0 sein");

		if (param == null) {
			param = new double[paramDescriptions.size()];
		}
		param[nr] = value;
	}

	public void calculate() {

	}

	public void initDatastructures() {
		in = new double[inDescriptions.size()];
		param = new double[paramDescriptions.size()];
		out = new double[outDescriptions.size()];
	}

	public void initValues() {

	}

	public ValueDescription<?> searchDescription(String name) {
		checkNull(name);

		return descriptions.get(name);
	}

	public Vector<ValueDescription<?>> openParameters(
			Hashtable<String, String> boundedParameter) {
		checkNull(boundedParameter);

		Vector<ValueDescription<?>> result = new Vector<>();
		for (ValueDescription<?> descr : paramDescriptions) {
			if (!boundedParameter.contains(descr.getLongName())) {
				result.add(descr);
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
