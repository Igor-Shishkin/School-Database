package school.database.gui.listeners;

import school.database.gui.ActualElements;
import school.database.data.Data;
import school.database.data.objects.Permissions;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PupilsTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;
    private final JLabel pupilInformationLabel;
    private final JPanel informationPanel;
    private final JButton showEditAchievementButton, showEditMarksButton, editDateButton, deletePupilButton;
    private final Data dataList;
    private final ActualElements actualElements;

    public PupilsTreeNodeMouseListener(JTree tree, JLabel pupilInformationLabel, JPanel informationPanel,
                     JButton showEditAchievementButton, JButton showEditMarksButton, JButton editDateButton,
                                       JButton deletePupilButton, Data dataList, ActualElements actualElements) {
        this.tree = tree;
        this.pupilInformationLabel = pupilInformationLabel;
        this.informationPanel = informationPanel;
        this.showEditAchievementButton = showEditAchievementButton;
        this.showEditMarksButton = showEditMarksButton;
        this.editDateButton = editDateButton;
        this.deletePupilButton = deletePupilButton;
        this.dataList = dataList;
        this.actualElements = actualElements;
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
            actualElements.setCurrentPupil(dataList.getPupilWithCertainID(id));
            actualElements.setCurrentID(id);

            assert actualElements.getCurrentPupil() != null;
            pupilInformationLabel.setText(actualElements.getCurrentPupil().getPupilInformation());


            if (actualElements.getCurrentGrade() == actualElements.getActualPermissions().getNumberPermission() ||
                    actualElements.getActualPermissions() == Permissions.DIRECTOR) {
                showEditMarksButton.setVisible(true);
                showEditMarksButton.setEnabled(actualElements.getCurrentPupil().getGrade() > 3);
                showEditAchievementButton.setVisible(true);
                editDateButton.setVisible(true);
                deletePupilButton.setVisible(actualElements.getActualPermissions() == Permissions.DIRECTOR);
            } else {
                showEditMarksButton.setVisible(false);
                showEditAchievementButton.setVisible(false);
                editDateButton.setVisible(false);
                deletePupilButton.setVisible(false);
            }

            actualElements.setCurrentID(id);

            pupilInformationLabel.repaint();
            informationPanel.repaint();
        }
    }

}

