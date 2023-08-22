package GUI;

import GUI.styleStorage.ColorsSets;
import database.PupilsDataList;

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
import java.util.HashMap;


public class MainWindow extends JFrame implements ActionListener {

//    static ArrayList<Pupil> listOfPupils;

    private JTextField currentStatusField;
    private final Border border;
    private final JPanel downPanel;
//    private String id;
//    HashMap<String,User> loginInfo;


    MainWindow(PupilsDataList dataList) throws IOException, FontFormatException {
//        this.id = id;
//        this.loginInfo = loginInfo;
        setColorsSet();
        setActualSetOfColors(ColorsSets.SET_OF_COLORS_SOFT_ROSE);


        border = BorderFactory.createSoftBevelBorder(SoftBevelBorder.RAISED, ColorsSets.ACTUAL_SET_OF_COLORS.get(0),
                ColorsSets.ACTUAL_SET_OF_COLORS.get(4));

//        JPanel topPanel = new JPanel();
//        topPanel.setPreferredSize(new Dimension(10, 5));
//        topPanel.setBorder(border);
        downPanel = new JPanel(new BorderLayout());
        setCurrentStatusPanel();

        JPanel panelForFilterRadioButtons = new JPanel(new GridLayout(5, 1, 4, 4));
        DefaultMutableTreeNode rootForGradePanel = new DefaultMutableTreeNode("School");
        DefaultTreeModel gradesTreeModel = new DefaultTreeModel(rootForGradePanel);
        JTree treeForGradePanel = new JTree(gradesTreeModel);
        JScrollPane paneForGradesTree = new JScrollPane(treeForGradePanel);
        paneForGradesTree.setVisible(false);
        JButton addPupilButton = new JButton("Add new pupil");
        addPupilButton.setVisible(false);

        JPanel centerPanel = new CentralPanel(this, currentStatusField, treeForGradePanel, rootForGradePanel,
                gradesTreeModel, panelForFilterRadioButtons, addPupilButton, paneForGradesTree, dataList);
        MyMenuBar myMenuBar = new MyMenuBar(this, currentStatusField, treeForGradePanel, gradesTreeModel,
                panelForFilterRadioButtons, addPupilButton, centerPanel, paneForGradesTree, dataList);

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
        ColorsSets.setColors();
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
    public static void setActualSetOfColors(ArrayList<Color> listOfColors) {
        for (int i = 0; i < 5; i++) {
            ColorsSets.ACTUAL_SET_OF_COLORS.add(i, listOfColors.get(i));
        }
    }




}


