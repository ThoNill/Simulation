package simulation.doubles;

import simulation.core.ValueDescription;

/**********
 * 
 * Beschreibung eines Double Value
 * 
 * Datenstruktur
 * 
 * @author Thomas Nill
 *
 */
public class DoubleValueDescription extends ValueDescription<DoubleValue> {
    private DoubleBound lower;
    private DoubleBound upper;

    public DoubleValueDescription(String name, DoubleBound lower,
            DoubleBound upper) {
        super(name);
        checkNulls(name, lower, upper);

        this.lower = lower;
        this.upper = upper;
    }

    public DoubleBound getLower() {
        return lower;
    }

    public DoubleBound getUpper() {
        return upper;
    }

    @Override
    public Class getValueClass() {
        return double.class;
    }

}
