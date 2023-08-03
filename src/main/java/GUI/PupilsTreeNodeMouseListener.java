package GUI;

import database.PupilsDataList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PupilsTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;


    public PupilsTreeNodeMouseListener(JTree tree) {
        this.tree = tree;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
//            JOptionPane.showMessageDialog(null, node.getUserObject(), "Test", JOptionPane.INFORMATION_MESSAGE);
            String nodeName = node.toString();
            int id = 0;
            for (int i = nodeName.length()-1, k=0; i > 0; i--,k++) {
                if (Character.isDigit(nodeName.charAt(i))) {
                    id += Character.digit(nodeName.charAt(i),10)*Math.pow(10,k);
                } else{
                    break;
                }
            }
            System.out.println(id);
            CentralPanel.showPupilInformation(id);

//            CentralPanel.showPupilsOfCertainGrade(PupilsDataList.getListOfPupilsOfCertainGrade(grade), node);

        }
    }
}

