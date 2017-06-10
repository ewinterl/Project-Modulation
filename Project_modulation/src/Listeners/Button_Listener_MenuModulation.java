package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import dialog.AboutDialog;
import modulation.MainFrame;
import modulation.ModeChanger;

public class Button_Listener_MenuModulation implements ActionListener {
	Object ItemAM;
	Object ItemFM;
	ModeChanger mc;

	public Button_Listener_MenuModulation(Object menuItemModAM, Object menuItemModFM, MainFrame mainFrame) {
		ItemAM = menuItemModAM;
		ItemFM = menuItemModFM;
		mc = mainFrame;
	}
	public void actionPerformed(ActionEvent e) {
		menuHandlerMode(e);
	}
	/*
	 * Handler for items in menu section modulation
	 */
	private void menuHandlerMode (ActionEvent ae) {
		if (ae.getSource() == ItemAM) {
			mc.setMenu(ItemAM);
		} else if (ae.getSource() == ItemFM) {
			mc.setMenu(ItemFM);
		}
		mc.changeGUI();
	}
}
