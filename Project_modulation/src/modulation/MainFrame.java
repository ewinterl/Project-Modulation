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
	private JPanel contentPane;
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
	
	//Radio Buttons
	private JRadioButton rb_lowrange = new JRadioButton("1Hz - 100Hz");
	private JRadioButton rb_midrange = new JRadioButton("100Hz - 10kHz");
	private JRadioButton rb_highrange = new JRadioButton("10kHz - 1000kHz");
	private JRadioButton rb_grid = new JRadioButton("grid on");
	
	// Slider
	private JSlider jslid_freq = new JSlider(1000, 10000, 1000);
	private JSlider jslid_ampl = new JSlider(2, 100, 100);

	// Splitpanes
	JSplitPane sp_main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane sp_left = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane sp_right = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	
	private Drawing drawing = new Drawing();
	
	//Variables
	private int mode = 0;
	private int range = 0;
	private boolean grid_on = false;
	

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
		
		
		
		// Splitpanes options
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
		topPanel.setLayout(new GridLayout(3, 2));
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
	    rb_lowrange.addActionListener(new RadioButtonListener());
	    rb_midrange.addActionListener(new RadioButtonListener());
	    rb_highrange.addActionListener(new RadioButtonListener());
	    rb_grid.addActionListener(new RadioButtonListener());
	    rb_lowrange.setSelected(true);
	    topPanel.add(rb_lowrange);
	    topPanel.add(jslid_freq);
		topPanel.add(rb_midrange);
		topPanel.add(jslid_ampl);
		topPanel.add(rb_highrange);
		topPanel.add(rb_grid);

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
		
		redraw();
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
	
	public class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			RadioButtonHandler(ae);
		}
	}

	public void RadioButtonHandler(ActionEvent ae) {
		if (ae.getSource() == rb_lowrange) {
			rb_midrange.setSelected(false);
			rb_highrange.setSelected(false);
			range = 1;
			redraw();
		} else if (ae.getSource() == rb_midrange) {
			rb_lowrange.setSelected(false);
			rb_highrange.setSelected(false);
			range = 10;
			redraw();
		} else if (ae.getSource() == rb_highrange) {
			rb_lowrange.setSelected(false);
			rb_midrange.setSelected(false);
			range = 100;
			redraw();
		} else if (ae.getSource() == rb_grid) {
			if(rb_grid.isSelected()){
				grid_on = true;
			} else{
				grid_on = false;
			}
			redraw();
		}
	}

	public void redraw() {
		drawing.setParameter(mode, jslid_freq.getValue()*range, jslid_ampl.getValue(), grid_on);
		//double freq = Math.round(jslid_freq.getValue()*0.01)*range;
		// function_freq.setText(freq + " Hz");
		// function_ampl.setText("Amplitude: " + jslid_ampl.getValue() / 100);
		drawing.repaint();
	}
}
