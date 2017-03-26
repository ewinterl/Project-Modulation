package modulation;

import java.awt.*;
import javax.swing.*;


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
	 */
	public static void main(String[] args) {
		String OS;
		String title = "Reaction Tester";
		String versionID = "Version 4.5.4";
		
		// detect current OS
		new OSUtils();
		OS = OSUtils.getOsName().toLowerCase();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Application(OS, title, versionID);
			}
		});
	}
}
