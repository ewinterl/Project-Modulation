package dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;

import grapher.ColorSetter;
import grapher.Screen;
import modulation.AlphaSetter;
import modulation.MainFrame;

public class PreferencesDialog extends JDialog {

	private JPanel contentPane = new JPanel(new BorderLayout());
	private JPanel preferencesPane = new JPanel(new GridLayout(4, 2));

	private JLabel color_label_function = new JLabel("Select function color");
	private JLabel color_label_grid = new JLabel("Select grid color");
	private JLabel color_label_background = new JLabel("Select background color");
	private JLabel colorText = new JLabel("Colors: black, red, yellow, green, cyan, blue, white");

	private ColorSetter cs;
	private AlphaSetter as;

	private String Colors[] = new String[]{ "black", "red", "yellow", "green", "cyan", "blue", "white" };

	private ColorSpinner csp_functioncolor = new ColorSpinner(Colors);
	private ColorSpinner csp_gridcolor = new ColorSpinner(Colors);
	private ColorSpinner csp_backgroundcolor = new ColorSpinner(Colors);

	private java.lang.reflect.Field field;

	private JRadioButton saveAlpha = new JRadioButton("Save with alpha");

	public PreferencesDialog(Frame parent, boolean modal, Screen screen, MainFrame mf) {
		super(parent, "Preferences", modal);

		cs = screen;
		as = mf;
		csp_functioncolor.setValue(Colors[1]);
		csp_gridcolor.setValue(Colors[0]);
		csp_backgroundcolor.setValue(Colors[6]);

		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			PreferenceDialogInit();
			CenterDialog();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void PreferenceDialogInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		this.setContentPane(contentPane);
		setSize(new Dimension(600, 300));
		
		colorText.setFont(new Font("light", 100, 10));

		saveAlpha.addActionListener(new ButtonListener());

		preferencesPane.add(color_label_function);
		preferencesPane.add(csp_functioncolor);
		preferencesPane.add(color_label_grid);
		preferencesPane.add(csp_gridcolor);
		preferencesPane.add(color_label_background);
		preferencesPane.add(csp_backgroundcolor);
		preferencesPane.add(saveAlpha);
		preferencesPane.add(colorText);
		contentPane.add(preferencesPane);
	}

	private void CenterDialog() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}

	public class ColorSpinner extends JSpinner {

		public ColorSpinner(String[] colorNames) {
			super();
			setModel(new SpinnerListModel(colorNames));
			setEditor(new Editor(this));
		}

		public class Editor extends JPanel implements ChangeListener {
			private Color color;

			public Editor(JSpinner spinner) {
				try {
					color = (Color) Class.forName("java.awt.Color").getField((String) spinner.getValue()).get(null);
				} catch (Exception e) {

				}

				spinner.addChangeListener(this);
				setBackground(color);
			}

			public void stateChanged(ChangeEvent evt) {
				JSpinner spinner = (JSpinner) evt.getSource();

				String value = (String) spinner.getValue();

				if (evt.getSource() == csp_functioncolor) {
					if(value == Colors[Colors.length-1]) {
					}
					setColor(value, 0);
				} else if (evt.getSource() == csp_gridcolor) {
					setColor(value, 1);
				} else if (evt.getSource() == csp_backgroundcolor) {
					setColor(value, 2);
				}
			}

			private void setColor(String colorName, int selected) {
				try {
					field = Class.forName("java.awt.Color").getField(colorName);
					Color color = (Color) field.get(null);
					if (selected == 0) {
						cs.setFunctionColor(color);
					} else if (selected == 1) {
						cs.setGridColor(color);
					} else if (selected == 2) {
						cs.setBackgroundColor(color);
					}
					this.setBackground(color);
				} catch (Exception e) {
				}
			}
		}

		public Object getNextValue() {
		    int index = Arrays.asList(Colors).indexOf(this.getValue());
		    	System.out.println(index);
		    index = (index >= Colors.length - 1) ? 0 : index + 1;
		    return Colors[index];
		}

		public Object getPreviousValue() {
			int index = Arrays.asList(Colors).indexOf(this.getValue());
	    	System.out.println(index);
	    index = (index <= 0) ? Colors.length - 1 : index - 1;
	    return Colors[index];
		}
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonHandler(e);

		}

		private void buttonHandler(ActionEvent e) {
			as.setAlpha(saveAlpha.isSelected());
		}

	}

}
