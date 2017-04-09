package grapher;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Drawing {
	// Surrounding of the painting
	private Component base;
	// Edge area
	private int border_percentage;
	// values to restrict canvas area
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	// Flag for a new line
	private boolean linestart = true;
	// stores last painted point
	private int xLast;
	private int yLast;

	public Drawing(Component base, int border_percentage, double xmin, double xmax, double ymin, double ymax) {
		this.base = base;
		this.border_percentage = border_percentage;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}

	public Drawing(Component base) {
		this(base, 5, -1, 1, -1, 1);
	}

	private int xcoord_to_pixel(double x_coord) {
		int gesamtpixel = base.getWidth();
		int pix_rand = (gesamtpixel * border_percentage) / 100;
		int pix_zeichen = gesamtpixel - 2 * pix_rand;
		if (x_coord < xmin) {
			return -1;
		} else if (x_coord > xmax) {
			return -1;
		} else {
			double xcoord_relat = x_coord - xmin;
			double x_bereich = xmax - xmin;
			int pix = (int) Math.round((double) pix_zeichen * xcoord_relat / x_bereich);
			int pix_gesamt = pix_rand + pix;
			return pix_gesamt;
		}
	}

	private int ycoord_to_pixel(double y_coord) {
		int gesamtpixel = base.getHeight();
		int pix_rand = (gesamtpixel * border_percentage) / 100;
		int pix_zeichen = gesamtpixel - 2 * pix_rand;
		if (y_coord < ymin) {
			return -1;
		} else if (y_coord > ymax) {
			return -1;
		} else {
			double ycoord_relat = y_coord - ymin;
			double y_bereich = ymax - ymin;
			int pix = (int) Math.round((double) pix_zeichen * ycoord_relat / y_bereich);
			int pix_gesamt = pix_rand + (pix_zeichen - pix);
			return pix_gesamt;
		}
	}

	private void drawSingleLine(Graphics g, double x0, double y0, double x1, double y1) {
		int xpix0 = xcoord_to_pixel(x0);
		int xpix1 = xcoord_to_pixel(x1);
		int ypix0 = ycoord_to_pixel(y0);
		int ypix1 = ycoord_to_pixel(y1);

		g.drawLine(xpix0, ypix0, xpix1, ypix1);
	}

	public void draw_coord(Graphics2D g, boolean grid) {
		double xPos;
		double xMark;
		double yMark;

		g.setColor(Color.black);
		
		g.setStroke(new BasicStroke(1));
		
		// draw the coordinate system
		drawSingleLine(g, 0.0, ymax, 0.0, ymin);
		drawSingleLine(g, xmin, 0.0, xmax, 0.0);
		
		if (grid) {
			// draw grid
			xMark = (xmax - xmin) / 200.0;
			drawSingleLine(g, -xMark, 1.0, xmax, 1.0);
			drawSingleLine(g, -xMark, 0.5, xmax, 0.5);
			drawSingleLine(g, -xMark, -0.5, xmax, -0.5);
			drawSingleLine(g, -xMark, -1.0, xmax, -1.0);
			yMark = (ymax - ymin) / 100.0;
			xPos = Math.PI / 2.0;
			while (xPos < xmax) {
				drawSingleLine(g, xPos, ymin, xPos, ymax);
				if (xPos > 6 && xPos < 7) { 
					g.drawString("2π", xcoord_to_pixel(xPos)-10, ycoord_to_pixel(yMark-ymax/10));
				}
				xPos += Math.PI / 2;
			}
		} else {
			// draw the scaling marks
			xMark = (xmax - xmin) / 200.0;
			drawSingleLine(g, -xMark, 1.0, xMark, 1.0);
			drawSingleLine(g, -xMark, 0.5, xMark, 0.5);
			drawSingleLine(g, -xMark, -0.5, xMark, -0.5);
			drawSingleLine(g, -xMark, -1.0, xMark, -1.0);
			yMark = (ymax - ymin) / 100.0;
			xPos = Math.PI / 2.0;
			while (xPos < xmax) {
				drawSingleLine(g, xPos, yMark, xPos, -yMark);
				if (xPos > 6 && xPos < 7) { 
					g.drawString("2π", xcoord_to_pixel(xPos)-10, ycoord_to_pixel(yMark-ymax/10));
				}
				xPos += Math.PI / 2;
			}
		}
	}

	// begin a new line
	public void newLine() {
		linestart = true;
	}

	// connect new point
	public void nextLine(Graphics2D g, double x, double y) {
		int x_pix = xcoord_to_pixel(x);
		int y_pix = ycoord_to_pixel(y);
		if (x_pix >= 0 && y_pix >= 0) {  // only draw if point is valid
			g.setColor(Color.red);
			if (linestart) {
				linestart = false;
			} else {
				g.setStroke(new BasicStroke(2));
				g.drawLine(xLast, yLast, x_pix, y_pix);
			}
			xLast = x_pix;
			yLast = y_pix;
		}
	}
}
