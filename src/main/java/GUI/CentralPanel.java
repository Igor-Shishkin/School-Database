package GUI;

import database.Pupil;
import database.PupilsDataList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CentralPanel extends JPanel implements ActionListener, TreeSelectionListener {
    JPanel gradesPanel;
    static JPanel pupilsPanel;
    JPanel informationPanel;
    Border border;
    GridBagConstraints constraints;
    DefaultMutableTreeNode rootForGradePanel;
    DefaultMutableTreeNode rootForPupilsPanel;
    DefaultMutableTreeNode gradeZero;
    DefaultMutableTreeNode gradeFirst;
    DefaultMutableTreeNode gradeSecond;
    DefaultMutableTreeNode gradeThird;
    DefaultMutableTreeNode gradeFourth;
    DefaultMutableTreeNode gradeFifth;
    DefaultMutableTreeNode gradeSixth;
    DefaultMutableTreeNode gradeSeventh;
    DefaultMutableTreeNode gradeEighth;
    static DefaultMutableTreeNode[] nodesForPupilsPanel;
    static ArrayList<Pupil> pupilsChosenGrades;
    JTree treeForGradePanel;
    static JTree treeForPupilsPanel;
    Font font;
    static DefaultMutableTreeNode rootForPupilsTree;
    static DefaultTreeModel pupilsTreeModel;
    public CentralPanel() {
        this.setLayout(new GridBagLayout());

        border = BorderFactory.createLoweredBevelBorder();
        font = new Font("MV Boli",Font.BOLD,16);
        nodesForPupilsPanel = new DefaultMutableTreeNode[30];
        rootForPupilsPanel = new DefaultMutableTreeNode();

        gradesPanel = new JPanel(new BorderLayout());
        gradesPanel.setBackground(MainWindow.actualSetColor.get(2));
        gradesPanel.setBorder(border);
        setTreeNode();
        gradesPanel.setToolTipText("Grades");
        gradesPanel.add(treeForGradePanel, BorderLayout.NORTH);


        pupilsPanel = new JPanel(new FlowLayout());
        pupilsPanel.setBackground(MainWindow.actualSetColor.get(2));
        pupilsPanel.setBorder(border);
        rootForPupilsTree = new DefaultMutableTreeNode("root");
        pupilsTreeModel = new DefaultTreeModel(rootForPupilsTree);
        treeForPupilsPanel = new JTree(pupilsTreeModel);
        treeForPupilsPanel.setCellRenderer(new CustomTreeCellRenderer());
        treeForPupilsPanel.setFont(font);
        treeForPupilsPanel.setRootVisible(false);
        pupilsPanel.add(treeForPupilsPanel, BorderLayout.NORTH);

        informationPanel = new JPanel();
        informationPanel.setBackground(MainWindow.actualSetColor.get(2));
        informationPanel.setBorder(border);

        setGridBagConstraints();
        this.setBackground(MainWindow.actualSetColor.get(0));
    }

    private void setTreeNode() {
        rootForGradePanel = new DefaultMutableTreeNode("School");

        gradeZero = new DefaultMutableTreeNode("Zero class");
        gradeFirst = new DefaultMutableTreeNode("First class");
        gradeSecond = new DefaultMutableTreeNode("Second class");
        gradeThird = new DefaultMutableTreeNode("Third class");
        gradeFourth = new DefaultMutableTreeNode("Fourth class");
        gradeFifth = new DefaultMutableTreeNode("Fifth class");
        gradeSixth = new DefaultMutableTreeNode("Sixth class");
        gradeSeventh = new DefaultMutableTreeNode("Seventh class");
        gradeEighth = new DefaultMutableTreeNode("Eighth class");

        rootForGradePanel.add(gradeZero);
        rootForGradePanel.add(gradeFirst);
        rootForGradePanel.add(gradeSecond);
        rootForGradePanel.add(gradeThird);
        rootForGradePanel.add(gradeFourth);
        rootForGradePanel.add(gradeFifth);
        rootForGradePanel.add(gradeSixth);
        rootForGradePanel.add(gradeSeventh);
        rootForGradePanel.add(gradeEighth);

        treeForGradePanel = new JTree(rootForGradePanel);
        treeForGradePanel.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeForGradePanel.addTreeSelectionListener(this);

        treeForGradePanel.putClientProperty("JTree.lineStyle", "Angled");
        treeForGradePanel.setFont(font);
        treeForGradePanel.setBackground(MainWindow.actualSetColor.get(2));
//        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer());
        treeForGradePanel.setCellRenderer(new TextTreeCellRenderer());
        treeForGradePanel.addMouseListener(new TextTreeNodeMouseListener(treeForGradePanel));
        treeForGradePanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForGradePanel));

        JScrollPane pane = new JScrollPane(treeForGradePanel);
        pane.setPreferredSize(new Dimension(250, 350));

    }

//

//        public void setBackground (DefaultMutableTreeNode node, Color color) {
//        node.setParent(color);
//        if (node instanceof Container) {
//            for (Component child : ((Container) component).getComponents()) {
//                setParent(child, color);
//            }
//        }
//    }

    private void setGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        constraints.insets = new Insets(5,3,5,2);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(gradesPanel, constraints);

        constraints.weightx = 0.65;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(pupilsPanel, constraints);

        constraints.weightx = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        this.add(informationPanel, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }

    public static void showPupilsOfCertainGrade (ArrayList<Pupil> list) {
        rootForPupilsTree.removeAllChildren();
        for (int i = 0; i < list.size(); i++) {
            nodesForPupilsPanel[i] = new DefaultMutableTreeNode(list.get(i).getIdNamesSurname());
            rootForPupilsTree.add(nodesForPupilsPanel[i]);
        }
        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        pupilsPanel.repaint();
    }
}
