/**
 * 
 */
package dialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import pictures.PicturePanel;

/**
 * @author ewinterl
 *
 */
public class AboutDialog extends JDialog {

	private JPanel contentPane;
	private JPanel centerPanel = new JPanel();
	private JPanel headerPanel = new JPanel();

	private String version;
	private String title;

	private JLabel versionLabel = new JLabel();
	private JLabel titleLabel = new JLabel();
	private JLabel javaVersionLabel = new JLabel();
	private JLabel copyrightLabel = new JLabel();
	

	private PicturePanel appIcon;

	public AboutDialog(Frame parent, String title, boolean modal, String versionID) {
		super(parent, "About " + title + " ...", modal); // Modal dialog

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
		
		setSize(new Dimension(600, 300));

		centerPanel.setLayout(new BorderLayout());
		headerPanel.setLayout(new GridLayout(2,1));

		appIcon = new PicturePanel("../icon_v3.png", 200, 200);

		javaVersionLabel.setText("System JRE version: " + System.getProperty("java.version"));
		titleLabel.setText("<html>   <h1>" + title + "<h1></html>");
		versionLabel.setText("   Version: " + version);
		copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		copyrightLabel.setFont(new Font("light", 100, 10));
		copyrightLabel.setText("Copyright © 2017 Weber and Winterleitner. All rights reserved");
		
		headerPanel.add(titleLabel);
		headerPanel.add(versionLabel);
		centerPanel.add(headerPanel, BorderLayout.NORTH);
		centerPanel.add(javaVersionLabel);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(appIcon, BorderLayout.WEST);
		contentPane.add(copyrightLabel, BorderLayout.SOUTH);
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
}
