package school.database.GUI;

import school.database.GUI.addEditWondows.EditUsers;
import school.database.GUI.styleStorage.ConstantsOfStyle;
import school.database.Main;
import school.database.data.Data;
import school.database.data.WriteReadDataToFile;
import school.database.data.objects.Permissions;
import school.database.data.objects.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class MyMenuBar implements ActionListener {
    private JMenu fileMenu, styleMenu, informationMenu, userMenu;
    private JMenuItem openFileItem, newDatabaseItem, saveFileItem, exitItem, usersItem, ocean, changeAdmissionItem,
            informationItem, contrast, saveAsFileItem;
    private final JMenuBar menuBar;
    private final WriteReadDataToFile writeReadDataToFile;
    private final JFrame parentFrame;
    private final JTextField currentStatusField;
    private final JTree treeForGradePanel;
    private final JPanel panelForFilterRadioButtons, centralPanel;
    private final JButton addPupilButton;
    private final JScrollPane paneForGradesTree;
    private final Data dataList;
    File file;
    DefaultMutableTreeNode rootForPupilsTree;
    DefaultTreeModel pupilsTreeModel;
    ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel;
    ConstantsOfStyle styleConstants;
    JTree treeForPupilsPanel;


    MyMenuBar(JFrame parentFrame, JTextField currentStatusField, JTree treeForGradePanel,
              DefaultTreeModel gradesTreeModel, JPanel panelForFilterRadioButtons, JButton addPupilButton,
              JPanel centralPanel, JScrollPane paneForGradesTree, Data dataList,
              DefaultMutableTreeNode rootForPupilsTree, DefaultTreeModel pupilsTreeModel,
              ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel, ConstantsOfStyle styleConstants,
              JTree treeForPupilsPanel)
                throws IOException {
        this.parentFrame = parentFrame;
        this.currentStatusField = currentStatusField;
        this.treeForGradePanel = treeForGradePanel;
        this.panelForFilterRadioButtons = panelForFilterRadioButtons;
        this.addPupilButton = addPupilButton;
        this.centralPanel = centralPanel;
        this.paneForGradesTree = paneForGradesTree;
        this.dataList = dataList;
        this.rootForPupilsTree = rootForPupilsTree;
        this.pupilsTreeModel = pupilsTreeModel;
        this.nodesForPupilsPanel = nodesForPupilsPanel;
        this.styleConstants = styleConstants;
        this.treeForPupilsPanel = treeForPupilsPanel;

        menuBar = new JMenuBar();
        writeReadDataToFile = new WriteReadDataToFile();

        setMenuComponents();
        setIconsToMenuComponents();
        addActionListenerToComponents();
    }

    private void addActionListenerToComponents() {
        openFileItem.addActionListener(this);
        newDatabaseItem.addActionListener(this);
        saveFileItem.addActionListener(this);
        saveAsFileItem.addActionListener(this);
        changeAdmissionItem.addActionListener(this);
        informationItem.addActionListener(this);
        usersItem.addActionListener(this);
        exitItem.addActionListener(this);
        contrast.addActionListener(this);
        ocean.addActionListener(this);
    }

    private void setIconsToMenuComponents() {
        fileMenu.setIcon(styleConstants.getFILE_ICON());
        informationMenu.setIcon(styleConstants.getINFO_ICON());
        styleMenu.setIcon(styleConstants.getSTYLE_ICON());
        userMenu.setIcon(styleConstants.getUSER_ICON());
    }

    private void setMenuComponents() {
        fileMenu = new JMenu("File");
        styleMenu = new JMenu("Style");
        informationMenu = new JMenu("Information");
        userMenu = new JMenu("User");

        newDatabaseItem = new JMenuItem("New");
        openFileItem = new JMenuItem("Open...");
        saveFileItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        usersItem = new JMenuItem("Users");
        changeAdmissionItem = new JMenuItem("Change admission");
        informationItem = new JMenuItem("Information");
        ocean = new JMenuItem("Ocean");
        contrast = new JMenuItem("Contrast");
        saveAsFileItem = new JMenuItem("Save as...");


        fileMenu.add(newDatabaseItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(saveAsFileItem);
        fileMenu.add(exitItem);

        styleMenu.add(ocean);
        styleMenu.add(contrast);
        informationMenu.add(informationItem);
        userMenu.add(changeAdmissionItem);
        userMenu.add(usersItem);

        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(styleMenu);
        menuBar.add(informationMenu);

        changeAdmissionItem.setEnabled(false);
        saveFileItem.setEnabled(false);
        saveAsFileItem.setEnabled(false);
        usersItem.setEnabled(false);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        styleMenu.setMnemonic(KeyEvent.VK_S);
        userMenu.setMnemonic(KeyEvent.VK_U);
        informationMenu.setMnemonic(KeyEvent.VK_H);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitItem) {
            int option = JOptionPane.showConfirmDialog(
                    null, "Do you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        if (e.getSource() == openFileItem) {
            if (dataList.getPupilsDataList() != null) {
                String[] responses = {"Open without saving", "Save changes at first", "Cancel"};
                int answer = JOptionPane.showOptionDialog(parentFrame, "Would you like to save changes\nin previous database at first",
                        "Save previous database?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        responses, responses[0]);
                if (answer == JOptionPane.YES_OPTION) {

                    JFileChooser fileChooser = new JFileChooser();
                    int response = fileChooser.showOpenDialog(null);

                    if (response == JFileChooser.APPROVE_OPTION) {
                        rootForPupilsTree.removeAllChildren();
                        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
                        setComponentsInvisible(centralPanel);
                        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        try {
                            dataList.setDataObject(writeReadDataToFile.readDataFromFile(file));
                            currentStatusField.setText("Database loaded successfully");
                            ChangeLogin changeLogin = new ChangeLogin(parentFrame,
                                    (HashMap<String, User>) dataList.getLoginInfo());
                            if (changeLogin.showAndReturnIsSuccess()) {
                                String nameOfFile = file.getName();
                                parentFrame.setTitle(nameOfFile);
                                treeForGradePanel.setVisible(true);
                                panelForFilterRadioButtons.setVisible(true);
                                addPupilButton.setVisible
                                        (dataList.getLoginInfo().get(Main.CURRENT_USER).getPermissions() == Permissions.DIRECTOR);
                                paneForGradesTree.setVisible(true);
                                changeAdmissionItem.setEnabled(true);
                                saveFileItem.setEnabled(true);
                                saveAsFileItem.setEnabled(true);
                            } else {
                                dataList.setPupilsDataList(null);
                                dataList.setPupilsDataList(null);
                                changeAdmissionItem.setEnabled(false);
                                saveFileItem.setEnabled(false);
                                saveAsFileItem.setEnabled(false);
                                usersItem.setEnabled(false);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    "\t\tI can't read this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                    JOptionPane.ERROR_MESSAGE);
                            currentStatusField.setText("Error. No data has been loaded at the moment.");
                            throw new RuntimeException(ex);
                        }
                    }
                } else if (answer == JOptionPane.NO_OPTION) {
                    rootForPupilsTree.removeAllChildren();
                    pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
                    setComponentsInvisible(centralPanel);
                    try {
                        if (file==null) {
                            JFileChooser fileChooser = new JFileChooser();
                            int response = fileChooser.showSaveDialog(null);

                            if (response == JFileChooser.APPROVE_OPTION) {
                                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                try {
                                    writeReadDataToFile.writeDataToFile(dataList.getDataToFile(), file);
                                    currentStatusField.setText("Database is saved successfully");
                                    JOptionPane.showMessageDialog(null,
                                            "\t\tDatabase is saved!", "SUCCESS",
                                            JOptionPane.PLAIN_MESSAGE);

                                } catch (JsonProcessingException ex) {
                                    JOptionPane.showMessageDialog(null,
                                            "\t\tI can't write this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                            JOptionPane.ERROR_MESSAGE);
                                    currentStatusField.setText("Error. I can't save this data");
                                    throw new RuntimeException(ex);
                                }
                            }
                        } else {
                            writeReadDataToFile.writeDataToFile(dataList.getDataToFile(), file);
                            currentStatusField.setText("Database is saved successfully");
                            JOptionPane.showMessageDialog(null,
                                    "\t\tDatabase is saved!", "SUCCESS",
                                    JOptionPane.PLAIN_MESSAGE);
                        }

                        JFileChooser fileChooser = new JFileChooser();
                        int response = fileChooser.showOpenDialog(null);

                        if (response == JFileChooser.APPROVE_OPTION) {
                            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                            try {
                                dataList.setDataObject(writeReadDataToFile.readDataFromFile(file));
                                currentStatusField.setText("Database loaded successfully");
                                ChangeLogin changeLogin = new ChangeLogin(parentFrame,
                                        (HashMap<String, User>)dataList.getLoginInfo());
                                if (changeLogin.showAndReturnIsSuccess()) {
                                    String nameOfFile = file.getName();
                                    parentFrame.setTitle(nameOfFile);
                                    treeForGradePanel.setVisible(true);
                                    panelForFilterRadioButtons.setVisible(true);
                                    addPupilButton.setVisible
                                            (dataList.getLoginInfo()
                                                    .get(Main.CURRENT_USER).getPermissions() == Permissions.DIRECTOR);
                                    paneForGradesTree.setVisible(true);
                                    changeAdmissionItem.setEnabled(true);
                                    saveFileItem.setEnabled(true);
                                    exitItem.setEnabled(true);
                                } else {
                                    dataList.setPupilsDataList(null);
                                    dataList.setPupilsDataList(null);
                                    changeAdmissionItem.setEnabled(false);
                                    saveFileItem.setEnabled(false);
                                    saveAsFileItem.setEnabled(false);
                                    usersItem.setEnabled(false);
                                }
                                saveFileItem.setEnabled(true);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null,
                                        "\t\tI can't read this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                        JOptionPane.ERROR_MESSAGE);
                                currentStatusField.setText("Error. No data has been loaded at the moment.");
                                throw new RuntimeException(ex);
                            }
                        }
                    } catch (JsonProcessingException ex) {
                        JOptionPane.showMessageDialog(null,
                                "\t\tI can't write this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                JOptionPane.ERROR_MESSAGE);
                        currentStatusField.setText("Error. I can't save this data");
                        throw new RuntimeException(ex);
                    }

                }
            } else {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        dataList.setDataObject(writeReadDataToFile.readDataFromFile(file));
                        currentStatusField.setText("Database loaded successfully");
                        ChangeLogin changeLogin = new ChangeLogin(parentFrame,
                                (HashMap<String, User>)dataList.getLoginInfo());
                        if (changeLogin.showAndReturnIsSuccess()) {
                            String nameOfFile = file.getName();
                            parentFrame.setTitle(nameOfFile);
                            treeForGradePanel.setVisible(true);
                            panelForFilterRadioButtons.setVisible(true);
                            addPupilButton.setVisible
                                    (dataList.getLoginInfo().get(Main.CURRENT_USER)
                                            .getPermissions() == Permissions.DIRECTOR);
                            paneForGradesTree.setVisible(true);
                            changeAdmissionItem.setEnabled(true);
                            saveFileItem.setEnabled(true);
                            saveAsFileItem.setEnabled(true);
                            usersItem.setEnabled(true);
                        } else {
                            dataList.setPupilsDataList(null);
                            dataList.setPupilsDataList(null);
                            changeAdmissionItem.setEnabled(false);
                            saveFileItem.setEnabled(false);
                            saveAsFileItem.setEnabled(false);
                            usersItem.setEnabled(false);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,
                                "\t\tI can't read this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                JOptionPane.ERROR_MESSAGE);
                        currentStatusField.setText("Error. No data has been loaded at the moment.");
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

        if (e.getSource() == saveAsFileItem) {

            if (dataList.getPupilsDataList().size() != 0) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        writeReadDataToFile.writeDataToFile(dataList.getDataToFile(), file);
                        currentStatusField.setText("Database is saved successfully");
                        saveFileItem.setEnabled(true);
                    } catch (JsonProcessingException ex) {
                        JOptionPane.showMessageDialog(null,
                                "\t\tI can't write this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                JOptionPane.ERROR_MESSAGE);
                        currentStatusField.setText("Error. I can't save this data");
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your database is empty", "title",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == saveFileItem) {

            if (dataList.getPupilsDataList().size() != 0 || file != null) {
                try {
                    writeReadDataToFile.writeDataToFile(dataList.getDataToFile(), file);
                    JOptionPane.showMessageDialog(null,
                            "Database is saved :)", "SUCCESS",
                            JOptionPane.PLAIN_MESSAGE);
                    currentStatusField.setText("Database saved is successfully");
                } catch (JsonProcessingException ex) {
                    JOptionPane.showMessageDialog(null,
                            "\t\tI can't write this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                            JOptionPane.ERROR_MESSAGE);
                    currentStatusField.setText("Error. I can't save this data");
                    throw new RuntimeException(ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Your database is empty", "title",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == newDatabaseItem) {
            if (dataList.getPupilsDataList() != null) {
                String[] responses = {"Open without saving", "Save changes at first", "Cancel"};
                int answer = JOptionPane.showOptionDialog(parentFrame, "Would you like to save changes\nin previous database at first",
                        "Save previous database?", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE, null,
                        responses, responses[0]);
                if (answer == JOptionPane.YES_OPTION) {
                    saveFileItem.setEnabled(false);
                    changeAdmissionItem.setEnabled(false);
                    usersItem.setEnabled(false);
                    saveAsFileItem.setEnabled(false);
                    file = null;
                    rootForPupilsTree.removeAllChildren();
                    pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
                    setComponentsInvisible(centralPanel);

                    dataList.setPupilsDataList(new ArrayList<>());
                    dataList.setLoginInfo(new HashMap<>());
                    dataList.getLoginInfo().put("Director", new User("0000", Permissions.DIRECTOR));
                    Main.CURRENT_USER = "Director";
                    JOptionPane.showMessageDialog(null,
                            "\t\tDatabase is created!\nUser 'Director' with '0000' password \n is added",
                            "SUCCESS",
                            JOptionPane.PLAIN_MESSAGE);
                    changeAdmissionItem.setEnabled(true);
                    usersItem.setEnabled(true);
                    saveAsFileItem.setEnabled(true);
                    currentStatusField.setText("New database is created");
                    addPupilButton.setVisible(true);


                } else if (answer == JOptionPane.NO_OPTION) {
                    rootForPupilsTree.removeAllChildren();
                    pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
                    setComponentsInvisible(centralPanel);
                    try {
                        writeReadDataToFile.writeDataToFile(dataList.getDataToFile(), file);
                        currentStatusField.setText("Database is saved successfully");
                        JOptionPane.showMessageDialog(null,
                                "\t\tDatabase is saved!", "SUCCESS",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (JsonProcessingException ex) {
                        JOptionPane.showMessageDialog(null,
                                "\t\tI can't write this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                JOptionPane.ERROR_MESSAGE);
                        currentStatusField.setText("Error. I can't save this data");
                        throw new RuntimeException(ex);
                    }
                    dataList.setPupilsDataList(new ArrayList<>());
                    dataList.setLoginInfo(new HashMap<>());
                    dataList.getLoginInfo().put("Director", new User("0000", Permissions.DIRECTOR));
                    Main.CURRENT_USER = "Director";
                    JOptionPane.showMessageDialog(null,
                            "\t\tDatabase is created!\nUser 'Director' with '0000' password \n is added",
                            "SUCCESS",
                            JOptionPane.PLAIN_MESSAGE);
                    file = null;
                    saveAsFileItem.setEnabled(true);
                    usersItem.setEnabled(true);
                    changeAdmissionItem.setEnabled(true);
                    currentStatusField.setText("New database is created");
                    addPupilButton.setVisible(true);

                }

            } else {
                file = null;
                dataList.setPupilsDataList(new ArrayList<>());
                dataList.setLoginInfo(new HashMap<>());
                dataList.getLoginInfo().put("Director", new User("0000", Permissions.DIRECTOR));
                Main.CURRENT_USER = "Director";
                JOptionPane.showMessageDialog(null,
                        "\t\tDatabase is created!\nUser 'Director' with '0000' password \n is added",
                        "SUCCESS",
                        JOptionPane.PLAIN_MESSAGE);
                saveAsFileItem.setEnabled(true);
                usersItem.setEnabled(true);
                changeAdmissionItem.setEnabled(true);
                currentStatusField.setText("New database is created");
                treeForGradePanel.setVisible(true);
                panelForFilterRadioButtons.setVisible(true);
                addPupilButton.setVisible(true);
                paneForGradesTree.setVisible(true);

            }
        }
        if (e.getSource() == contrast) {
            styleConstants.setActualSetOfColors(styleConstants.getSET_OF_COLORS_CONTRAST());
            refreshPanels(centralPanel);
            centralPanel.repaint();

            fileMenu.setBackground(Color.BLACK);
            menuBar.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
            setMenuBarColors(Color.BLUE, styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        }
        if (e.getSource() == ocean) {
            styleConstants.setActualSetOfColors(styleConstants.getSET_OF_COLORS_OCEAN());
            refreshPanels(centralPanel);
            centralPanel.repaint();

            menuBar.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
            setMenuBarColors(styleConstants.getACTUAL_SET_OF_COLORS().get(2), styleConstants.getACTUAL_SET_OF_COLORS().get(4));
        }
        if (e.getSource() == changeAdmissionItem) {
            ChangeLogin changeLogin = new ChangeLogin(parentFrame,
                    (HashMap<String, User>)dataList.getLoginInfo());
            changeLogin.showAndReturnIsSuccess();
            addPupilButton.setVisible
                    (dataList.getLoginInfo().get(Main.CURRENT_USER).getPermissions() == Permissions.DIRECTOR);
            currentStatusField.setText("Actual permissions: "
                    .concat(dataList.getLoginInfo().get(Main.CURRENT_USER).getPermissions().toString()));
        }
        if (e.getSource() == usersItem) {
            new EditUsers(parentFrame, dataList, currentStatusField, styleConstants);
        }
    }

    private void refreshMenu(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JMenuItem) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(4));
            }
            if (component instanceof Container) {
                refreshMenu((Container) component);
            }
        }
    }

    public void setMenuBarColors(Color foreground, Color background) {
        menuBar.setForeground(foreground);
        fileMenu.setForeground(foreground);
        styleMenu.setForeground(foreground);
        informationMenu.setForeground(foreground);
        userMenu.setForeground(foreground);

        newDatabaseItem.setForeground(foreground);
        openFileItem.setForeground(foreground);
        saveFileItem.setForeground(foreground);
        saveAsFileItem.setForeground(foreground);
        exitItem.setForeground(foreground);
        usersItem.setForeground(foreground);
        ocean.setForeground(foreground);
        changeAdmissionItem.setForeground(foreground);
        informationItem.setForeground(foreground);
        contrast.setForeground(foreground);

        menuBar.setBackground(background);

        fileMenu.setBackground(background);
        styleMenu.setBackground(background);
        informationMenu.setBackground(background);
        userMenu.setBackground(background);

        newDatabaseItem.setBackground(background);
        openFileItem.setBackground(background);
        saveFileItem.setBackground(background);
        saveAsFileItem.setBackground(background);
        exitItem.setBackground(background);
        usersItem.setBackground(background);
        ocean.setBackground(background);
        changeAdmissionItem.setBackground(background);
        informationItem.setBackground(background);
        contrast.setBackground(background);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void refreshPanels(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
                if (Objects.equals(((JButton) component).getText(), "DELETE PUPIL")) {
                    component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(8));
                    component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
                }
                if (Objects.equals(((JButton) component).getText(), "Add new pupil")) {
                    component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(8));
                    component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(3));
                }
                if (!Objects.equals(((JButton) component).getText(), "Add new pupil")) {
                    ((JButton) component).setBorder(BorderFactory.createLineBorder
                            (styleConstants.getACTUAL_SET_OF_COLORS().get(0), 3));
                }
            }
            if (component instanceof JLabel || component instanceof JTextField ||
                    component instanceof JPanel || component instanceof JScrollPane ||
                    component instanceof JRadioButton || component instanceof JTree) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof Container) {
                refreshPanels((Container) component);
            }
            currentStatusField.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
            currentStatusField.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
            setMenuBarColors(styleConstants.getACTUAL_SET_OF_COLORS().get(2),
                    styleConstants.getACTUAL_SET_OF_COLORS().get(5));

        }
    }

    public void setComponentsInvisible(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setVisible(false);
            }
            if (component instanceof JLabel) {
                ((JLabel) component).setText("");
            }
            if (component instanceof Container) {
                setComponentsInvisible((Container) component);
            }
        }
    }
}
