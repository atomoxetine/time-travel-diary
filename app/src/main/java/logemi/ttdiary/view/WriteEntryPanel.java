package logemi.ttdiary.view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import logemi.ttdiary.controller.Entries;
import java.awt.Color;

//design is definetely not my passion
public class WriteEntryPanel extends JPanel {
  private JLabel label;
  private JPanel titlePanel;
  private JLabel titleLabel;
  private JTextField titleText;
  private JTextArea entryText;
  private JScrollPane entryScroll;
  private JButton submitButton;
  private JPanel bottomPanel;
  private JPanel datePanel;
  private JLabel yearLabel;
  private JTextField yearText;
  private JLabel monthLabel;
  private JTextField monthText;
  private JLabel dayLabel;
  private JTextField dayText;
  private static Color errorBackgroundColor = new Color(255, 124, 114);
  private static Color normalBackgroundColor = new Color(240, 240, 240);

  public WriteEntryPanel() {
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));


    label = new JLabel("Write Entry");
    label.setFont(label.getFont().deriveFont(30f));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(label);

    this.add(Box.createRigidArea(new Dimension(0, 20)));

    titlePanel = new JPanel();
    titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleLabel = new JLabel("Title: ");
    titleLabel.setFont(titleLabel.getFont().deriveFont(25f));
    titleText = new JTextField();
    titleText.setFont(titleText.getFont().deriveFont(25f));
    titleText.setMaximumSize(new Dimension(400, 50));

    titlePanel.add(titleLabel);
    titlePanel.add(titleText);
    this.add(titlePanel);

    this.add(Box.createRigidArea(new Dimension(0, 20)));

    entryText = new JTextArea();
    entryText.setFont(entryText.getFont().deriveFont(15f));
    entryText.setLineWrap(true);
    entryScroll = new JScrollPane(entryText,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    entryText.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(entryScroll);

    this.add(Box.createRigidArea(new Dimension(0,10)));

    bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.setMaximumSize(new Dimension(5000, 30));
    bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    submitButton = new JButton("Submit");
    submitButton.setFont(submitButton.getFont().deriveFont(18f));
    submitButton.setPreferredSize(new Dimension(107, 30));
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       submitEntry(); 
      }
    });

    datePanel = new JPanel();
    datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
    yearLabel = new JLabel("Date: ");
    yearLabel.setFont(yearLabel.getFont().deriveFont(22f));
    yearText = new JTextField();
    yearText.setFont(yearLabel.getFont());
    yearText.setPreferredSize(new Dimension(68, 15));
    monthLabel = new JLabel("-");
    monthLabel.setFont(yearLabel.getFont());
    monthText = new JTextField();
    monthText.setFont(yearLabel.getFont());
    monthText.setPreferredSize(new Dimension(37, 15));
    dayLabel = new JLabel("-");
    dayLabel.setFont(yearLabel.getFont());
    dayText = new JTextField();
    dayText.setFont(yearLabel.getFont());
    dayText.setPreferredSize(new Dimension(37, 15));

    datePanel.add(yearLabel);
    datePanel.add(yearText);
    datePanel.add(monthLabel);
    datePanel.add(monthText);
    datePanel.add(dayLabel);
    datePanel.add(dayText);

    bottomPanel.add(submitButton, BorderLayout.LINE_START);
    bottomPanel.add(datePanel, BorderLayout.LINE_END);

    this.add(bottomPanel);
  }
  
  private void submitEntry() {
    String title = titleText.getText();
    String content = entryText.getText();
    String yearString = yearText.getText();
    String monthString = monthText.getText();
    String dayString = dayText.getText();

    int year=0, month=0, day=0;
    boolean hasErrors = false;

    yearText.setBackground(normalBackgroundColor);
    monthText.setBackground(normalBackgroundColor);
    dayText.setBackground(normalBackgroundColor);

    try {
      year = Integer.parseInt(yearString);
    } catch (Exception e) {
      yearText.setBackground(errorBackgroundColor);
      hasErrors = true;
    }

    try {
      month = Integer.parseInt(monthString);
      if (month < 1 || month > 12) throw new Exception();
    } catch (Exception e) {
      monthText.setBackground(errorBackgroundColor);
      hasErrors = true;
    }

    try {
      day = Integer.parseInt(dayString);
      if (day < 1) throw new Exception();
      switch (month) {
        case 2:
          if (year % 4 == 0) {
            if (day > 29) throw new Exception();
          } else {
            if (day > 28) throw new Exception();
          }
          break;
        case 4:
        case 6:
        case 9:
        case 11:
          if (day > 30) throw new Exception();
        default:
          if (day > 31) throw new Exception();
      }
    } catch (Exception e) {
      dayText.setBackground(errorBackgroundColor);
      hasErrors = true;
    }

    if (hasErrors) return;

    Entries.INSTANCE.createEntry(content, title, year, (byte)month, (byte)day, null); 
  
    titleText.setText("");
    entryText.setText("");
    yearText.setText("");
    monthText.setText("");
    dayText.setText("");
  }
}
