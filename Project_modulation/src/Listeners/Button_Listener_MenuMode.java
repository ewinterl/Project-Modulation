package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modulation.MainFrame;
import modulation.ModeChanger;

public class Button_Listener_MenuMode implements ActionListener {
	Object ItemMod;
	Object ItemPlot;
	ModeChanger mc;

	public Button_Listener_MenuMode (Object ItemModulation, Object ItemPlotter, MainFrame mf) {
		ItemMod = ItemModulation;
		ItemPlot = ItemPlotter;
		mc = mf;
	}

	public void actionPerformed(ActionEvent ae) {
		menuHandlerMode(ae); 

	}

	/*
	 * Handler for items in menu section control
	 */
	private void menuHandlerMode (ActionEvent ae) {
		if (ae.getSource() == ItemMod) {
			mc.setMenu(ItemMod);
		} else if (ae.getSource() == ItemPlot) {
			mc.setMenu(ItemPlot);
		}
		mc.changeGUI();
	}

}
