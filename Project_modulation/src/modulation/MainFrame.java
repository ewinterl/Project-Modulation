package modulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Listeners.Button_Listener_MenuFile;
import Listeners.Button_Listener_MenuMode;
import Listeners.Button_Listener_MenuThree;
import grapher.Screen;
import os.OSXSetup;

public class MainFrame extends JFrame implements ModeChanger, ImageSaver {
	// Panels
	private JPanel contentPane;
	private JPanel topPanelSignal = new JPanel(); // top panel for controls
	private JPanel topLeftSignalPanel = new JPanel(); // topLeft for freq range
	private JPanel topMidSignalPanel = new JPanel(); // topMid for text
	private JPanel topRightSignalPanel = new JPanel(); // topRight for sliders +
	// grid
	private JPanel leftPanel = new JPanel(); // left panel for functions
	private JPanel textPanel = new JPanel(); // lower panel for text output
	private JPanel topPanelCarrier = new JPanel(); // top panel for controls
	private JPanel topLeftCarrierPanel = new JPanel(); // topLeft for freq range
	// of modulating signal
	private JPanel topMidCarrierPanel = new JPanel(); // topMid for text
	private JPanel topRightCarrierPanel = new JPanel(); // topRight for sliders
	// of modulating signal

	// Labels
	private JLabel functionName = new JLabel();
	private JLabel frequencyLabel = new JLabel();
	private JLabel amplitudeLabel = new JLabel();
	private JLabel signalLabel = new JLabel();
	private JLabel signalLabelCarrier = new JLabel();
	private JLabel functionNameCarrier = new JLabel();
	private JLabel frequencyLabelCarrier = new JLabel();
	private JLabel amplitudeLabelCarrier = new JLabel();
	private JLabel signalLabelLeft = new JLabel();
	private JLabel signalLabelCarrierLeft = new JLabel();

	// Menu Bar
	private JMenuBar menuBar = new JMenuBar();

	// Menu
	private JMenu menuFile = new JMenu("File");
	private JMenu menuMode = new JMenu("Mode");
	private JMenu menuLevel = new JMenu("Modulation");
	private JMenu menuInfo = new JMenu("Help");

	// Submenu
	private JCheckBoxMenuItem menuItemModeMod = new JCheckBoxMenuItem("Modulator", true);
	private JCheckBoxMenuItem menuItemModePlot = new JCheckBoxMenuItem("Plotter");
	private JMenuItem menuItemFileSave = new JMenuItem("Save");
	private JMenuItem menuItemInfoAbout = new JMenuItem("Windoof");

	// App info
	private String OS;
	private String title;
	private String versionID;

	// Buttons
	private JButton buttonSine = new JButton("Sine");
	private JButton buttonCosine = new JButton("Cosine");
	private JButton buttonSawtooth = new JButton("Sawtooth");
	private JButton buttonTriangle = new JButton("Triangle");
	private JButton buttonSineCarrier = new JButton("Sine");
	private JButton buttonCosineCarrier = new JButton("Cosine");
	private JButton buttonSawtoothCarrier = new JButton("Sawtooth");
	private JButton buttonTriangleCarrier = new JButton("Triangle");

	// Radio Buttons
	private ButtonGroup bg_rangeSignal = new ButtonGroup();
	private ButtonGroup bg_rangeCarrier = new ButtonGroup();
	private JRadioButton rb_lowrange = new JRadioButton("1Hz - 10Hz");
	private JRadioButton rb_highrange = new JRadioButton("10Hz - 100Hz");
	private JRadioButton rb_lowrangeCarrier = new JRadioButton("1Hz - 10Hz");
	private JRadioButton rb_highrangeCarrier = new JRadioButton("10Hz - 100Hz");

	// Checkbox
	private JCheckBox cb_grid = new JCheckBox("Display grid");
	private JCheckBox cb_fine = new JCheckBox("Set fine");

	// Slider
	private JSlider jslid_freq = new JSlider(1, 10, 1);
	private JSlider jslid_ampl = new JSlider(0, 100, 100);
	private JSlider jslid_freqCarrier = new JSlider(1, 10, 1);
	private JSlider jslid_amplCarrier = new JSlider(0, 100, 100);

	// Splitpanes
	JSplitPane sp_main = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JSplitPane sp_bottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	JSplitPane sp_top = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	// Surroundings for Canvas
	Screen mainCanvas;

	// Variables
	private int mode = 0;
	private int modeCar = 0;
	private int range = 1;
	private int rangeCarrier = 1;
	private int frequencyVal = 1;
	private int frequencyValCarrier = 1;

	private double frequency = 1;
	private double amplitude = 1;
	private double amplitudeCarrier = 1;
	private double frequencyCarrier = 1;

	private boolean modulate = true;
	private boolean fine = false;

	// Listeners
	Button_Listener_MenuMode mListenerControl;
	Button_Listener_MenuFile mListenerFile;
	Button_Listener_MenuThree mListenerRT;

	// Image
	private SaveImage ImageSaver;

	private GridBagConstraints gbc = new GridBagConstraints();

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
			menuItemModeMod.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_M, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			menuItemModePlot.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			menuItemFileSave.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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

		// set layout
		topPanelSignal.setLayout(new BorderLayout());
		topPanelCarrier.setLayout(new BorderLayout());
		topLeftSignalPanel.setLayout(new GridLayout(2, 2));
		topMidSignalPanel.setLayout(new BorderLayout());
		topRightSignalPanel.setLayout(new GridLayout(2, 2));
		leftPanel.setLayout(new GridLayout(12, 1));
		textPanel.setLayout(new GridLayout(2, 3));
		topLeftCarrierPanel.setLayout(new GridLayout(2, 2));
		topMidCarrierPanel.setLayout(new BorderLayout());
		topRightCarrierPanel.setLayout(new GridLayout(2, 2));

		mainCanvas = new Screen(this);

		// component options
		// slider
		// tool tip
		jslid_freq.setToolTipText("Change the Signal frequency");
		jslid_ampl.setToolTipText("Change the Signal amplitude");
		jslid_freqCarrier.setToolTipText("Change the Carrier Signal frequency");
		jslid_amplCarrier.setToolTipText("Change the Carrier Signal amplitude");
		// snap to tick marks
		jslid_freq.setSnapToTicks(true);
		jslid_freqCarrier.setSnapToTicks(true);
		// radio buttons
		// tool tip
		rb_lowrange.setSelected(true);
		rb_lowrangeCarrier.setSelected(true);
		rb_lowrange.setToolTipText("Set the slider range to 1 - 10 Hz");
		rb_highrange.setToolTipText("Set the slider range to 10 - 100 Hz");
		rb_lowrangeCarrier.setToolTipText("Set the slider range to 1 - 10 Hz");
		rb_highrangeCarrier.setToolTipText("Set the slider range to 10 - 100 Hz");
		// button group
		bg_rangeSignal.add(rb_lowrange);
		bg_rangeSignal.add(rb_highrange);
		bg_rangeCarrier.add(rb_lowrangeCarrier);
		bg_rangeCarrier.add(rb_highrangeCarrier);
		// labels
		signalLabel.setText("<html><b>Signal</b></html>");
		signalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signalLabelCarrier.setText("<html><b>Carrier</b></html>");
		signalLabelCarrier.setHorizontalAlignment(SwingConstants.CENTER);
		signalLabelLeft.setText("<html><b>Signal</b></html>");
		signalLabelCarrierLeft.setText("<html><b>Carrier</b></html>");
		// listeners
		// slider
		jslid_freq.addChangeListener(new SliderListener());
		jslid_ampl.addChangeListener(new SliderListener());
		jslid_amplCarrier.addChangeListener(new SliderListener());
		jslid_freqCarrier.addChangeListener(new SliderListener());
		// buttons
		buttonSine.addActionListener(new ButtonListener());
		buttonCosine.addActionListener(new ButtonListener());
		buttonSawtooth.addActionListener(new ButtonListener());
		buttonTriangle.addActionListener(new ButtonListener());
		buttonSineCarrier.addActionListener(new ButtonListenerCarrier());
		buttonCosineCarrier.addActionListener(new ButtonListenerCarrier());
		buttonSawtoothCarrier.addActionListener(new ButtonListenerCarrier());
		buttonTriangleCarrier.addActionListener(new ButtonListenerCarrier());
		rb_lowrange.addActionListener(new RadioButtonListener());
		rb_highrange.addActionListener(new RadioButtonListener());
		rb_lowrangeCarrier.addActionListener(new RadioButtonListener());
		rb_highrangeCarrier.addActionListener(new RadioButtonListener());
		cb_grid.addActionListener(new CheckBoxListener());
		cb_fine.addActionListener(new CheckBoxListener());
		// menu
		mListenerControl = new Button_Listener_MenuMode(menuItemModeMod, menuItemModePlot, this);
		mListenerFile = new Button_Listener_MenuFile(menuItemFileSave, this);
		mListenerRT = new Button_Listener_MenuThree();
		menuItemInfoAbout.addActionListener(mListenerRT);
		menuItemModeMod.addActionListener(mListenerControl);
		menuItemModePlot.addActionListener(mListenerControl);
		menuItemFileSave.addActionListener(mListenerFile);


		// splitpane options
		// main splitpane
		// sp_main.add(sp_left, JSplitPane.LEFT);
		sp_main.add(sp_bottom, JSplitPane.BOTTOM);
		sp_main.add(sp_top, JSplitPane.TOP);
		sp_main.setDividerLocation(150);
		sp_main.setDividerSize(1); // delete divider
		sp_main.setEnabled(false);
		// right splitpane
		sp_bottom.add(leftPanel, JSplitPane.LEFT);
		sp_bottom.add(mainCanvas, JSplitPane.RIGHT);
		sp_bottom.setDividerLocation(getHeight() / 4);
		sp_bottom.setEnabled(false);
		// top splitpane
		sp_top.add(topPanelCarrier, JSplitPane.TOP);
		sp_top.add(topPanelSignal, JSplitPane.BOTTOM);
		sp_top.setDividerLocation(getHeight() / 8 - 3);
		sp_top.setDividerSize(4); // make divider smaller
		sp_top.setEnabled(false);

		// left Panel options
		leftPanel.add(signalLabelLeft);
		leftPanel.add(buttonSine);
		leftPanel.add(buttonCosine);
		leftPanel.add(buttonSawtooth);
		leftPanel.add(buttonTriangle);
		leftPanel.add(signalLabelCarrierLeft);
		leftPanel.add(buttonSineCarrier);
		leftPanel.add(buttonCosineCarrier);
		leftPanel.add(buttonSawtoothCarrier);
		leftPanel.add(buttonTriangleCarrier);
		leftPanel.add(cb_grid);
		leftPanel.add(cb_fine);

		// top panel options
		// top left
		topLeftSignalPanel.add(new JLabel("<html><i>Frequency range: </i></html>"));
		topLeftSignalPanel.add(rb_lowrange);
		topLeftSignalPanel.add(new JLabel());
		topLeftSignalPanel.add(rb_highrange);
		// top mid
		topMidSignalPanel.add(signalLabel);
		// top right
		topRightSignalPanel.add(new JLabel("<html><i>Frequency trimmer</i></html>"));
		topRightSignalPanel.add(jslid_freq);
		topRightSignalPanel.add(new JLabel("<html><i>Amplitude trimmer</i></html>"));
		topRightSignalPanel.add(jslid_ampl);

		// carrier panel options
		// top left
		topLeftCarrierPanel.add(new JLabel("<html><i>Frequency range: </i></html>"));
		topLeftCarrierPanel.add(rb_lowrangeCarrier);
		topLeftCarrierPanel.add(new JLabel());
		topLeftCarrierPanel.add(rb_highrangeCarrier);
		// top mid
		topMidCarrierPanel.add(signalLabelCarrier);
		// top right
		topRightCarrierPanel.add(new JLabel("<html><i>Frequency trimmer</i></html>"));
		topRightCarrierPanel.add(jslid_freqCarrier);
		topRightCarrierPanel.add(new JLabel("<html><i>Amplitude trimmer</i></html>"));
		topRightCarrierPanel.add(jslid_amplCarrier);

		// add to top Signal
		topPanelSignal.add(topLeftSignalPanel, BorderLayout.WEST);
		topPanelSignal.add(topMidSignalPanel, BorderLayout.CENTER);
		topPanelSignal.add(topRightSignalPanel, BorderLayout.EAST);
		// add to top Carrier
		topPanelCarrier.add(topLeftCarrierPanel, BorderLayout.WEST);
		topPanelCarrier.add(topMidCarrierPanel, BorderLayout.CENTER);
		topPanelCarrier.add(topRightCarrierPanel, BorderLayout.EAST);

		// text panel options
		textPanel.add(functionName);
		textPanel.add(frequencyLabel);
		textPanel.add(amplitudeLabel);
		textPanel.add(functionNameCarrier);
		textPanel.add(frequencyLabelCarrier);
		textPanel.add(amplitudeLabelCarrier);

		// place Menu
		setJMenuBar(menuBar);
		// add Menu
		menuBar.add(menuFile);
		menuBar.add(menuMode);
		menuBar.add(menuLevel);
		menuBar.add(menuInfo);
		// add Submenu
		menuMode.add(menuItemModeMod);
		menuMode.add(menuItemModePlot);
		menuFile.add(menuItemFileSave);

		contentPane.add(sp_main, BorderLayout.CENTER);
		contentPane.add(textPanel, BorderLayout.SOUTH);

		ImageSaver = new SaveImage(mainCanvas);

		// draw a sine at start
		buttonSine.doClick();
		buttonSineCarrier.doClick();
	}

	public class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == jslid_freq) {
				frequencyVal = jslid_freq.getValue();
			} else if (e.getSource() == jslid_freqCarrier) {
				frequencyValCarrier = jslid_freqCarrier.getValue();
			} else if (e.getSource() == jslid_ampl) {
				amplitude = jslid_ampl.getValue() / 100.00;
			} else if (e.getSource() == jslid_amplCarrier) {
				amplitudeCarrier = jslid_amplCarrier.getValue() / 100.00;
			}

			redraw();
		}
	}

	public double getAmplitude() {
		return amplitude;
	}

	public double getFrequency() {
		return frequency;
	}

	public double getAmplitudeCarrier() {
		return amplitudeCarrier;
	}

	public double getFrequencyCarrier() {
		return frequencyCarrier;
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			ButtonHandler(ae);
		}
	}

	public void ButtonHandler(ActionEvent ae) {
		Object src = ae.getSource();
		buttonSine.setSelected(false);
		buttonCosine.setSelected(false);
		buttonSawtooth.setSelected(false);
		buttonTriangle.setSelected(false);

		if (src == buttonSine) {
			functionName.setText("Function: sine");
			buttonSine.setSelected(true);
			mode = 0;
		} else if (src == buttonCosine) {
			functionName.setText("Function: cosine");
			buttonCosine.setSelected(true);
			mode = 1;
		} else if (src == buttonSawtooth) {
			functionName.setText("Function: sawtooth");
			buttonSawtooth.setSelected(true);
			mode = 2;
		} else if (src == buttonTriangle) {
			functionName.setText("Function: triangle");
			buttonTriangle.setSelected(true);
			mode = 3;
		}
		redraw();
	}

	public class ButtonListenerCarrier implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			ButtonHandlerCarrier(ae);
		}
	}

	public void ButtonHandlerCarrier(ActionEvent ae) {
		Object src = ae.getSource();

		buttonSineCarrier.setSelected(false);
		buttonCosineCarrier.setSelected(false);
		buttonSawtoothCarrier.setSelected(false);
		buttonTriangleCarrier.setSelected(false);

		if (src == buttonSineCarrier) {
			functionNameCarrier.setText("Function: sine");
			buttonSineCarrier.setSelected(true);
			modeCar = 0;
		} else if (src == buttonCosineCarrier) {
			functionNameCarrier.setText("Function: cosine");
			buttonCosineCarrier.setSelected(true);
			modeCar = 1;
		} else if (src == buttonSawtoothCarrier) {
			functionNameCarrier.setText("Function: sawtooth");
			buttonSawtoothCarrier.setSelected(true);
			modeCar = 2;
		} else if (src == buttonTriangleCarrier) {
			functionNameCarrier.setText("Function: triangle");
			buttonTriangleCarrier.setSelected(true);
			modeCar = 3;
		}
		redraw();
	}

	public int getMode() {
		return mode;
	}

	public int getModeCar() {
		return modeCar;
	}

	public class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			RadioButtonHandler(ae);
		}

		private void RadioButtonHandler(ActionEvent ae) {
			if (ae.getSource() == rb_lowrange) {
				range = 1;
			} else if (ae.getSource() == rb_highrange) {
				range = 10;
			} else if (ae.getSource() == rb_lowrangeCarrier) {
				rangeCarrier = 1;
			} else if (ae.getSource() == rb_highrangeCarrier) {
				rangeCarrier = 10;
			}
			redraw();
		}
	}

	public class CheckBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			CheckBoxHandler(ae);
		}

		private void CheckBoxHandler(ActionEvent ae) {
			if (ae.getSource() == cb_grid) {
				redraw();
			} else {
				if (cb_fine.isSelected()) {
					fine = true;
					jslid_freq.setMaximum(1000);
					jslid_freq.setValue(frequencyVal * 100);
					jslid_freqCarrier.setMaximum(1000);
					jslid_freqCarrier.setValue(frequencyValCarrier * 100);
					jslid_freq.setSnapToTicks(false);
					jslid_freqCarrier.setSnapToTicks(false);
				} else {
					fine = false;
					jslid_freq.setValue((int) (frequencyVal * 0.01));
					jslid_freqCarrier.setValue((int) (frequencyValCarrier * 0.01));
					jslid_freq.setSnapToTicks(true);
					jslid_freqCarrier.setSnapToTicks(true);
					jslid_freq.setMaximum(10);
					jslid_freqCarrier.setMaximum(10);
				}
			}

		}
	}

	public void redraw() {
		frequency = frequencyVal * range;
		frequencyCarrier = frequencyValCarrier * rangeCarrier;
		if (fine == true) {
			frequency /= 100;
			frequencyCarrier /= 100;
		}
		setAmplitudeLabel(amplitude);
		setFrequencyLabel(frequency);
		if (modulate == true) {
			setAmplitudeLabelCarrier(amplitudeCarrier);
			setFrequencyLabelCarrier(frequencyCarrier);
		}
		mainCanvas.repaint();
	}

	public void setMenu(Object Menu) {
		if (Menu == menuItemModeMod) {
			menuItemModePlot.setSelected(false);
			modulate = true;
		} else if (Menu == menuItemModePlot) {
			menuItemModeMod.setSelected(false);
			modulate = false;
		}
		redraw();
	}

	public boolean isModulate() {
		return modulate;
	}

	public void setAmplitudeLabel(double amplitude) {
		amplitudeLabel.setText("Amplitude: " + amplitude);
	}

	public void setFrequencyLabel(double frequency) {
		frequencyLabel.setText("Frequency: " + frequency + " Hz");
	}

	public void setFrequencyLabelCarrier(double frequencyCarrier) {
		frequencyLabelCarrier.setText("Carrier frequency: " + frequencyCarrier + " Hz");
		;
	}

	public void setAmplitudeLabelCarrier(double amplitudeCarrier) {
		amplitudeLabelCarrier.setText("Carrier amplitude: " + amplitudeCarrier);
	}

	public boolean getCb_grid() {
		return cb_grid.isSelected();
	}

	public void changeGUI() {
		if (modulate == false) {
			sp_top.remove(topPanelCarrier);
			sp_top.setDividerSize(0);
			sp_main.setDividerLocation(75);
			sp_main.validate();
			leftPanel.remove(signalLabelCarrierLeft);
			leftPanel.remove(buttonSineCarrier);
			leftPanel.remove(buttonCosineCarrier);
			leftPanel.remove(buttonSawtoothCarrier);
			leftPanel.remove(buttonTriangleCarrier);
			leftPanel.repaint();
			textPanel.remove(functionNameCarrier);
			textPanel.remove(frequencyLabelCarrier);
			textPanel.remove(amplitudeLabelCarrier);
			textPanel.add(new JLabel(), 3);
			textPanel.add(new JLabel(), 4);
			textPanel.add(new JLabel(), 5);
			textPanel.repaint();
		} else {
			sp_top.setTopComponent(topPanelCarrier);
			sp_main.setDividerLocation(150);
			sp_main.validate();
			sp_top.setDividerLocation(0.5);
			sp_top.setDividerSize(5);
			leftPanel.add(signalLabelCarrierLeft, 5);
			leftPanel.add(buttonSineCarrier, 6);
			leftPanel.add(buttonCosineCarrier, 7);
			leftPanel.add(buttonSawtoothCarrier, 8);
			leftPanel.add(buttonTriangleCarrier, 9);
			leftPanel.repaint();
			textPanel.add(functionNameCarrier, 3);
			textPanel.add(frequencyLabelCarrier, 4);
			textPanel.add(amplitudeLabelCarrier, 5);
			textPanel.repaint();
		}

	}

	public void saveImage() {
		ImageSaver.Save();
	}
}
