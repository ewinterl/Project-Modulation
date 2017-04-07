package modulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Listeners.Button_Listener_MenuMode;
import Listeners.Button_Listener_MenuThree;
import Listeners.Button_Listener_MenuTwo;
import grapher.Screen;
import mac.OSXSetup;

public class MainFrame extends JFrame implements ModeChanger {
	// Panels
	private JPanel contentPane;
	private JPanel iconPanel = new JPanel(); // top left icon panel
	private JPanel topPanel = new JPanel(); // top panel for controls
	private JPanel leftPanel = new JPanel(); // left panel for functions

	// Menu Bar
	private JMenuBar menuBar = new JMenuBar();
	
	// Menu
	private JMenu menuMode = new JMenu("Mode");
	private JMenu menuLevel = new JMenu("Modulation");
	private JMenu menuInfo = new JMenu("Help");

	// Submenu
	private JCheckBoxMenuItem  menuItemModeMod = new JCheckBoxMenuItem ("Modulation", true);
	private JCheckBoxMenuItem  menuItemModePlot = new JCheckBoxMenuItem ("Plotter");
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
	private JRadioButton rb_lowrange = new JRadioButton("1Hz - 10Hz");
	private JRadioButton rb_midrange = new JRadioButton("10Hz - 100Hz");
	private JRadioButton rb_highrange = new JRadioButton("100Hz - 1kHz");
	private JRadioButton rb_grid = new JRadioButton("grid on");
	
	// Slider
	private JSlider jslid_freq = new JSlider(1, 10, 1);
	private JSlider jslid_ampl = new JSlider(0, 100, 100);

	// Splitpanes
	JSplitPane sp_main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane sp_left = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane sp_right = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	// Surroundings for Canvas
	Screen mainCanvas;
	
	//Variables
	private int mode = 0;
	private int range = 1;
	private int frequency = 1;
	private double amplitude = 1;
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
		
		mainCanvas = new Screen(this);
		
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
		sp_right.add(mainCanvas, JSplitPane.BOTTOM);
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
	    rb_lowrange.setEnabled(true);
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
		menuBar.add(menuMode);
		menuBar.add(menuLevel);
		menuBar.add(menuInfo);
		// add Submenu
		menuMode.add(menuItemModeMod);
		menuMode.add(menuItemModePlot);
		// menu listener control
		Button_Listener_MenuMode mListenerControl = new Button_Listener_MenuMode(menuItemModeMod, menuItemModePlot, this);
		menuItemModeMod.addActionListener(mListenerControl);
		menuItemModePlot.addActionListener(mListenerControl);
		// menu listener control
		Button_Listener_MenuTwo mListenerLevel = new Button_Listener_MenuTwo();
		// menu listener RT
		Button_Listener_MenuThree mListenerRT = new Button_Listener_MenuThree();
		menuItemInfoAbout.addActionListener(mListenerRT);
		
		redraw();
	}

	public class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == jslid_freq) {
				frequency = jslid_freq.getValue();
			} else {
				amplitude = jslid_ampl.getValue()*0.01;
			}
			redraw();
		}
	}

	public double getAmplitude() {
		return amplitude;
	}

	public int getFrequency() {
		return frequency*range;
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			ButtonHandler(ae);
		}
	}

	public void ButtonHandler(ActionEvent ae) {
		if (ae.getSource() == buttonSine) {
			// function.setText("sinus");
			mode = 1;
			redraw();
		} else if (ae.getSource() == buttonCosine) {
			// function.setText("cosinus");
			mode = 2;
			redraw();
		} else if (ae.getSource() == buttonRect) {
			// function.setText("rect");
			mode = 3;
			redraw();
		}
	}
	
	public int getMode() {
		return mode;
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
		mainCanvas.repaint();
	}

	@Override
	public void setMenu(Object Menu) {
		if (Menu == menuItemModeMod) {
			menuItemModePlot.setSelected(false);
		} else if (Menu == menuItemModePlot) {
			menuItemModeMod.setSelected(false);
		}
		
	}
}
