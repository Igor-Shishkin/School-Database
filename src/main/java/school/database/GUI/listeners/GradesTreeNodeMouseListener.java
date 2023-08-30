package school.database.GUI.listeners;

import school.database.data.Data;
import school.database.data.objects.Pupil;
import school.database.GUI.CentralPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GradesTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;
    DefaultMutableTreeNode rootForPupilsTree;
    ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel;
    DefaultTreeModel pupilsTreeModel;
    JLabel informationLabelForPupilPanel;
    JPanel pupilsPanel;
    List<Pupil> list;
    JRadioButton getAllPupilsRadioButton;
    JButton showEditAchievementButton, showEditMarksButton, editDateButton, deletePupilButton;
    Data dataList;


    public GradesTreeNodeMouseListener(JTree tree, DefaultMutableTreeNode rootForPupilsTree,
                  ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel, DefaultTreeModel pupilsTreeModel,
                  JLabel informationLabelForPupilPanel, JPanel pupilsPanel, JRadioButton getAllPupilsRadioButton,
                  JButton showEditAchievementButton, JButton showEditMarksButton, JButton editDateButton,
                                       JButton deletePupilButton, Data dataList) {
        this.tree = tree;
        this.rootForPupilsTree = rootForPupilsTree;
        this.nodesForPupilsPanel = nodesForPupilsPanel;
        this.pupilsTreeModel = pupilsTreeModel;
        this.informationLabelForPupilPanel = informationLabelForPupilPanel;
        this.pupilsPanel = pupilsPanel;
        this.getAllPupilsRadioButton = getAllPupilsRadioButton;
        this.showEditAchievementButton = showEditAchievementButton;
        this.showEditMarksButton = showEditMarksButton;
        this.editDateButton = editDateButton;
        this.deletePupilButton = deletePupilButton;
        this.dataList = dataList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getAllPupilsRadioButton.setSelected(true);
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            int grade = (Objects.equals(node.toString(), "Zero class")) ? 0 :
                    (Objects.equals(node.toString(), "First class")) ? 1 :
                    (Objects.equals(node.toString(), "Second class")) ? 2 :
                    (Objects.equals(node.toString(), "Third class")) ? 3 :
                    (Objects.equals(node.toString(), "Fourth class")) ? 4 :
                    (Objects.equals(node.toString(), "Fifth class")) ? 5 :
                    (Objects.equals(node.toString(), "Sixth class")) ? 6 :
                    (Objects.equals(node.toString(), "Seventh class")) ? 7 :
                    (Objects.equals(node.toString(), "Eighth class")) ? 8 : -1;

            CentralPanel.CURRENT_GRADE = grade;

            rootForPupilsTree.removeAllChildren();

            if (grade>-1) {
                list =  dataList.getListOfPupilsOfCertainGrade(grade);
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        String nameNode = String.format("%d. %s", i + 1, dataList.getIdNamesSurname(list.get(i)));
                        nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                        rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                    }
                    informationLabelForPupilPanel.setText(node.toString().concat(":"));
                } else {
                    String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>", node.toString());
                    informationLabelForPupilPanel.setText(textForLabel);
                }
            } else {
                list = dataList.getListOfAllPupils();
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
//                        String nameNode = String.format(PupilsDataList.getGradeIdNamesSurname(list.get(i)));
                        String nameNode = list.get(i).getGradeIdNamesSurname();
                        nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                        rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                    }
                    informationLabelForPupilPanel.setText(node.toString().concat(":"));
                } else {
                    String textForLabel = "There are no database";
                    informationLabelForPupilPanel.setText(textForLabel);
                }
            }

            showEditMarksButton.setVisible(false);
            showEditAchievementButton.setVisible(false);
            editDateButton.setVisible(false);
            deletePupilButton.setVisible(false);


            pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
            pupilsPanel.repaint();
        }
    }
}
