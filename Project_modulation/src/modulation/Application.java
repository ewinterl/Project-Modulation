package modulation;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class Application {

	/**
	 * Construct and show the application.
	 */
	public Application(String OS, String title, String versionID) {
		MainFrame frame = new MainFrame(OS, title, versionID);
		// Validate frames that have preset sizes
		frame.validate();



		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation( (screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}

	/**
	 * Application entry point.
	 *
	 * @param args String[]
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvocationTargetException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		String OS;
		String title = "Modulater";
		String versionID = "Version x";

		// detect current OS
		new OSUtils();
		OS = OSUtils.getOsName().toLowerCase();

		final SplashScreen splash = SplashScreen.getSplashScreen();
		if (splash == null) {
			System.out.println("SplashScreen.getSplashScreen() returned null");
		} else {
			Graphics2D g = splash.createGraphics();
			//System.out.println(splash.equals(ImageIO.read(getClass().getResource("../Resources/splash_last.png"))));
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Application(OS, title, versionID);
			}
		});
	}
}
