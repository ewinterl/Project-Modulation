package modulation;

public class Abs2PixelCoord {
int nPoints;		// amount of points on x - axis
int width;
int height;
int pixBorder_x;
int pixBorder_y;
	
	public int translate_xpos(double xPoint) {
		xPoint = (xPoint / nPoints) * (width - (2 * pixBorder_x)) + pixBorder_x;
		return (int) xPoint;
	}

	public int translate_ypos(double yPoint) {
		yPoint = yPoint * (height / 2 - pixBorder_y) + height / 2;
		return (int) yPoint;
	}
	
	public void setParam (int nPoints, int width, int height, int pixBorder_x, int pixBorder_y) {
		this.nPoints = nPoints;
		this.width = width;
		this.height = height; 
		this.pixBorder_x = pixBorder_x;
		this.pixBorder_y = pixBorder_y;
	}
}
