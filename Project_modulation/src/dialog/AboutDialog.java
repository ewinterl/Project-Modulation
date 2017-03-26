/**
 * 
 */
package dialog;

import java.awt.*;
import javax.swing.*;


/**
 * @author ewinterl
 *
 */
public class AboutDialog extends JDialog {

	private JPanel contentPane;

	public AboutDialog (Frame parent, String title, boolean modal, String versionID) {
		super (parent, "About " + title + " ...", modal);		// Modal dialog

		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			AboutDialogInit(versionID);
			CenterDialog();
		} catch (Exception exception) {
			exception.printStackTrace();
		}


	}

	private void AboutDialogInit(String versionID) throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);	

		setSize(new Dimension(300, 250));
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
