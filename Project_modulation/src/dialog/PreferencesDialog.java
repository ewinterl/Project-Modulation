package dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import grapher.ColorSetter;

public class PreferencesDialog extends JDialog implements ColorSetter {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel(new BorderLayout());
	private JPanel preferencesPane = new JPanel(new GridLayout(6, 1));
	private JLabel color_label_function = new JLabel("Select function color");
	private ColorSpinner sp_functioncolor = new ColorSpinner(new String[] { "red", "green", "blue" });
	private JLabel color_label_grid = new JLabel("Select grid color");
	private ColorSpinner sp_gridcolor = new ColorSpinner(new String[] { "black", "red", "green", "blue" });
	private JLabel color_label_background = new JLabel("Select background color");
	private ColorSpinner sp_backgroundcolor = new ColorSpinner(new String[] { "white", "cyan", "yellow" });
	private Color functioncolor = Color.red;
	private Color gridcolor = Color.black;
	private Color backgroundcolor = Color.white;
	private ColorSetter colorSetter;

	public PreferencesDialog(Frame parent, boolean modal) {
		super(parent, "Preferences", modal);
		
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

		contentPane.add(preferencesPane);
		preferencesPane.add(color_label_function);
		preferencesPane.add(sp_functioncolor);
		preferencesPane.add(color_label_grid);
		preferencesPane.add(sp_gridcolor);
		preferencesPane.add(color_label_background);
		preferencesPane.add(sp_backgroundcolor);
		sp_functioncolor.setValue(functioncolor);
		sp_gridcolor.setValue(gridcolor);
		sp_backgroundcolor.setValue(backgroundcolor);
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

	class ColorSpinner extends JSpinner {
		private static final long serialVersionUID = 1L;

		public ColorSpinner(String[] colorNames) {
			super();
			setModel(new SpinnerListModel(colorNames));
			setEditor(new Editor(this));
		}

		public class Editor extends JPanel implements ChangeListener {
			private static final long serialVersionUID = 1L;
			int preferredWidth = 30;
			int preferredHeight = 16;

			Editor(JSpinner spinner) {
				spinner.addChangeListener(this);
				setPreferredSize(new Dimension(preferredWidth, preferredHeight));
				setColor((String) spinner.getValue(), 0);
			}

			public void stateChanged(ChangeEvent evt) {
				JSpinner spinner = (JSpinner) evt.getSource();

				String value = (String) spinner.getValue();

				if (evt.getSource() == sp_functioncolor) {
					setColor(value, 0);
				} else if (evt.getSource() == sp_gridcolor) {
					setColor(value, 1);
				} else if (evt.getSource() == sp_backgroundcolor) {
					setColor(value, 2);
				}
			}

			public void setColor(String colorName, int selected) {
				try {
					java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(colorName);
					Color color = (Color) field.get(null);
					if (selected == 0) {
						functioncolor = color;
					} else if (selected == 1) {
						gridcolor = color;
					} else if (selected == 2) {
						backgroundcolor = color;
					}
					setBackground(color);
				} catch (Exception e) {
				}
			}
		}
	}

	public Color getFunctionColor() {
		return functioncolor;
	}

	public Color getGridColor() {
		return gridcolor;
	}

	public Color getBackgroundColor() {
		return backgroundcolor;
	}

}
