package dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class PreferencesDialog extends JDialog {
	private JPanel contentPane = new JPanel(new BorderLayout());

	
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

	private void PreferenceDialogInit() throws Exception{
		contentPane = (JPanel) getContentPane(); 
		this.setContentPane(contentPane);
		setSize(new Dimension(300, 150));

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
		setLocation( (screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}



}
