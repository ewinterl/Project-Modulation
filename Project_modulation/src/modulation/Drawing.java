package modulation;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Drawing extends Canvas {
	int mode = 0;
	double freq = 1;
	double freqold = freq;
	double amplitude = 1;
	int nPoints = 1000;
	int[] xPoints = new int[nPoints+1];
	int[] yPoints = new int[nPoints+1];
	int[] xPointsHold = new int[nPoints+1];
	int[] yPointsHold = new int[nPoints+1];
	int[] xTriangle1 = new int[4];
	int[] yTriangle1 = new int[4];
	int[] xTriangle2 = new int[4];
	int[] yTriangle2 = new int[4];
	int[] xTriangle3 = new int[4];
	int[] yTriangle3 = new int[4];
	
	int x_range;
	int pixelrange_x;
	int pixelrange_y;
	int border = 5; 				//in %
	int pixborder_x;				//in pixel
	int pixborder_y;				//in pixel
	int drawingarea_x;
	int drawingarea_y;
		
	public Drawing(){
		super();
		setBackground(Color.white);
	}
	
	public void paint(Graphics g){
		pixborder_x = (border*getWidth())/100;
		pixborder_y = (border*getHeight())/100;
		
		//Kordinaten Sytem
		calcCordinates();
		g.setColor(Color.black);
		g.drawLine(pixborder_x-5, this.getHeight()/2, this.getWidth()-pixborder_x+5, this.getHeight()/2);
		g.drawLine(pixborder_x, pixborder_y-5, pixborder_x, this.getHeight()-pixborder_y+5);
		g.drawLine(this.getWidth()-pixborder_x, this.getHeight()/2-5, this.getWidth()-pixborder_x, this.getHeight()/2+5);
		g.drawLine(pixborder_x-5, pixborder_y, pixborder_x+5, pixborder_y);
		g.drawLine(pixborder_x-5, this.getHeight()-pixborder_y, pixborder_x+5, this.getHeight()-pixborder_y);
		g.drawPolyline(xTriangle1, yTriangle1, 4);
		g.drawPolyline(xTriangle2, yTriangle2, 4);
		g.drawPolyline(xTriangle3, yTriangle3, 4);
		//Frame
		//g.drawRect(pixborder_x, pixborder_y, this.getWidth()-2*pixborder_x, this.getHeight()-2*pixborder_y);

		function_calc(freq);
		
		//Funktion
		g.setColor(Color.red);
		g.drawPolyline(xPoints, yPoints, nPoints);
		
		freqold = freq;
		xPointsHold = xPoints;
		yPointsHold = yPoints;
	}
	
	private void calcCordinates() {
		xTriangle1[0] = pixborder_x-5;
		xTriangle1[1] = pixborder_x+5;
		xTriangle1[2] = pixborder_x;
		xTriangle1[3] = pixborder_x-5;
		yTriangle1[0] = pixborder_y-5;
		yTriangle1[1] = pixborder_y-5;
		yTriangle1[2] = pixborder_y-20;
		yTriangle1[3] = pixborder_y-5;
		
		xTriangle2[0] = pixborder_x-5;
		xTriangle2[1] = pixborder_x+5;
		xTriangle2[2] = pixborder_x;
		xTriangle2[3] = pixborder_x-5;
		yTriangle2[0] = this.getHeight()-pixborder_y+5;
		yTriangle2[1] = this.getHeight()-pixborder_y+5;
		yTriangle2[2] = this.getHeight()-pixborder_y+20;
		yTriangle2[3] = this.getHeight()-pixborder_y+5;
		
		xTriangle3[0] = this.getWidth()-pixborder_x+5;
		xTriangle3[1] = this.getWidth()-pixborder_x+5;
		xTriangle3[2] = this.getWidth()-pixborder_x+15;
		xTriangle3[3] = this.getWidth()-pixborder_x+5;
		yTriangle3[0] = this.getHeight()/2-5;
		yTriangle3[1] = this.getHeight()/2+5;
		yTriangle3[2] = this.getHeight()/2;
		yTriangle3[3] = this.getHeight()/2-5;
	}

	public void function_calc(double f){
		for(int i = 0; i <= nPoints; i++){
			double xPoint = i;
			double yPoint = 0;
			xPoints[i] = translate_xpos(xPoint);
			
			if(mode == 0){												//calculate sinus
				yPoint = (-amplitude)*Math.sin(((2*Math.PI))*i*f);
			} else if (mode == 1){										//calculate cosinus
				yPoint = (-amplitude)*Math.cos(((2*Math.PI))*i*f);
			} else if (mode == 2){										//calculate rect
				double buff = 0;
				
				for(int j = 1; j <= 10500; j = j+2){
					yPoint += Math.sin(((j*2*Math.PI))*i*f)/j;
				}
				yPoint*= -amplitude;
			}
			yPoints[i] = translate_ypos(yPoint);
		}
	}
	
	public int translate_xpos(double xPoint){
		xPoint = (xPoint/nPoints)*(getWidth()-(2*pixborder_x))+pixborder_x;
		return (int)xPoint;
	}
	
	public int translate_ypos(double yPoint){
		yPoint = yPoint*(getHeight()/2-pixborder_y)+getHeight()/2;
		return (int)yPoint;
	}
	
	public void setParameter(int mode, int freq, int ampl){
		this.mode = mode;
		this.freq = freq*0.01;
		this.amplitude = ampl*0.01;
		System.out.println(this.freq);
	}
}
