package school.database.GUI;

import school.database.GUI.styleStorage.ConstantsOfStyle;
import school.database.data.Data;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class MainWindow extends JFrame implements ActionListener {

//    static ArrayList<Pupil> listOfPupils;

    private JTextField currentStatusField;
    private final Border border;
    private final JPanel downPanel;
    ConstantsOfStyle styleConstants;
    MyMenuBar myMenuBar;
    ActualElements actualElements;


    public MainWindow(Data dataList, ConstantsOfStyle styleConstants, ActualElements actualElements)
            throws IOException {
        this.styleConstants = styleConstants;
        this.actualElements = actualElements;

        border = BorderFactory.createSoftBevelBorder
                (SoftBevelBorder.RAISED, styleConstants.getACTUAL_SET_OF_COLORS().get(0),
                        styleConstants.getACTUAL_SET_OF_COLORS().get(4));

        downPanel = new JPanel(new BorderLayout());
        setCurrentStatusPanel();

        JPanel panelForFilterRadioButtons = new JPanel(new GridLayout(5, 1, 4, 4));
        DefaultMutableTreeNode rootForGradePanel = new DefaultMutableTreeNode("School");
        DefaultTreeModel gradesTreeModel = new DefaultTreeModel(rootForGradePanel);
        JTree treeForGradePanel = new JTree(gradesTreeModel);
        JScrollPane paneForGradesTree = new JScrollPane(treeForGradePanel);

        DefaultMutableTreeNode rootForPupilsTree = new DefaultMutableTreeNode("root");
        DefaultTreeModel pupilsTreeModel = new DefaultTreeModel(rootForPupilsTree);
        JTree treeForPupilsPanel = new JTree(pupilsTreeModel);

        paneForGradesTree.setVisible(false);
        JButton addPupilButton = new JButton("Add new pupil");
        addPupilButton.setVisible(false);


        ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel = new ArrayList<>();


        JPanel centerPanel = new CentralPanel(this, currentStatusField, treeForGradePanel, rootForGradePanel,
                gradesTreeModel, panelForFilterRadioButtons, addPupilButton, paneForGradesTree, dataList, pupilsTreeModel,
                nodesForPupilsPanel, rootForPupilsTree, styleConstants, treeForPupilsPanel, actualElements);
        myMenuBar = new MyMenuBar(this, currentStatusField, treeForGradePanel, gradesTreeModel,
                panelForFilterRadioButtons, addPupilButton, centerPanel, paneForGradesTree, dataList,
                rootForPupilsTree, pupilsTreeModel, nodesForPupilsPanel, styleConstants, treeForPupilsPanel, actualElements);

        refreshPanels(centerPanel);

        setWindowCloseListener();
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(1000, 700);
        this.setJMenuBar(myMenuBar.getMenuBar());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(downPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Nothing loaded");
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
    public void setCurrentStatusPanel () {
        currentStatusField = new JTextField("Logged in. No data has been loaded at the moment.");
        currentStatusField.setPreferredSize(new Dimension(982, 25));
        currentStatusField.setEditable(false);
        downPanel.setPreferredSize(new Dimension(10, 35));
        downPanel.setBorder(border);
        downPanel.add(currentStatusField, BorderLayout.WEST);
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
            myMenuBar.setMenuBarColors(styleConstants.getACTUAL_SET_OF_COLORS().get(2),
                    styleConstants.getACTUAL_SET_OF_COLORS().get(5));
        }
    }
    private void setWindowCloseListener() {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        null, "Do you want to exit without saving?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        };
        this.addWindowListener(windowListener);
    }


}


