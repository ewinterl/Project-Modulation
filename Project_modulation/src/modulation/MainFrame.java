package modulation;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Listeners.Button_Listener_MenuOne;
import Listeners.Button_Listener_MenuThree;
import Listeners.Button_Listener_MenuTwo;
import dialog.AboutDialog;
import mac.OSXSetup;

public class MainFrame extends JFrame {
	// Panels
	JPanel iconPanel = new JPanel(); 		// top left icon panel
	JPanel topPanel = new JPanel();			// top panel for controls
	JPanel leftPanel = new JPanel();		// left panel for functions
	JPanel mainPanel = new JPanel();		// main panel for graphics
	
	// Menu Bar
	JMenuBar menuBar = new JMenuBar();

	// Menu
	JMenu menuControl = new JMenu("Project");
	JMenu menuLevel = new JMenu("Modulation");
	JMenu menuInfo = new JMenu("Help");

	// Submenu
	JMenuItem menuItemControlResume = new JMenuItem("Whats");
	JMenuItem menuItemControlPause = new JMenuItem("up?");
	JCheckBoxMenuItem menuItemLevelEasy = new JCheckBoxMenuItem("Fu", true);
	JCheckBoxMenuItem menuItemLevelMedium = new JCheckBoxMenuItem("Ck");
	JCheckBoxMenuItem menuItemLevelHard = new JCheckBoxMenuItem("YOU");
	JMenuItem menuItemInfoAbout = new JMenuItem("lulz");

	// App info
	String OS;
	String title;
	String versionID;
	
	// Buttons
	JButton buttonSine = new JButton("Sine");
	JButton buttonCosine = new JButton("Cosine");
	
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

		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new GridLayout(2,2));
		setSize(new Dimension(400, 300));
		setTitle("Modulation Demo");
		
		// set layout 
		iconPanel.setLayout(new BorderLayout());
		topPanel.setLayout(new GridLayout());
		leftPanel.setLayout(new GridLayout(2,1));
		mainPanel.setLayout(new BorderLayout());

		// place Menu
		setJMenuBar(menuBar);

		// add Menu
		menuBar.add(menuControl);
		menuBar.add(menuLevel);
		menuBar.add(menuInfo);

		// add Submenu
		menuControl.add(menuItemControlResume);
		menuControl.add(menuItemControlPause);
		menuLevel.add(menuItemLevelEasy);
		menuLevel.add(menuItemLevelMedium);
		menuLevel.add(menuItemLevelHard);

		// menu listener control
		Button_Listener_MenuOne mListenerControl = new Button_Listener_MenuOne();
		menuItemControlResume.addActionListener(mListenerControl);
		menuItemControlPause.addActionListener(mListenerControl);

		// menu listener control
		Button_Listener_MenuTwo mListenerLevel = new Button_Listener_MenuTwo();
		menuItemLevelEasy.addActionListener(mListenerLevel);
		menuItemLevelMedium.addActionListener(mListenerLevel);
		menuItemLevelHard.addActionListener(mListenerLevel);

		// menu listener RT
		Button_Listener_MenuThree mListenerRT = new Button_Listener_MenuThree();
		menuItemInfoAbout.addActionListener(mListenerRT);
		
		
		// left Panel options
		leftPanel.add(buttonSine);
		leftPanel.add(buttonCosine);
		
		contentPane.add(iconPanel);
		contentPane.add(topPanel);
		contentPane.add(leftPanel);
		contentPane.add(mainPanel);
	}
}

