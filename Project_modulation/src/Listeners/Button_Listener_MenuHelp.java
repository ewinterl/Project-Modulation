package Listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modulation.AboutDialogMaker;
import modulation.MainFrame;

public class Button_Listener_MenuHelp implements ActionListener {
	AboutDialogMaker about;
	
	public Button_Listener_MenuHelp (MainFrame mf) {
		about = mf;
		mf.setSize(new Dimension(100, 100));
	}
	
	public void actionPerformed(ActionEvent ae) {
		menuHandlerHelp(ae); 

	}

	/*
	 * Handler for items in menu section help
	 */
	private void menuHandlerHelp (ActionEvent ae) {
		about.showAbout();
	}
}
