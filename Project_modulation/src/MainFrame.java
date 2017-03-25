
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
  private JPanel contentPane;
  public MainFrame() {
    try {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      FrameInit();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Component initialization.
   *
   * @throws java.lang.Exception
   */
  private void FrameInit() throws Exception {
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(new BorderLayout());
    setSize(new Dimension(400, 300));
    setTitle("Modulation Demo");
  }
}
