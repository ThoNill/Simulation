package simulation.doubles;

/**********
 * 
 * Ober und Untergrenzen von Double Werten
 * 
 * Datenstruktur
 * 
 * @author Thomas Nill
 *
 */
public class DoubleBound {
	public static DoubleBound UNRESTRICTED = new DoubleBound();

	private double limit;

	public double getLimit() {
		return limit;
	}

	public DoubleBound(double limit) {
		super();
		this.limit = limit;
	}

	public DoubleBound() {
		this(0.0);
	}

	@Override
	public String toString() {
		if (this == UNRESTRICTED)
			return "";
		return "" + limit;
	}

}
