package simulation.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;

import allgemein.Check;

/**
 * 
 * Die Klasse des Datenmodells eine Skitze, die einen Namen und verschiedene
 * Teile hat
 * 
 * @author Thomas Nill
 *
 */
public class LineGraph extends Check implements GraphicalElement {
	private static final long serialVersionUID = 8968178121114199097L;
	private Color currentColor = Color.black;
	private int currentLineWidth = 1;
	private ArrayList<Point> points = new ArrayList<Point>();

	public LineGraph() {
	}

	public void setCurrentColor(Color currentColor) {
		checkNull(currentColor);

		this.currentColor = currentColor;
	}

	public void setCurrentLineWidth(int currentLineWidth) {
		check(currentLineWidth > 0, "Strichstärke muss > 0 sein");
		this.currentLineWidth = currentLineWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simulation.graphics.GraphicalElement#paint(java.awt.Graphics2D)
	 */
	@Override
	public void paint(Graphics2D g, ShowResultComponent comp) {

		Dimension dim = comp.getSize();
		Point ursprung = comp.getUrsprung();
		int x0 = ursprung.x;
		int y0 = ursprung.y;
		int h = dim.height - y0;

		Font f = g.getFont();
		Stroke s = g.getStroke();
		Color c = g.getColor();

		g.setStroke(new BasicStroke(currentLineWidth));
		g.setColor(currentColor);

		Point previousPoint = null;

		synchronized (points) {
			for (Point t : points) {
				if (previousPoint != null) {
					g.drawLine(previousPoint.x + x0, h - previousPoint.y, t.x
							+ x0, h - t.y);
				}
				previousPoint = t;

			}
		}

		g.setFont(f);
		g.setStroke(s);
		g.setColor(c);

	}

	public void addElement(Point t) {
		checkNull(t);

		points.add(t);
	}

	public void clear() {
		points.removeAll(points);

	}

}
