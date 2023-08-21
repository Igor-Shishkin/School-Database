package GUI;

import GUI.styleStorage.ColorsSets;
import GUI.styleStorage.ConstantsOfStyle;
import com.fasterxml.jackson.core.JsonProcessingException;
import database.Pupil;
import database.PupilsDataList;
import database.WriteReadDataToFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class MyMenuBar  implements ActionListener {
    Properties properties;
    private JMenu fileMenu, styleMenu, informationMenu, userMenu;
    private JMenuItem openFileItem, newDatabaseItem, saveFileItem, closeItem, logOutItem, ocean, changeAdmissionItem,
            informationItem, softRose, aggressive, contrast;
    private final JMenuBar menuBar;
    private final WriteReadDataToFile writeReadDataToFile;
    private final JFrame parentFrame;
    private final JTextField currentStatusField;
    private final JTree treeForGradePanel;
    private DefaultTreeModel gradesTreeModel;
    private final JPanel panelForFilterRadioButtons, centralPanel;
    private final JButton addPupilButton;
    private final JScrollPane paneForGradesTree;
    private final PupilsDataList dataList;
    private Permissions permissions;

    MyMenuBar(JFrame parentFrame, JTextField currentStatusField, JTree treeForGradePanel,
              DefaultTreeModel gradesTreeModel, JPanel panelForFilterRadioButtons, JButton addPupilButton,
              JPanel centralPanel, JScrollPane paneForGradesTree, PupilsDataList dataList,
              Permissions permissions)
            throws IOException {
        this.parentFrame = parentFrame;
        this.currentStatusField = currentStatusField;
        this.treeForGradePanel = treeForGradePanel;
        this.gradesTreeModel = gradesTreeModel;
        this.panelForFilterRadioButtons = panelForFilterRadioButtons;
        this.addPupilButton = addPupilButton;
        this.centralPanel = centralPanel;
        this.paneForGradesTree = paneForGradesTree;
        this.dataList = dataList;
        this.permissions = permissions;

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
        changeAdmissionItem.addActionListener(this);
        informationItem.addActionListener(this);
        logOutItem.addActionListener(this);
        closeItem.addActionListener(this);
        contrast.addActionListener(this);
        aggressive.addActionListener(this);
        ocean.addActionListener(this);
    }

    private void setIconsToMenuComponents() {
        fileMenu.setIcon(ConstantsOfStyle.FILE_ICON);
        informationMenu.setIcon(ConstantsOfStyle.INFO_ICON);
        styleMenu.setIcon(ConstantsOfStyle.STYLE_ICON);
        userMenu.setIcon(ConstantsOfStyle.USER_ICON);
    }

    private void setMenuComponents() {
        fileMenu = new JMenu("File");
        styleMenu = new JMenu("Style");
        informationMenu = new JMenu("Information");
        userMenu = new JMenu("User");

        newDatabaseItem = new JMenuItem("New");
        openFileItem = new JMenuItem("Open");
        saveFileItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        logOutItem = new JMenuItem("Log out");
        changeAdmissionItem = new JMenuItem("Change admission");
        informationItem = new JMenuItem("Information");
        softRose = new JMenuItem("Soft rose");
        ocean = new JMenuItem("Ocean");
        contrast = new JMenuItem("Contrast");
        aggressive = new JMenuItem("Aggressive");

        fileMenu.add(newDatabaseItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(closeItem);

        styleMenu.add(ocean);
        styleMenu.add(softRose);
        styleMenu.add(contrast);
        styleMenu.add(aggressive);
        informationMenu.add(informationItem);
        userMenu.add(changeAdmissionItem);
        userMenu.add(logOutItem);

        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(styleMenu);
        menuBar.add(informationMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        styleMenu.setMnemonic(KeyEvent.VK_S);
        userMenu.setMnemonic(KeyEvent.VK_U);
        informationMenu.setMnemonic(KeyEvent.VK_H);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeItem) {
            System.exit(0);
        }
        if (e.getSource() == openFileItem) {
            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(null);
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    PupilsDataList.setPupilsDataList(writeReadDataToFile.readListFromFile(file));
//                    Main.setTextForStatusPanel("Database loaded successfully");
                    currentStatusField.setText("Database loaded successfully");
                    String nameOfFile = file.getName();
                    parentFrame.setTitle(nameOfFile);
                    treeForGradePanel.setVisible(true);
                    panelForFilterRadioButtons.setVisible(true);
                    addPupilButton.setVisible(permissions==Permissions.DIRECTOR);
                    paneForGradesTree.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "\t\tI can't read this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                            JOptionPane.ERROR_MESSAGE);
                    currentStatusField.setText("Error. No data has been loaded at the moment.");
                    throw new RuntimeException(ex);
                }
            }
        }

        if (e.getSource() == saveFileItem) {

            if (dataList.getPupilsDataList().size() != 0) {
                JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(null);
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        writeReadDataToFile.writeListLoFile(dataList.getPupilsDataList(), file);
//                        statusTextField.setText("Database saved successfully");
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
        if (e.getSource()==contrast) {
            ColorsSets.setActualSetOfColors(ColorsSets.SET_OF_COLORS_CONTRAST);
            refreshPanels(centralPanel);
            centralPanel.repaint();

            fileMenu.setBackground(Color.BLACK);
            menuBar.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(5));
            setMenuBarColors(Color.BLUE, ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        }
        if (e.getSource()==ocean) {
            ColorsSets.setActualSetOfColors(ColorsSets.SET_OF_COLORS_OCEAN);
            refreshPanels(centralPanel);
            centralPanel.repaint();

            menuBar.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(5));
            setMenuBarColors(ColorsSets.ACTUAL_SET_OF_COLORS.get(2), ColorsSets.ACTUAL_SET_OF_COLORS.get(4));

        }
        if (e.getSource() == changeAdmissionItem) {
            new ChangeLogin(parentFrame, permissions);
            System.out.println(permissions);
            addPupilButton.setVisible(permissions.getNumberPermission()==9);
        }
    }

    private void refreshMenu(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JMenuItem) {
                component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(4));
            }
            if (component instanceof Container) {
                refreshPanels((Container) component);
            }
        }
    }

    public void setMenuBarColors(Color foreground, Color background) {
        fileMenu.setForeground(foreground);
        styleMenu.setForeground(foreground);
        informationMenu.setForeground(foreground);
        userMenu.setForeground(foreground);

        newDatabaseItem.setForeground(foreground);
        openFileItem.setForeground(foreground);
        saveFileItem.setForeground(foreground);
        closeItem.setForeground(foreground);
        logOutItem.setForeground(foreground);
        ocean.setForeground(foreground);
        changeAdmissionItem.setForeground(foreground);
        informationItem.setForeground(foreground);

        fileMenu.setBackground(background);
        styleMenu.setBackground(background);
        informationMenu.setBackground(background);
        userMenu.setBackground(background);

        newDatabaseItem.setBackground(background);
        openFileItem.setBackground(background);
        saveFileItem.setBackground(background);
        closeItem.setBackground(background);
        logOutItem.setBackground(background);
        ocean.setBackground(background);
        changeAdmissionItem.setBackground(background);
        informationItem.setBackground(background);
    }
    public JMenuBar getMenuBar () {
        return menuBar;
    }

    public void refreshPanels (Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(5));
                component.setForeground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
                if (Objects.equals(((JButton) component).getText(), "DELETE PUPIL")){
                    component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
                    component.setForeground(ColorsSets.ACTUAL_SET_OF_COLORS.get(7));
                }
                if (Objects.equals(((JButton) component).getText(), "Add new pupil")){
                    component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
                    component.setForeground(ColorsSets.ACTUAL_SET_OF_COLORS.get(8));
                }
            }
            if (component instanceof JLabel || component instanceof JTextField ||
                    component instanceof JPanel || component instanceof JScrollPane ||
                    component instanceof JRadioButton || component instanceof JTree) {
                component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(0));
            }
            if (component instanceof Container) {
                refreshPanels((Container) component);
            }
        }
    }

}
