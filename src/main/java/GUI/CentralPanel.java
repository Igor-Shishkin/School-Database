package GUI;

import GUI.styleStorage.ColorsSets;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class CentralPanel extends JPanel implements ActionListener, TreeSelectionListener {
    JPanel gradesPanel;
    static JPanel pupilsPanel;
    static JPanel informationPanel;
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
    static JLabel informationLabelForPupilPanel;
    static JLabel pupilInformationLabel;
    Font remRegular;
    static JScrollPane scrollPaneForInformationLabel;
    static JTextField textField;
    public CentralPanel() throws IOException, FontFormatException {
        this.setLayout(new GridBagLayout());
//        this.setLayout(new GridBagLayout());

        border = BorderFactory.createLoweredBevelBorder();
//        font = new Font("MV Boli",Font.BOLD,16);
        Path workDir = Paths.get("src", "main", "resources");
        File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
        remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        font = remRegular.deriveFont(Font.PLAIN, 16);
        nodesForPupilsPanel = new DefaultMutableTreeNode[30];
        rootForPupilsPanel = new DefaultMutableTreeNode();

        gradesPanel = new JPanel(new BorderLayout());
        gradesPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        gradesPanel.setBorder(border);
        setTreeNode();
        gradesPanel.setToolTipText("Grades");
//        gradesPanel.add(treeForGradePanel, BorderLayout.NORTH);


        setPupilPanel();


        pupilInformationLabel = new JLabel();
        pupilInformationLabel.setFont(font);
        pupilInformationLabel.setText("No student selected");
        pupilInformationLabel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
//        pupilInformationLabel.setPreferredSize(new Dimension (10, 200));
        pupilsPanel.setFont(font);
        informationPanel = new JPanel();
//        scrollPaneForInformationLabel = new JScrollPane(pupilInformationLabel);
//        scrollPaneForInformationLabel.setBackground(MainWindow.actualSetColor.get(2));
        informationPanel.setLayout(new BorderLayout());
//        scrollPaneForInformationLabel.setPreferredSize(new Dimension (400, 300));
        informationPanel.add(pupilInformationLabel, BorderLayout.NORTH);
        informationPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        informationPanel.setBorder(border);
//        informationPanel.setMaximumSize(new Dimension(350, 900));

        setGridBagConstraints();
        this.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(0));
    }

    private void setPupilPanel() {
        pupilsPanel = new JPanel(new BorderLayout());
        pupilsPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        pupilsPanel.setBorder(border);

        informationLabelForPupilPanel = new JLabel("List of pupils");
        informationLabelForPupilPanel.setFont(remRegular.deriveFont(Font.BOLD, 18));
        pupilsPanel.add(informationLabelForPupilPanel, BorderLayout.NORTH);

        rootForPupilsTree = new DefaultMutableTreeNode("root");
        pupilsTreeModel = new DefaultTreeModel(rootForPupilsTree);
        treeForPupilsPanel = new JTree(pupilsTreeModel);
        treeForPupilsPanel.setCellRenderer(new CustomTreeCellRenderer());
        treeForPupilsPanel.setFont(font);
        treeForPupilsPanel.setRootVisible(false);
        treeForPupilsPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        treeForPupilsPanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForPupilsPanel));
        treeForPupilsPanel.addMouseListener(new PupilsTreeNodeMouseListener(treeForPupilsPanel));
//        pupilsPanel.add(treeForPupilsPanel);
        JScrollPane scrollPaneForPupilTree = new JScrollPane(treeForPupilsPanel);
        pupilsPanel.add(scrollPaneForPupilTree);
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
        treeForGradePanel.setFont(new Font("MV Boli",Font.BOLD,16));
        treeForGradePanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
//        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer());
        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer());
        treeForGradePanel.addMouseListener(new GradesTreeNodeMouseListener(treeForGradePanel));
        treeForGradePanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForGradePanel));

        JScrollPane paneForGradesTree = new JScrollPane(treeForGradePanel);
        gradesPanel.add(paneForGradesTree);
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

        constraints.weightx = 0.43;
        constraints.weighty = 0.3;
        constraints.insets = new Insets(5,3,5,2);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(gradesPanel, constraints);

        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(pupilsPanel, constraints);

        constraints.weightx = 0.6;
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

    public static void showPupilsOfCertainGrade (ArrayList<Pupil> list, DefaultMutableTreeNode node) {
        rootForPupilsTree.removeAllChildren();
        if (list.size()!=0) {
            for (int i = 0; i < list.size(); i++) {
                String nameNode = String.format("%d. %s", i+1, PupilsDataList.getIdNamesSurname(list.get(i)));
                nodesForPupilsPanel[i] = new DefaultMutableTreeNode
                        (nameNode);
                rootForPupilsTree.add(nodesForPupilsPanel[i]);
            }
            informationLabelForPupilPanel.setText(node.toString().concat(":"));
        } else {
            String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>", node.toString());
            informationLabelForPupilPanel.setText(textForLabel);
        }
        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        pupilsPanel.repaint();
    }
    public static void showPupilInformation (int id) {
        pupilInformationLabel.setText(PupilsDataList.getPupilInformation
                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
        System.out.println(PupilsDataList.getPupilInformation
                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
        pupilInformationLabel.repaint();
//        scrollPaneForInformationLabel.repaint();
        informationPanel.repaint();


    }
}
