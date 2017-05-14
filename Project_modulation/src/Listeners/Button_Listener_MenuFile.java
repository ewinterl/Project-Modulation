package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modulation.ImageSaver;
import modulation.MainFrame;

public class Button_Listener_MenuFile implements ActionListener {
	Object itemSave;
	ImageSaver imSaver;
	
	public Button_Listener_MenuFile(Object itemSave, MainFrame mf) {
		this.itemSave = itemSave;
		imSaver = mf;
	}
	
	public void actionPerformed(ActionEvent ae) {
		menuHandlerFile(ae);
	}
	
	/*
	 * Handler for items in menu section level
	 * disables and checks selected menu items
	 */
	private void menuHandlerFile (ActionEvent ae) {
		Object source = ae.getSource();
		
		if(source == itemSave) {
			imSaver.saveImage();
		}
	}
}
