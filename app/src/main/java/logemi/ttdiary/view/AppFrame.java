package logemi.ttdiary.view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AppFrame extends JFrame {

  public AppFrame() {
    super("Time Travel Diary");
    JTabbedPane jTabbedPane = new JTabbedPane();

    jTabbedPane.addTab("Write entry", new WriteEntryPanel());

    this.add(jTabbedPane);
    this.setSize(700,500);
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  
}
