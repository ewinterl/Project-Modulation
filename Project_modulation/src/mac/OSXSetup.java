package mac;

import javax.swing.ImageIcon;

import com.apple.eawt.AboutHandler; 
import com.apple.eawt.AppEvent.AboutEvent; 
import com.apple.eawt.AppEvent.PreferencesEvent;
import com.apple.eawt.Application;
import com.apple.eawt.PreferencesHandler;
import dialog.AboutDialog;
import dialog.PreferencesDialog;


/*
 * 
 * Look for new handlers 
 * some may be deprecated 
 * 
 */

public class OSXSetup { 

	private boolean modal = true;									// set dialogs modal
	private java.awt.Frame parent;
	private AboutDialog aboutDialog;
	private PreferencesDialog preferencesDialog;

	public OSXSetup(String title, String versionID) { 
		Application app = Application.getApplication();
	
		aboutDialog = new AboutDialog(parent, title, modal, versionID);
		preferencesDialog = new PreferencesDialog(parent, modal);
		
		app.setPreferencesHandler(new PreferencesHandler() {
			public void handlePreferences(PreferencesEvent pf) {
				preferencesDialog.setVisible(true);
			}
		});

		app.setAboutHandler(new AboutHandler() { 
			public void handleAbout(AboutEvent ae) { 
				aboutDialog.setVisible(true); 
			} 
		});
		
		app.setDockIconImage(new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage());
		app.setDockIconBadge("Mod");	
	}


}