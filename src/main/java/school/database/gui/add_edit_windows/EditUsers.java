package school.database.gui.add_edit_windows;

import school.database.gui.ActualElements;
import school.database.gui.styleStorage.ConstantsOfStyle;
import school.database.data.objects.User;
import school.database.data.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditUsers extends JDialog implements ActionListener {
    private final Data dataList;
    private final JTextField currentStatusField;
    private JPanel mainPanel, usersPanel;
    private ArrayList<JRadioButton> listOfUsers;
    private JButton addButton, removeButton, cancelButton;

    private Map<String, User> loginInfo;
    private final JFrame parentFrame;
    private GridBagConstraints c;
    private ButtonGroup buttonGroup;
    private final transient ConstantsOfStyle styleConstants;
    private final transient ActualElements actualElements;

    public EditUsers(JFrame parentFrame, Data dataList, JTextField currentStatusField,
                     ConstantsOfStyle styleConstants, ActualElements actualElements) {
        super(parentFrame, "Achievement", true);
        this.currentStatusField = currentStatusField;
        this.dataList = dataList;
        this.parentFrame = parentFrame;
        this.styleConstants = styleConstants;
        this.actualElements = actualElements;

        setComponentsToGeneralPanel();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1, 1, 0, 10));
        this.add(mainPanel);
        setStyleForWindow(this);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pupil's achievement");
        this.setVisible(true);
    }

    private void setComponentsToGeneralPanel() {
        JLabel capitalLabel;
        capitalLabel = new JLabel("User : PERMISSIONS");
        capitalLabel.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.BOLD, 30));

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
            if (rb.getText().substring(0, rb.getText().indexOf(" : ")).equals(actualElements.getUserName())) {
                rb.setEnabled(false);
            }
            rb.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 23));
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
            addUserMethod();
        }
        if (e.getSource() == removeButton) {
            removeUserMethod();
        }
    }

    private void removeUserMethod() {
        for (JRadioButton radioButton : listOfUsers) {
            if (radioButton.isSelected()) {
                String id = radioButton.getText().substring(0, radioButton.getText().indexOf(" : "));
                loginInfo.remove(id);
                listOfUsers.remove(radioButton);
            }
            mainPanel.remove(usersPanel);

            usersPanel = new JPanel(new GridLayout(listOfUsers.size(), 1, 7, 7));
            for (JRadioButton rb : listOfUsers) {
                rb.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 23));
                usersPanel.add(rb);
                buttonGroup.add(rb);
                if (rb.getText().substring(0, rb.getText().indexOf(" : ")).equals(actualElements.getUserName())) {
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

    private void addUserMethod() {
        AddUser addUser = new AddUser(parentFrame, loginInfo, currentStatusField, styleConstants);
        String newID = addUser.showDialogAndGetResult();
        if (newID != null) {
            mainPanel.remove(usersPanel);
            listOfUsers.add(new JRadioButton(newID
                    .concat(" : ")
                    .concat(loginInfo.get(newID).getPermissions().toString())));
            usersPanel = new JPanel(new GridLayout(listOfUsers.size(), 1, 7, 7));
            for (JRadioButton rb : listOfUsers) {
                rb.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 23));
                usersPanel.add(rb);
                buttonGroup.add(rb);
                if (rb.getText().substring(0, rb.getText().indexOf(" : ")).equals(actualElements.getUserName())) {
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

    private void setStyleForWindow(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof JLabel || component instanceof JPanel) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));

            }
            if (component instanceof Container) {
                setStyleForWindow((Container) component);
            }
            if (component instanceof JTextField) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
            }
        }
    }
}


