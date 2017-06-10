package Listeners;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JMenuItem;

import modulation.ImageSaver;
import modulation.MainFrame;

public class Button_Listener_MenuManual implements ActionListener{
	Object itemManual;
	ImageSaver imSaver;
	
	public Button_Listener_MenuManual(JMenuItem itemManual, MainFrame mf) {
		this.itemManual = itemManual;
		imSaver = mf;
	}

	public void actionPerformed(ActionEvent ae) {
		menuHandlerHelp(ae); 

	}

	private void menuHandlerHelp (ActionEvent ae) {
		Object source = ae.getSource();
		
		if(source == itemManual) {
			if (Desktop.isDesktopSupported()) {
				InputStream inputPdf = getClass().getClassLoader().getResourceAsStream("GraphApp.pdf");

	            try {
	                File pdfTemp = new File("GraphApp.pdf");
	               
	                FileOutputStream fileos = new FileOutputStream(pdfTemp);
	                while (inputPdf.available() > 0) {
	                      fileos.write(inputPdf.read());
	                }  
	                fileos.close();
	                Desktop.getDesktop().open(pdfTemp);
	            } catch (IOException e) {
	                System.out.println("Error opening pdf");
	            }  
		    }
		}
	}
}
