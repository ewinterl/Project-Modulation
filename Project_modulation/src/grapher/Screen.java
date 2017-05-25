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
		double xval;
		double yval;

		g = (Graphics2D)graphics;
		// draw the coordinate system
		graphCanvas.draw_coord(g, mainFrame.getCb_grid());

		// draw a new line
		graphCanvas.newLine();  // begin a new line
		xval = 0.0;

		// selects the waveform
		while (xval <= XMAX - DELTAX) {
			if (mainFrame.isModulate()) {
				switch (mainFrame.getMode()) {
				case 0:
					yval = Math.sin(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
					break;
				case 1:
					yval = Math.cos(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
					break;
				default:
					yval = 0;
					break;
				}
				System.out.println(mainFrame.getModeCar());
				switch (mainFrame.getModeCar()) {
				case 0: 
					yval *= Math.sin(xval*mainFrame.getFrequencyCarrier())*mainFrame.getAmplitudeCarrier();
					break;
				case 1: 
					yval *= Math.cos(xval*mainFrame.getFrequencyCarrier())*mainFrame.getAmplitudeCarrier();
				}
			} else {
				switch (mainFrame.getMode()) {
				case 0:
					yval = Math.sin(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
					break;
				case 1:
					yval = Math.cos(xval*mainFrame.getFrequency())*mainFrame.getAmplitude();
					break;
				default:
					yval = 0;
					break;
				}
			}
			graphCanvas.nextLine(g, xval, yval);
			xval += DELTAX;
		}
	}
}
