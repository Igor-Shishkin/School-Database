package GUI.addPupil;

import GUI.styleStorage.ConstantsOfColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAchievement extends JDialog implements ActionListener {
    String achievements;
    JButton addButton, cancelButton;
    JTextArea textArea;

    AddAchievement(JFrame parentFrame, String achievements) {
        super(parentFrame, "Achievement", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        textArea = new JTextArea(achievements);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(ConstantsOfColors.THE_MAIN_FONT.deriveFont(Font.PLAIN,16));

        addButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400,120));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7,20,4,20);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx=0;
        c.gridy=0;
        c.gridwidth = 4;
        c.gridheight = 2;
        this.add(scrollPane, c);

        c.insets = new Insets(7,5,7,5);
        c.gridx=0;
        c.gridy=2;
        c.gridwidth = 4;
        c.gridheight = 1;
        this.add(new JSeparator(), c);

        c.insets = new Insets(6,10,6,10);
        c.gridx=0;
        c.gridy=3;
        c.gridwidth = 2;
        this.add(addButton, c);

        c.insets = new Insets(6,10,6,10);
        c.gridx=2;
        c.gridy=3;
        c.gridwidth = 2;
        this.add(cancelButton, c);

        addButton.addActionListener(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pupil's achievement");
//        this.setVisible(true);

    }
    public String showDialogAndGetInput(){
        this.setVisible(true);
        return achievements;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            achievements = textArea.getText();
            dispose();

        }
        if (e.getSource()==addButton){
            dispose();

        }
    }
}
