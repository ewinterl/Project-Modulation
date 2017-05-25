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
	private JPanel topPanel = new JPanel();
	
	private String version;
	private String title;
	
	private JLabel versionLabel = new JLabel();
	private JLabel titleLabel = new JLabel();

	public AboutDialog (Frame parent, String title, boolean modal, String versionID) {
		super (parent, "About " + title + " ...", modal);		// Modal dialog

		this.title = title;
		version = versionID;
		
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			AboutDialogInit();
			CenterDialog();
		} catch (Exception exception) {
			exception.printStackTrace();
		}


	}

	private void AboutDialogInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
		
		topPanel.setLayout(new BorderLayout());
		
		titleLabel.setText("<html><b>" + title + "</b> </html>");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		versionLabel.setText(version);
		
		topPanel.add(titleLabel, BorderLayout.NORTH);
		topPanel.add(new JLabel ("Version:"));
		topPanel.add(versionLabel, BorderLayout.SOUTH);
		contentPane.add(topPanel, BorderLayout.NORTH);

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
