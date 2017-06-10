package pictures;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.net.*;
import java.io.*;
 
public class PicturePanel extends JPanel { 
   private Image image;
   private ImageIcon icon;
 
   public PicturePanel(String imgPath, int width, int heigth) {
      super(new GridBagLayout());
 
      try {
         image = ImageIO.read(getClass().getResource(imgPath));
         image = image.getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
         icon = new ImageIcon(image);
         System.out.println(image);
         JLabel pictureLabel = new JLabel(icon);
         this.add(pictureLabel);
      }
      catch(IllegalArgumentException iae) {
         JOptionPane.showMessageDialog(this, "Grafikdatei nicht gefunden!\n"+iae.getMessage());
      }
      catch(IOException ioe) {
         JOptionPane.showMessageDialog(this, "Fehler beim Einlesen einer Grafikdatei!\n"+ioe.getMessage());
      }
 
   }
}
