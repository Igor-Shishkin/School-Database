package GUI.addEditPupil;

import GUI.CentralPanel;
import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditAchievement extends JDialog implements ActionListener {
    String achievements, returnedAchievements;
    JButton addButton, cancelButton;
    JTextArea textArea;
    JTextField currentStatusField;
    ConstantsOfStyle styleConstants;


    public AddEditAchievement(JFrame parentFrame, String achievements, JTextField currentStatusField,
                              ConstantsOfStyle styleConstants) {
        super(parentFrame, "Achievement", true);
        this.currentStatusField = currentStatusField;
        this.styleConstants = styleConstants;

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());


        returnedAchievements = achievements;

        textArea = new JTextArea(achievements);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN,18));

        addButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400,120));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7,20,4,20);
        c.fill = GridBagConstraints.HORIZONTAL;

        JPanel panelForButtons = new JPanel(new GridLayout(1,2,5,10));
        panelForButtons.add(addButton);
        panelForButtons.add(cancelButton);

        c.gridx=0;
        c.gridy=0;
        c.gridwidth = 2;
        c.gridheight = 2;
        this.add(scrollPane, c);

        c.insets = new Insets(7,5,7,5);
        c.gridx=0;
        c.gridy=2;
        c.gridheight = 1;
        this.add(new JSeparator(), c);

        c.insets = new Insets(6,10,6,10);
        c.gridx=0;
        c.gridy=3;
        this.add(panelForButtons, c);



        addButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setStyleForWindow(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pupil's achievement");
//        this.setVisible(true);

    }
    public String showDialogAndGetInput(){
        this.setVisible(true);
        return returnedAchievements;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            returnedAchievements = textArea.getText();
            currentStatusField.setText(String.format("Changes are saved (%s: achievement)",
                    CentralPanel.CURRENT_PUPIL.getNamesAndSurname()));
            dispose();
        }
        if (e.getSource()==cancelButton){
            dispose();

        }
    }
    private void setStyleForWindow(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof JLabel || component instanceof JPanel) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
            }
            if (component instanceof Container) {
                setStyleForWindow((Container) component);
            }
        }
    }
}
