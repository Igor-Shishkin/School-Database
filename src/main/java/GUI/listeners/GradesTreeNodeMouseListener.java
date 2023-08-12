package GUI.listeners;

import database.Pupil;
import database.PupilsDataList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class GradesTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;
    DefaultMutableTreeNode rootForPupilsTree;
    ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel;
    DefaultTreeModel pupilsTreeModel;
    JLabel informationLabelForPupilPanel;
    JPanel pupilsPanel;
    ArrayList<Pupil> list;

    public GradesTreeNodeMouseListener(JTree tree, DefaultMutableTreeNode rootForPupilsTree,
                  ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel, DefaultTreeModel pupilsTreeModel,
                  JLabel informationLabelForPupilPanel, JPanel pupilsPanel) {
        this.tree = tree;
        this.rootForPupilsTree = rootForPupilsTree;
        this.nodesForPupilsPanel = nodesForPupilsPanel;
        this.pupilsTreeModel = pupilsTreeModel;
        this.informationLabelForPupilPanel = informationLabelForPupilPanel;
        this.pupilsPanel = pupilsPanel;
    }

//    public GradesTreeNodeMouseListener(JTree tree) {
//        this.tree = tree;
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
//            JOptionPane.showMessageDialog(null, node.getUserObject(), "Test", JOptionPane.INFORMATION_MESSAGE);

            int grade = (Objects.equals(node.toString(), "Zero class")) ? 0 :
                    (Objects.equals(node.toString(), "First class")) ? 1 :
                    (Objects.equals(node.toString(), "Second class")) ? 2 :
                    (Objects.equals(node.toString(), "Third class")) ? 3 :
                    (Objects.equals(node.toString(), "Fourth class")) ? 4 :
                    (Objects.equals(node.toString(), "Fifth class")) ? 5 :
                    (Objects.equals(node.toString(), "Sixth class")) ? 6 :
                    (Objects.equals(node.toString(), "Seventh class")) ? 7 :
                    (Objects.equals(node.toString(), "Eighth class")) ? 8 : -1;

            rootForPupilsTree.removeAllChildren();
            if (grade>-1) {
                list = PupilsDataList.getListOfPupilsOfCertainGrade(grade);
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String nameNode = String.format("%d. %s", i + 1, PupilsDataList.getIdNamesSurname(list.get(i)));
                        nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                        rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                    }
                    informationLabelForPupilPanel.setText(node.toString().concat(":"));
                } else {
                    String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>", node.toString());
                    informationLabelForPupilPanel.setText(textForLabel);
                }
            } else {
                list = PupilsDataList.getListOfAllPupils();
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String nameNode = String.format(PupilsDataList.getGradeIdNamesSurname(list.get(i)));
                        nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                        rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                    }
                    informationLabelForPupilPanel.setText(node.toString().concat(":"));
                } else {
                    String textForLabel = "There are no database";
                    informationLabelForPupilPanel.setText(textForLabel);
                }
            }
            pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
            pupilsPanel.repaint();

//            CentralPanel.showPupilsOfCertainGrade(PupilsDataList.getListOfPupilsOfCertainGrade(grade), node);

        }
    }
}