package pictures;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveImage {
	private Component parent;
	private Canvas canvas;
	private String fname;
	private File file;

	public SaveImage(Canvas canvas) {
		super();
		this.canvas = canvas;
	}

	public void save() {
		
		// may change to fileDialog
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filterJPG = new FileNameExtensionFilter("JPG", "jpg");
		FileNameExtensionFilter filterPNG = new FileNameExtensionFilter("PNG", "png");
		chooser.setFileFilter(filterPNG);
		chooser.addChoosableFileFilter(filterJPG);
		chooser.addChoosableFileFilter(filterPNG);

		if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
			BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D) image.getGraphics();
			/*
			 * 
			 * use for background
			 * check for option to make transparent
			 * 
			 * g2.setColor(canvas.getBackground());
			 * g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			 */
			canvas.paint(g2);
			

			file = chooser.getSelectedFile();
			fname = file.getName();
			System.out.println(fname);
			if (fname.contains("."))
				fname = fname.substring(0, fname.lastIndexOf('.'));
			
			try {
				ImageIO.write(image, "png", new File(chooser.getCurrentDirectory() + System.getProperty("file.separator") + fname + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
