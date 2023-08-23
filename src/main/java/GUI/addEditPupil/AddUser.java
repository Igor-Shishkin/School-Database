package GUI.addEditPupil;

import GUI.Permissions;
import GUI.User;
import GUI.styleStorage.ConstantsOfStyle;
import database.PupilsDataList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AddUser extends JDialog implements ActionListener {
    JTextField currentStatusField;
    JPanel mainPanel;
    JButton addButton, cancelButton;
    JTextField nameField, passwordField, repeatPasswordField;
    JComboBox<String> permissionComboBox;
    JLabel capitalLabel;
    HashMap<String, User> loginInfo;
    String addedID = null;

    public AddUser (JFrame parentFrame, HashMap<String, User> loginInfo, JTextField currentStatusField){
        super(parentFrame,"Achievement",true);
        this.currentStatusField =currentStatusField;
        this.loginInfo = loginInfo;

        setComponentsToMainPanel();

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1,1,20,20));
        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pupil's achievement");
        this.setVisible(false);
    }

    private void setComponentsToMainPanel() {
        capitalLabel = new JLabel("Enter new user's data:");
        nameField = new JTextField(13);
        passwordField = new JTextField(13);
        repeatPasswordField = new JTextField(13);

        nameField.setText("User name");
        passwordField.setText("Enter password");
        repeatPasswordField.setText("Repeat password");
        repeatPasswordField.setToolTipText("Repeat password");
        nameField.setToolTipText("User name");
        passwordField.setToolTipText("Enter password");

        String[] responses = {"", "Director" , "Teacher", "0 grade's class teacher", "0 grade's class teacher",
                "1 grade's class teacher",  "2 grade's class teacher", "3 grade's class teacher",
                "4 grade's class teacher", "5 grade's class teacher", "6 grade's class teacher",
                "7 grade's class teacher", "8 grade's class teacher"} ;
        permissionComboBox = new JComboBox<>(responses);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2,7,7));
        addButton = new JButton("ADD USER");
        cancelButton = new JButton("CANCEL");
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);
        buttonsPanel.add(addButton);
        buttonsPanel.add(cancelButton);

        mainPanel = new JPanel(new GridLayout(6,1,10,10));
        mainPanel.add(capitalLabel);
        mainPanel.add(nameField);
        mainPanel.add(passwordField);
        mainPanel.add(repeatPasswordField);
        mainPanel.add(permissionComboBox);
        mainPanel.add(buttonsPanel);

        setFontForComponents(mainPanel);
    }

    public void setFontForComponents(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton || component instanceof JLabel || component instanceof JTextField
                    || component instanceof JRadioButton) {
                component.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN,30));
            }
            if (component instanceof Container) {
                setFontForComponents((Container) component);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            if (!passwordField.getText().trim().equals("") &&
                    !nameField.getText().trim().equals("") &&
                    permissionComboBox.getSelectedIndex()>0)
            {
                if (passwordField.getText().trim().equals(repeatPasswordField.getText().trim())) {
                    Permissions permissions = (permissionComboBox.getSelectedIndex()==2) ? Permissions.DIRECTOR
                            : (permissionComboBox.getSelectedIndex()==3) ? Permissions.TEACHER
                            : (permissionComboBox.getSelectedIndex()==4) ? Permissions.CLASS_TEACHER_0
                            : (permissionComboBox.getSelectedIndex()==5) ? Permissions.CLASS_TEACHER_1
                            : (permissionComboBox.getSelectedIndex()==6) ? Permissions.CLASS_TEACHER_2
                            : (permissionComboBox.getSelectedIndex()==7) ? Permissions.CLASS_TEACHER_3
                            : (permissionComboBox.getSelectedIndex()==8) ? Permissions.CLASS_TEACHER_4
                            : (permissionComboBox.getSelectedIndex()==9) ? Permissions.CLASS_TEACHER_5
                            : (permissionComboBox.getSelectedIndex()==10) ? Permissions.CLASS_TEACHER_6
                            : (permissionComboBox.getSelectedIndex()==2) ? Permissions.CLASS_TEACHER_7
                            : Permissions.CLASS_TEACHER_8;
                    loginInfo.put(nameField.getText().trim(), new User(passwordField.getText().trim(), permissions));
                    addedID  = nameField.getText().trim();
                    dispose();
                } else {
                    repeatPasswordField.setText("WRONG PASSWORD");
                }
            }  else {
                capitalLabel.setText("Please fill in all fields");
            }
        }
        if (e.getSource()==cancelButton) {
            dispose();
        }
    }
    public String showDialogAndGetResult() {
        this.setVisible(true);
        return addedID;
    }
}
