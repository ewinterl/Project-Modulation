package dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import grapher.ColorSetter;
import grapher.Screen;

public class PreferencesDialog extends JDialog {

	private JPanel contentPane = new JPanel(new BorderLayout());
	private JPanel preferencesPane = new JPanel(new GridLayout(6, 1));

	private JLabel color_label_function = new JLabel("Select function color");
	private JLabel color_label_grid = new JLabel("Select grid color");
	private JLabel color_label_background = new JLabel("Select background color");

	private ColorSetter cs;

	private ColorSpinner csp_functioncolor = new ColorSpinner(new String[] { "red", "green", "blue" });
	private ColorSpinner csp_gridcolor = new ColorSpinner(new String[] { "black", "red", "green", "blue" });
	private ColorSpinner csp_backgroundcolor = new ColorSpinner(new String[] { "white", "cyan", "yellow" });
	
	java.lang.reflect.Field field;

	public PreferencesDialog(Frame parent, boolean modal, Screen screen) {
		super(parent, "Preferences", modal);

		cs = screen;

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



		preferencesPane.add(color_label_function);
		preferencesPane.add(csp_functioncolor);
		preferencesPane.add(color_label_grid);
		preferencesPane.add(csp_gridcolor);
		preferencesPane.add(color_label_background);
		preferencesPane.add(csp_backgroundcolor);
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
			private int preferredWidth = 30;
			private int preferredHeight = 16;
			private Color color;

			public Editor(JSpinner spinner) {
				try {
					color = (Color) Class.forName("java.awt.Color").getField((String) spinner.getValue()).get(null);
				} catch (Exception e) {
					
				}
				
				spinner.addChangeListener(this);
				setPreferredSize(new Dimension(preferredWidth, preferredHeight));
				setBackground(color);
			}

			public void stateChanged(ChangeEvent evt) {
				JSpinner spinner = (JSpinner) evt.getSource();

				String value = (String) spinner.getValue();

				if (evt.getSource() == csp_functioncolor) {
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
	}
}
