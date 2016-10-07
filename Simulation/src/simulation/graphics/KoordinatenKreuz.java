/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

// ******************************************************************************
// Spirograph.java:	Applet
// 
// ******************************************************************************
package simulation.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class KoordinatenKreuz implements GraphicalElement {

    public KoordinatenKreuz() {
    }

    @Override
    public void paint(Graphics2D g, ShowResultComponent comp) {
        Dimension dim = comp.getSize();

        int x0 = dim.width / 2;
        int y0 = dim.height / 2;

        g.drawLine(x0, 0, x0, dim.height);
        g.drawLine(0, y0, dim.width, y0);

    }

}

/*--- formatting done in "Sun Java Convention" style on 07-22-2000 ---*/

