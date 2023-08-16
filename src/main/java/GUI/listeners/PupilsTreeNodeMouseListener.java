package GUI.listeners;

import GUI.CentralPanel;
import database.PupilsDataList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PupilsTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;
    JLabel pupilInformationLabel;
    JPanel informationPanel;
    JButton showEditAchievementButton, showEditMarksButton, editDateButton, deletePupilButton;

    public PupilsTreeNodeMouseListener(JTree tree, JLabel pupilInformationLabel, JPanel informationPanel,
                     JButton showEditAchievementButton, JButton showEditMarksButton, JButton editDateButton,
                                       JButton deletePupilButton) {
        this.tree = tree;
        this.pupilInformationLabel = pupilInformationLabel;
        this.informationPanel = informationPanel;
        this.showEditAchievementButton = showEditAchievementButton;
        this.showEditMarksButton = showEditMarksButton;
        this.editDateButton = editDateButton;
        this.deletePupilButton = deletePupilButton;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            String nodeName = node.toString();
            int id = 0;
            for (int i = nodeName.length()-1, k=0; i > 0; i--,k++) {
                if (Character.isDigit(nodeName.charAt(i))) {
                    id += Character.digit(nodeName.charAt(i),10)*Math.pow(10,k);
                } else{
                    break;
                }
            }
            CentralPanel.CURRENT_PUPIL = PupilsDataList.getPupilWithCertainID(id);
            CentralPanel.CURRENT_ID = id;

            assert CentralPanel.CURRENT_PUPIL != null;
            pupilInformationLabel.setText(CentralPanel.CURRENT_PUPIL.getPupilInformation());

            showEditMarksButton.setVisible(true);
            showEditMarksButton.setEnabled(CentralPanel.CURRENT_GRADE>3);
            showEditAchievementButton.setVisible(true);
            editDateButton.setVisible(true);
            deletePupilButton.setVisible(true);

            CentralPanel.CURRENT_ID = id;

            pupilInformationLabel.repaint();
            informationPanel.repaint();
        }
    }
}

