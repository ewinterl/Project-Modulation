package modulation;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import os.OSXSetup;

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
		String title = "GraphApp";
		String versionID = "0.9.3 (Pre-Alpha)";

		// detect current OS
		OS = System.getProperty("os.name").toLowerCase();
		System.out.println(OS);
		if (OS.indexOf("mac") >= 0) {
			System.setProperty("apple.awt.application.name", "Modulater");
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}

		final SplashScreen splash = SplashScreen.getSplashScreen();
		if (splash == null) {
			System.out.println("SplashScreen.getSplashScreen() returned null");
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Application(OS, title, versionID);
			}
		});
	}
}
