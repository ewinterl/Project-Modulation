package modulation;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import modulation.ImageSaver;

public class SaveImage {
	private Component parent;
	private Canvas canvas;
	private String fname;
	private File file;

	public SaveImage(Canvas canvas) {
		super();
		this.canvas = canvas;
	}

	public void Save(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filterJPG = new FileNameExtensionFilter("JPG", "jpg");
		FileNameExtensionFilter filterPNG = new FileNameExtensionFilter("PNG", "png" );
		chooser.addChoosableFileFilter(filterJPG);
		chooser.addChoosableFileFilter(filterPNG);
	
		if(chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
			BufferedImage image=new BufferedImage(canvas.getWidth(), canvas.getHeight(),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2=(Graphics2D)image.getGraphics();
			canvas.paint(g2);
			try {
				file = chooser.getSelectedFile();
				fname = file.getName();
				if (fname.contains("."))
					fname = fname.substring(0, fname.lastIndexOf('.'));
				ImageIO.write(image, "png", new File(file + ".png"));
				
			} catch (Exception e) {
				
			}
		}
	}
}

