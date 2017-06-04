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
	private static final double PI = Math.PI;

	// mainFrame
	private MainFrame mainFrame;

	// private Drawing graph;

	Graphics2D g;

	private double period;
	private double periodCar;

	public Screen(MainFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
		setBackground(Color.WHITE);
		graphCanvas = new Drawing(this, BORDER_PERCENTAGE, XMIN, XMAX, YMIN, YMAX);
	}

	public void paint(Graphics graphics) {
		double xval;
		double yval;
		double ylast = 1;
		double ycheck;
		double ylastCar = 1;
		double ycheckCar;
		int inv = -1;

		g = (Graphics2D) graphics;
		// draw the coordinate system
		graphCanvas.draw_coord(g, mainFrame.getCb_grid());

		// draw a new line
		graphCanvas.newLine(); // begin a new line
		xval = 0.0;

		period = 1 / mainFrame.getFrequency() * 2 * PI;
		periodCar = 1 / mainFrame.getFrequencyCarrier() * 2 * PI;

		// selects the waveform
		while (xval <= XMAX - DELTAX) {
			switch (mainFrame.getMode()) {
			case 0:
				yval = Math.sin(xval * mainFrame.getFrequency()) * mainFrame.getAmplitude();
				break;
			case 1:
				yval = Math.cos(xval * mainFrame.getFrequency()) * mainFrame.getAmplitude();
				break;
			case 2:
				yval = (xval + period / 2F) % period / period - 0.5F;
				yval *= mainFrame.getAmplitude() * 2;
				break;
			case 3:
				ycheck = (xval + period / 2F) % period / period - 0.5F;
				ycheck *= mainFrame.getAmplitude() * 2;
				if (ycheck < ylast) {
					inv *= -1;
				}
				yval = ycheck * inv;;
				ylast = ycheck;
				break;
			default:
				yval = 0;
				break;
			}
			if (mainFrame.isModulate()) {
				switch (mainFrame.getModeCar()) {
				case 0:
					yval *= Math.sin(xval * mainFrame.getFrequencyCarrier()) * mainFrame.getAmplitudeCarrier();
					break;
				case 1:
					yval *= Math.cos(xval * mainFrame.getFrequencyCarrier()) * mainFrame.getAmplitudeCarrier();
					break;
				case 2:
					yval *= (xval + periodCar / 2F) % periodCar / periodCar - 0.5F;
					yval *= mainFrame.getAmplitude() * 2;
				case 3:
					ycheckCar = (xval + periodCar / 2F) % periodCar / (periodCar) - 0.5F;
					ycheckCar *= mainFrame.getAmplitudeCarrier() * 2;
					if (ycheckCar < ylastCar) {
						inv *= -1;
					}
					yval *= ycheckCar * inv;;
					ylastCar = ycheckCar;
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
