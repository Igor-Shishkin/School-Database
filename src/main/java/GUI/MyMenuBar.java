package GUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import database.Pupil;
import database.PupilsDataList;
import database.WriteReadDataToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class MyMenuBar extends JMenuBar implements ActionListener {
    Properties properties;
    JMenu fileMenu;
    JMenu styleMenu;
    JMenu informationMenu;
    JMenuItem openFileItem;
    JMenuItem newDatabaseItem;
    JMenuItem saveFileItem;
    JMenuItem closeItem;
    JMenu userMenu;
    JMenuItem logOutItem;
    JMenuItem ocean;
    JMenuItem changeAdmissionItem;
    JMenuItem informationItem;
    JMenuItem softRose;
    JMenuItem aggressive;
    JMenuItem contrast;
    WriteReadDataToFile writeReadDataToFile;
    PupilsDataList pupilsDataList = new PupilsDataList();

    MyMenuBar() throws IOException {
        writeReadDataToFile = new WriteReadDataToFile();

        Path iconsPath = Paths.get("src", "main", "resources", "icons");
        URL imageURL = MainWindow.class.getResource("/src/main/resources/icons/goals.png");
        assert imageURL != null;
        ImageIcon fileIcon = new ImageIcon(iconsPath.resolve("file.png").toString());
        ImageIcon infoIcon = new ImageIcon(iconsPath.resolve("info.png").toString());
        ImageIcon userIcon = new ImageIcon(iconsPath.resolve("user.png").toString());
        ImageIcon styleIcon = new ImageIcon(iconsPath.resolve("style.png").toString());

        fileMenu = new JMenu("File");
        styleMenu = new JMenu("Style");
        informationMenu = new JMenu("Information");
        userMenu = new JMenu("User");

        fileMenu.setIcon(fileIcon);
        informationMenu.setIcon(infoIcon);
        styleMenu.setIcon(styleIcon);
        userMenu.setIcon(userIcon);

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

        this.add(fileMenu);
        this.add(userMenu);
        this.add(styleMenu);
        this.add(informationMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        styleMenu.setMnemonic(KeyEvent.VK_S);
        userMenu.setMnemonic(KeyEvent.VK_U);
        informationMenu.setMnemonic(KeyEvent.VK_H);


        openFileItem.addActionListener(this);
        newDatabaseItem.addActionListener(this);
        saveFileItem.addActionListener(this);
        changeAdmissionItem.addActionListener(this);
        informationItem.addActionListener(this);
        logOutItem.addActionListener(this);
        changeAdmissionItem.addActionListener(this);
        closeItem.addActionListener(this);

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
                    MainWindow.setTextForStatusPanel("Database loaded successfully");
                    String nameOfFile = file.getName();
                    Main.setTitleForFrame(nameOfFile);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "\t\tI can't read this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                            JOptionPane.ERROR_MESSAGE);
                    MainWindow.setTextForStatusPanel("Error. No data has been loaded at the moment.");
                    throw new RuntimeException(ex);
                }
            }
        }

        if (e.getSource() == saveFileItem) {

            if (PupilsDataList.getPupilsDataList().size() != 0) {
                JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(null);
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        writeReadDataToFile.writeListLoFile(PupilsDataList.getPupilsDataList(), file);
                        MainWindow.setTextForStatusPanel("Database saved successfully");
                    } catch (JsonProcessingException ex) {
                        JOptionPane.showMessageDialog(null,
                                "\t\tI can't write this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                JOptionPane.ERROR_MESSAGE);
                        MainWindow.setTextForStatusPanel("Error. I can't save this data");
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your database is empty", "title",
                        JOptionPane.INFORMATION_MESSAGE);
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
}
