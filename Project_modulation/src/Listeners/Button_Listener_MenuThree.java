package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dialog.AboutDialog;

public class Button_Listener_MenuThree implements ActionListener {
	
	// About dialog
		AboutDialog aboutDialog;
		
	public void actionPerformed(ActionEvent ae) {
		menuHandlerThree(ae);
	}
	
	/*
	 * Handler for items in menu section RT
	 */
	private void menuHandlerThree (ActionEvent ae) {
		aboutDialog.setVisible(true);
	}
}
