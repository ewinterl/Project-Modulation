package modulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Listeners.Button_Listener_MenuOne;
import Listeners.Button_Listener_MenuThree;
import Listeners.Button_Listener_MenuTwo;
import dialog.AboutDialog;
import mac.OSXSetup;

public class MainFrame extends JFrame {
	// Panels
	private JPanel iconPanel = new JPanel(); // top left icon panel
	private JPanel topPanel = new JPanel(); // top panel for controls
	private JPanel leftPanel = new JPanel(); // left panel for functions

	// Menu Bar
	private JMenuBar menuBar = new JMenuBar();
	// Menu
	private JMenu menuControl = new JMenu("Project");
	private JMenu menuLevel = new JMenu("Modulation");
	private JMenu menuInfo = new JMenu("Help");

	// Submenu
	private JMenuItem menuItemControlResume = new JMenuItem("Whats");
	private JMenuItem menuItemControlPause = new JMenuItem("up?");
	private JCheckBoxMenuItem menuItemLevelEasy = new JCheckBoxMenuItem("Fu", true);
	private JCheckBoxMenuItem menuItemLevelMedium = new JCheckBoxMenuItem("Ck");
	private JCheckBoxMenuItem menuItemLevelHard = new JCheckBoxMenuItem("YOU");
	private JMenuItem menuItemInfoAbout = new JMenuItem("lulz");

	// App info
	private String OS;
	private String title;
	private String versionID;

	// Buttons
	private JButton buttonSine = new JButton("Sine");
	private JButton buttonCosine = new JButton("Cosine");
	private JButton buttonRect = new JButton("Rect");

	// Slider
	private JSlider jslid_freq = new JSlider(1, 10000, 36);
	private JSlider jslid_ampl = new JSlider(10, 100, 100);

	// Splitpanes
	JSplitPane sp_main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane sp_left = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane sp_right = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	private Drawing drawing = new Drawing();;
	private int mode = 0;
	private JPanel contentPane;

	public MainFrame(String oS, String appTitle, String appVersion) {
		OS = oS;
		title = appTitle;
		versionID = appVersion;
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			FrameInit();
		} catch (Exception exception) {
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
			 * menuInfo.add(menuItemInfoAbout); aboutDialog = new
			 * AboutDialog(this, title, true, versionID);
			 */
		} else {
			// Cross platform options
			/*
			 * UIManager.setLookAndFeel(UIManager.
			 * getCrossPlatformLookAndFeelClassName());
			 * menuInfo.add(menuItemInfoAbout); aboutDialog = new
			 * AboutDialog(this, title, true, versionID);
			 */
		}

		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		setMinimumSize(new Dimension(900, 600));
		setTitle("Modulation Demo");

		// Splitpanes
		contentPane.add(sp_main, BorderLayout.CENTER);
		sp_main.add(sp_left, JSplitPane.LEFT);
		sp_main.add(sp_right, JSplitPane.RIGHT);
		sp_main.setDividerLocation(150);
		sp_main.setEnabled(false);
		sp_left.add(iconPanel, JSplitPane.TOP);
		sp_left.add(leftPanel, JSplitPane.BOTTOM);
		sp_left.setDividerLocation(getHeight() / 4);
		sp_left.setEnabled(false);
		sp_right.add(topPanel, JSplitPane.TOP);
		sp_right.add(drawing, JSplitPane.BOTTOM);
		sp_right.setDividerLocation(getHeight() / 5);
		sp_right.setEnabled(false);

		// set layout
		iconPanel.setLayout(new BorderLayout());
		topPanel.setLayout(new GridLayout(4, 1));
		leftPanel.setLayout(new GridLayout(6, 1));

		// left Panel options
		buttonSine.addActionListener(new ButtonListener());
		buttonCosine.addActionListener(new ButtonListener());
		buttonRect.addActionListener(new ButtonListener());
		leftPanel.add(buttonSine);
		leftPanel.add(buttonCosine);
		leftPanel.add(buttonRect);

		// top Panel options
		jslid_freq.addChangeListener(new SliderListener());
	    jslid_ampl.addChangeListener(new SliderListener());
		topPanel.add(jslid_freq);
		topPanel.add(jslid_ampl);

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
	}

	public class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			redraw();
		}
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			ButtonHandler(ae);
		}
	}

	public void ButtonHandler(ActionEvent ae) {
		if (ae.getSource() == buttonSine) {
			// function.setText("sinus");
			mode = 0;
			redraw();
		} else if (ae.getSource() == buttonCosine) {
			// function.setText("cosinus");
			mode = 1;
			redraw();
		} else if (ae.getSource() == buttonRect) {
			// function.setText("rect");
			mode = 2;
			redraw();
		}
	}

	public void redraw() {
		drawing.setParameter(mode, jslid_freq.getValue(), jslid_ampl.getValue());
		double freq = Math.round(jslid_freq.getValue() * 0.001 / 0.36 * 100) / 100.00;
		// function_freq.setText(freq + " Hz");
		// function_ampl.setText("Amplitude: " + jslid_ampl.getValue() / 100);
		drawing.repaint();
	}
}
