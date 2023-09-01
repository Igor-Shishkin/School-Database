package school.database.gui.add_edit_windows;

import school.database.gui.styleStorage.ConstantsOfStyle;
import school.database.data.objects.Permissions;
import school.database.data.objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AddUser extends JDialog implements ActionListener {
    private final JTextField currentStatusField;
    private JPanel mainPanel;
    private JButton addButton, cancelButton;
    private JTextField nameField;
    private JPasswordField passwordField, repeatPasswordField;
    private JComboBox<String> permissionComboBox;
    private JLabel capitalLabel;
    private final Map<String, User> loginInfo;
    private String addedID = null;
    private final transient ConstantsOfStyle styleConstants;

    public AddUser (JFrame parentFrame, Map<String, User> loginInfo, JTextField currentStatusField,
                    ConstantsOfStyle styleConstants){
        super(parentFrame,"Achievement",true);
        this.currentStatusField =currentStatusField;
        this.loginInfo = loginInfo;
        this.styleConstants = styleConstants;

        setComponentsToMainPanel();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1,1,20,20));
        this.add(mainPanel);
        setStyleForWindow(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pupil's achievement");
        this.setVisible(false);
    }

    private void setComponentsToMainPanel() {
        capitalLabel = new JLabel("Enter new user's data:");
        nameField = new JTextField(13);
        passwordField = new JPasswordField(13);
        repeatPasswordField = new JPasswordField(13);

        JLabel nameLabel = new JLabel("Name:");
        JLabel passwordLabel = new JLabel("Enter password:");
        JLabel repeatPasswordLabel = new JLabel("Repeat password:");
        JLabel choosePermissionLabel = new JLabel("Choose permission:");


        repeatPasswordField.setToolTipText("Repeat password");
        nameField.setToolTipText("User name");
        passwordField.setToolTipText("Enter password");

        String[] responses = {"", "Director" , "Teacher", "0 grade's class teacher",
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

        mainPanel = new JPanel(new GridLayout(10,1,5,10));
        mainPanel.add(capitalLabel);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(repeatPasswordLabel);
        mainPanel.add(repeatPasswordField);
        mainPanel.add(choosePermissionLabel);
        mainPanel.add(permissionComboBox);
        mainPanel.add(buttonsPanel);

        setFontForComponents(mainPanel);
    }

    public void setFontForComponents(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton || component instanceof JLabel || component instanceof JTextField
                    || component instanceof JRadioButton) {
                component.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN,30));
            }
            if (component instanceof Container) {
                setFontForComponents((Container) component);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton) {
            if (!String.valueOf(passwordField.getPassword()).equals("") &&
                    !nameField.getText().trim().equals("") &&
                    permissionComboBox.getSelectedIndex()>0)
            {
                if (String.valueOf(passwordField.getPassword())
                        .equals(String.valueOf(repeatPasswordField.getPassword()))) {
                    Permissions permissions = (permissionComboBox.getSelectedIndex()==1) ? Permissions.DIRECTOR
                            : (permissionComboBox.getSelectedIndex()==2) ? Permissions.TEACHER
                            : (permissionComboBox.getSelectedIndex()==3) ? Permissions.CLASS_TEACHER_0
                            : (permissionComboBox.getSelectedIndex()==4) ? Permissions.CLASS_TEACHER_1
                            : (permissionComboBox.getSelectedIndex()==5) ? Permissions.CLASS_TEACHER_2
                            : (permissionComboBox.getSelectedIndex()==6) ? Permissions.CLASS_TEACHER_3
                            : (permissionComboBox.getSelectedIndex()==7) ? Permissions.CLASS_TEACHER_4
                            : (permissionComboBox.getSelectedIndex()==8) ? Permissions.CLASS_TEACHER_5
                            : (permissionComboBox.getSelectedIndex()==9) ? Permissions.CLASS_TEACHER_6
                            : (permissionComboBox.getSelectedIndex()==10) ? Permissions.CLASS_TEACHER_7
                            : Permissions.CLASS_TEACHER_8;
                    loginInfo.put(nameField.getText().trim(),
                            new User(String.valueOf(passwordField.getPassword()), permissions));
                    addedID  = nameField.getText().trim();
                    dispose();
                } else {
                    capitalLabel.setText("WRONG PASSWORD");
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
    private void setStyleForWindow(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof JLabel || component instanceof JPanel) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
            }
            if (component instanceof JTextField) {
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof Container) {
                setStyleForWindow((Container) component);
            }
        }
    }
}
