package GUI.addEditPupil;

import GUI.Main;
import GUI.User;
import GUI.styleStorage.ConstantsOfStyle;
import database.PupilsDataList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditUsers extends JDialog implements ActionListener {
    private final PupilsDataList dataList;
    JTextField currentStatusField;
    JPanel mainPanel, usersPanel;
    ArrayList<JRadioButton> listOfUsers;
    JButton addButton, removeButton, cancelButton;
    JLabel capitalLabel;
    HashMap<String, User> loginInfo;
    JFrame parentFrame;
    GridBagConstraints c;
    ButtonGroup buttonGroup;

    public EditUsers(JFrame parentFrame, PupilsDataList dataList, JTextField currentStatusField) {
        super(parentFrame, "Achievement", true);
        this.currentStatusField = currentStatusField;
        this.dataList = dataList;
        this.parentFrame = parentFrame;

        setComponentsToGeneralPanel();

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1, 1, 0, 10));
        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pupil's achievement");
        this.setVisible(true);
    }

    private void setComponentsToGeneralPanel() {
        capitalLabel = new JLabel("User : PERMISSIONS");
        capitalLabel.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.BOLD, 30));

        loginInfo = dataList.getLoginInfo();
        listOfUsers = new ArrayList<>();
        for (Map.Entry<String, User> entry : loginInfo.entrySet()) {
            listOfUsers.add(new JRadioButton
                    (entry.getKey()
                            .concat(" : ")
                            .concat(entry.getValue().getPermissions().toString())));
        }

        addButton = new JButton("Add users");
        addButton.addActionListener(this);
        removeButton = new JButton("Remove user");
        removeButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        buttonGroup = new ButtonGroup();
        usersPanel = new JPanel(new GridLayout(loginInfo.size(), 1, 5, 5));
        usersPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        for (JRadioButton rb : listOfUsers) {
            if (rb.getText().substring(0, rb.getText().indexOf(" : ")).equals(Main.CURRENT_USER)) {
                rb.setEnabled(false);
            }
            rb.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 23));
            usersPanel.add(rb);
            buttonGroup.add(rb);
        }

        mainPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        mainPanel.add(capitalLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = loginInfo.size();
        mainPanel.add(usersPanel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        mainPanel.add(addButton, c);

        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(removeButton, c);

        c.gridx = 1;
        c.gridy = 3;
        mainPanel.add(cancelButton, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
        }
        if (e.getSource() == addButton) {
            AddUser addUser = new AddUser(parentFrame, loginInfo, currentStatusField);
            String newID = addUser.showDialogAndGetResult();
            if (newID != null) {
                mainPanel.remove(usersPanel);
                listOfUsers.add(new JRadioButton(newID
                        .concat(" : ")
                        .concat(loginInfo.get(newID).getPermissions().toString())));
                usersPanel = new JPanel(new GridLayout(listOfUsers.size(), 1, 7, 7));
                for (JRadioButton rb : listOfUsers) {
                    rb.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 23));
                    usersPanel.add(rb);
                    buttonGroup.add(rb);
                    if (rb.getText().substring(0, rb.getText().indexOf(" : ")).equals(Main.CURRENT_USER)) {
                        rb.setEnabled(false);
                    }

                }
                c.gridx = 0;
                c.gridy = 1;
                c.gridheight = loginInfo.size();
                mainPanel.add(usersPanel, c);

                mainPanel.repaint();
                this.pack();
            }
        }
        if (e.getSource() == removeButton) {
            for (JRadioButton radioButton : listOfUsers) {
                if (radioButton.isSelected()) {
                    String id = radioButton.getText().substring(0, radioButton.getText().indexOf(" : "));
                    loginInfo.remove(id);
                    listOfUsers.remove(radioButton);
                }
                mainPanel.remove(usersPanel);

                usersPanel = new JPanel(new GridLayout(listOfUsers.size(), 1, 7, 7));
                for (JRadioButton rb : listOfUsers) {
                    rb.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 23));
                    usersPanel.add(rb);
                    buttonGroup.add(rb);
                    if (rb.getText().substring(0, rb.getText().indexOf(" : ")).equals(Main.CURRENT_USER)) {
                        rb.setEnabled(false);
                    }
                }
                c.gridx = 0;
                c.gridy = 1;
                c.gridheight = loginInfo.size();
                mainPanel.add(usersPanel, c);

                mainPanel.repaint();
                this.pack();
            }
        }
    }
}


