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
public class Skala implements GraphicalElement {
    private int step = 10;
    private boolean horizontal = true;
    private boolean beidseitig = false;
    private boolean positiv = false;

    public Skala(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isPositiv() {
        return positiv;
    }

    public void setPositiv(boolean positiv) {
        this.positiv = positiv;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setBeidseitig(boolean beidseitig) {
        this.beidseitig = beidseitig;
    }

    @Override
    public void paint(Graphics2D g, ShowResultComponent comp) {
        Dimension dim = comp.getSize();

        int x0 = (positiv) ? 10 : dim.width / 2;
        int y0 = (positiv) ? 10 : dim.height / 2;

        g.drawLine(x0, 0, x0, dim.height);
        g.drawLine(0, dim.height - y0, dim.width, dim.height - y0);

        int count = 0;
        int t = 0;
        int h = 0;

        int grenze = (horizontal) ? dim.width / 2 : dim.height / 2;
        if (positiv) {
            grenze = 2 * grenze;
        }

        while (t < grenze) {
            h = getStrichHoehe(count);
            zeichneStrichchen(g, dim, x0, y0, t, h);
            t += step;
            count++;
        }
    }

    private int getStrichHoehe(int count) {
        if (count % 10 == 0)
            return 12;
        return (count % 5 == 0) ? 10 : 5;
    }

    private void zeichneStrichchen(Graphics2D g, Dimension dim, int x0, int y0,
            int t, int h) {
        if (horizontal) {
            g.drawLine(t + x0, dim.height - y0, t + x0, dim.height - y0 + h);
            if (beidseitig) {
                g.drawLine(-t + x0, dim.height - y0, -t + x0, dim.height - y0
                        + h);
            }
        } else {
            g.drawLine(x0, dim.height - t - y0, x0 - h, dim.height - t - y0);
            if (beidseitig) {
                g.drawLine(x0, dim.height + t - y0, x0 - h, dim.height + t - y0);
            }
        }
    }

}

/*--- formatting done in "Sun Java Convention" style on 07-22-2000 ---*/

