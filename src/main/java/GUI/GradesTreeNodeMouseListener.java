package GUI;

import database.PupilsDataList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

class GradesTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;

    public GradesTreeNodeMouseListener(JTree tree) {
        this.tree = tree;
    }

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
                    (Objects.equals(node.toString(), "Eighth class")) ? 8 : 9;
            System.out.println(grade);

            CentralPanel.showPupilsOfCertainGrade(PupilsDataList.getListOfPupilsOfCertainGrade(grade), node);

        }
    }
}
