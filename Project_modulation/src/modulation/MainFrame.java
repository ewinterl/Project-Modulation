package modulation;

import java.awt.*;
import javax.swing.*;
import dialog.AboutDialog;
import mac.OSXSetup;

public class MainFrame extends JFrame {
	// Menu Bar
	JMenuBar menuBar = new JMenuBar();

	// Menu
	JMenu menuControl = new JMenu("Control");
	JMenu menuLevel = new JMenu("Level");
	JMenu menuInfo = new JMenu("Help");

	// Submenu
	JMenuItem menuItemControlResume = new JMenuItem("Resume");
	JMenuItem menuItemControlPause = new JMenuItem("Pause");
	JCheckBoxMenuItem menuItemLevelEasy = new JCheckBoxMenuItem("Easy", true);
	JCheckBoxMenuItem menuItemLevelMedium = new JCheckBoxMenuItem("Medium");
	JCheckBoxMenuItem menuItemLevelHard = new JCheckBoxMenuItem("Hard");
	JMenuItem menuItemInfoAbout = new JMenuItem("About");
	
	String OS;
	String title;
	String versionID;

	private JPanel contentPane;
	public MainFrame(String oS, String appTitle, String appVersion) {
		OS = oS; 
		title = appTitle;
		versionID = appVersion;
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			FrameInit();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 *
	 * @throws java.lang.Exception
	 */
	private void FrameInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		setSize(new Dimension(400, 300));
		setTitle("Modulation Demo");


		// Mac OS specific options
		if (OS.indexOf("mac") >= 0) {
			new OSXSetup(title, versionID);
		} else if (OS.indexOf("win") >= 0) {
			// windows options
			/*
			 * menuInfo.add(menuItemInfoAbout);
			 * aboutDialog = new AboutDialog(this, title, true, versionID);
			 */
		} else {
			// Cross platform options
			/*
			 * UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			 * menuInfo.add(menuItemInfoAbout);
			 * aboutDialog = new AboutDialog(this, title, true, versionID);
			 */
		}
	}
}
