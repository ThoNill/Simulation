package simulation.setter;

import simulation.core.Node;

/**********
 * 
 * Setzt einen IN auf einen Out Wert
 * 
 * Object
 * 
 * @author Thomas Nill
 *
 */
public class SetInToOut implements SetValue {
    private Node inNode;
    private Node outNode;
    private int outNr;
    private int inNr;

    public SetInToOut(Node inNode, int inNr, Node outNode, int outNr) {
        super();
        this.inNode = inNode;
        this.inNr = inNr;
        this.outNode = outNode;
        this.outNr = outNr;
    }

    @Override
    public void setValue() {
        inNode.setIn(inNr, outNode.getOut(outNr));
    }

}
