package grapher;

import java.awt.*;

import modulation.MainFrame;


public class Screen extends Canvas {
	
	// Canvas
	private Drawing graphCanvas;
	private static final int BORDER_PERCENTAGE = 5;
	private static final double XMIN = -0.2;
	private static final double XMAX = Math.PI * 3;
	private static final double DELTAX = Math.PI / 1000; 
	private static final double YMIN = -1;
	private static final double YMAX = 1; 
	
	// mainFrame
	private MainFrame mainFrame;

	private Drawing graph;
	
	Graphics2D g;

	public Screen(MainFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
		setBackground(Color.WHITE);
		graphCanvas = new Drawing(this, BORDER_PERCENTAGE, XMIN, XMAX, YMIN, YMAX);
	}

	public void paint(Graphics graphics) {
		double xval, yval;
		
		g = (Graphics2D)graphics;
		// draw the coordinate system
		graphCanvas.draw_coord(g, mainFrame.isGrid_on());

		// draw a new line
		graphCanvas.newLine();  // begin a new line
		xval = 0.0;
		
		// selects the waveform
		switch (mainFrame.getMode()) {
		case 1:
			while (xval <= XMAX - DELTAX) {
				if (mainFrame.isModulate()) {
					yval = Math.sin(xval*mainFrame.getFrequency())*mainFrame.getAmplitude()*Math.sin(xval*mainFrame.getFrequency())*mainFrame.getAmplitude()*Math.sin(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
				} else {
					yval = Math.sin(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
				}
				graphCanvas.nextLine(g, xval, yval);
				xval += DELTAX;
			}
			break;
		case 2:
			while (xval <= XMAX - DELTAX) {
				yval = Math.cos(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
				graphCanvas.nextLine(g, xval, yval);
				xval += DELTAX;
			}
			break;
		}
	}
}
