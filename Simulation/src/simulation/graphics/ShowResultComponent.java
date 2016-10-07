package simulation.graphics;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import allgemein.Check;

/**
 * 
 * Swing Komponente um Skitzen grafisch darzustellen
 * 
 * @author Thomas Nill
 *
 */
public class ShowResultComponent extends JComponent implements ChangeListener {

    private static final long serialVersionUID = 4228495550165832404L;
    private Point ursprung;
    private ShowResultComponentUI ui;
    protected Check check = new Check();

    private ArrayList<GraphicalElement> elements = new ArrayList<GraphicalElement>();

    public ShowResultComponent() {
        super();
        ui = new DefaultShowResultComponentUI();
    }

    public Point getUrsprung() {
        return ursprung;
    }

    public void setUrsprung(Point ursprung) {
        check.checkNull(ursprung);
        this.ursprung = ursprung;
    }

    public ArrayList<GraphicalElement> getModel() {
        return elements;
    }

    public void addElement(GraphicalElement e) {
        check.checkNull(e);

        elements.add(e);
        updateUI();
    }

    @Override
    public void stateChanged(ChangeEvent arg0) {
        updateUI();
    }

    public void clear() {
        elements.removeAll(elements);
        updateUI();
    }

    @Override
    public void updateUI() {
        setUI(ui);
        invalidate();
    }

    @Override
    public String getUIClassID() {
        return "ShowResultComponentUI";
    }
}
