package GUI;

import GUI.styleStorage.ColorsSets;
import database.WriteReadDataToFile;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;


public class MainWindow extends JFrame implements ActionListener {

//    static ArrayList<Pupil> listOfPupils;

    Properties properties;
    JTextField currentStatusField;
    Border border;
    JPanel centerPanel;
    JPanel gradesPanel;
    JPanel pupilsPanel;
    JPanel panelForFilterRadioButtons;
    JPanel downPanel;
    WriteReadDataToFile writeReadDataToFile;
    MyMenuBar myMenuBar;
    JTree treeForGradePanel;
    DefaultMutableTreeNode rootForGradePanel;
    DefaultTreeModel gradesTreeModel;


    MainWindow() throws IOException, FontFormatException {
        setColorsSet();
        setActualSetOfColors(ColorsSets.SET_OF_COLORS_SOFT_ROSE);



        border = BorderFactory.createSoftBevelBorder(SoftBevelBorder.RAISED, ColorsSets.ACTUAL_SET_OF_COLORS.get(0),
                ColorsSets.ACTUAL_SET_OF_COLORS.get(4));

//        JPanel topPanel = new JPanel();
//        topPanel.setPreferredSize(new Dimension(10, 5));
//        topPanel.setBorder(border);
        downPanel = new JPanel(new BorderLayout());
        setCurrentStatusPanel();

        panelForFilterRadioButtons = new JPanel(new GridLayout(5, 1, 4, 4));
        rootForGradePanel = new DefaultMutableTreeNode("School");
        gradesTreeModel = new DefaultTreeModel(rootForGradePanel);
        treeForGradePanel = new JTree(gradesTreeModel);

        centerPanel = new CentralPanel(this, currentStatusField, treeForGradePanel, rootForGradePanel,
                gradesTreeModel, panelForFilterRadioButtons);
        myMenuBar = new MyMenuBar(this, currentStatusField, treeForGradePanel, gradesTreeModel,
                panelForFilterRadioButtons);

        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(1000, 700);
        this.setJMenuBar(myMenuBar.getMenuBar());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(downPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Nothing loaded");
        this.setVisible(true);

    }

    private void setColorsSet() {
        ColorsSets.SET_OF_COLORS_SOFT_ROSE.add(new Color(0xFFFFFF));
        ColorsSets.SET_OF_COLORS_SOFT_ROSE.add(new Color(0xE9FAE3));
        ColorsSets.SET_OF_COLORS_SOFT_ROSE.add(new Color(0xdee8d5));
        ColorsSets.SET_OF_COLORS_SOFT_ROSE.add(new Color(0xd5c7bc));
        ColorsSets.SET_OF_COLORS_SOFT_ROSE.add(new Color(0xac92a6));
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
    void setTitleForFrame(String text) {
        this.setTitle(text);
    }
    public void setTextForStatusPanel (String text) {
        currentStatusField.setText(text);
    }
//    public void setFonts (Component component, Font font) {
//        component.setFont(font);
//        if (component instanceof Container) {
//            for (Component child : ((Container) component).getComponents()) {
//                setFonts(child, font);
//            }
//        }
//    }
    public void setCurrentStatusPanel () {
        currentStatusField = new JTextField("Logged in. No data has been loaded at the moment.");
        currentStatusField.setPreferredSize(new Dimension(982, 25));
        currentStatusField.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        currentStatusField.setForeground(Color.DARK_GRAY);
        currentStatusField.setEditable(false);
        downPanel.setPreferredSize(new Dimension(10, 35));
        downPanel.setBorder(border);
        downPanel.add(currentStatusField, BorderLayout.WEST);
    }
    public void setStatusPanel() throws IOException, FontFormatException {
        currentStatusField = new JTextField("Logged in");
        currentStatusField.setBounds(2, 609, 977, 25);
        currentStatusField.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        currentStatusField.setForeground(ColorsSets.ACTUAL_SET_OF_COLORS.get(4));
        currentStatusField.setEditable(false);

        Path path = Paths.get("src", "main", "resources");
        File fontFile = new File(path.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        currentStatusField.setFont(remRegular);
    }
    public void setActualSetOfColors(ArrayList<Color> listOfColors) {
        for (int i = 0; i < 5; i++) {
            ColorsSets.ACTUAL_SET_OF_COLORS.add(i, listOfColors.get(i));
        }
    }


}


