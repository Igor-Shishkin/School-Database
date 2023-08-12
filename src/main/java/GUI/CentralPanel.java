package GUI;

import GUI.listeners.GradesTreeNodeMouseListener;
import GUI.listeners.HandCursorForMouseMotionAdapter;
import GUI.listeners.PupilsTreeNodeMouseListener;
import GUI.styleStorage.ColorsSets;
import database.Pupil;
import database.PupilsDataList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
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
    JPanel gradesPanel, pupilsPanel, informationPanel, rightPanel;
    Border border;
    GridBagConstraints constraints;
    DefaultMutableTreeNode rootForGradePanel, rootForPupilsTree;
    DefaultMutableTreeNode gradeZero, gradeFirst, gradeSecond, gradeThird, gradeFourth, gradeFifth, gradeSixth,
                 gradeSeventh, gradeEighth;
    ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel;
    ArrayList<Pupil> pupilsChosenGrades;
    JTree treeForGradePanel, treeForPupilsPanel;
    Font font;
    DefaultTreeModel pupilsTreeModel;
    JLabel informationLabelForPupilPanel, pupilInformationLabel;
    Font remRegular;
    JTextField  currentStatusField;
    public CentralPanel(JTextField  currentStatusField) throws IOException, FontFormatException {
        this.currentStatusField = currentStatusField;
        this.setLayout(new BorderLayout());
//        this.setLayout(new GridBagLayout());

        border = BorderFactory.createLoweredBevelBorder();

        Path workDir = Paths.get("src", "main", "resources");
        File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
        remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        font = remRegular.deriveFont(Font.PLAIN, 16);
        nodesForPupilsPanel = new ArrayList<>();




        setComponentsForGradePanel();
        setPupilPanel();
        setComponentsForInformationPanel();


        setListeners();


        setPanels();
        this.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(0));
    }

    private void setListeners() {
        treeForGradePanel.addMouseListener(new GradesTreeNodeMouseListener(treeForGradePanel, rootForPupilsTree,
                nodesForPupilsPanel, pupilsTreeModel, informationLabelForPupilPanel, pupilsPanel));
        treeForPupilsPanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForPupilsPanel));
        treeForPupilsPanel.addMouseListener(new PupilsTreeNodeMouseListener(treeForPupilsPanel, pupilInformationLabel,
                informationPanel));
    }

    private void setComponentsForGradePanel() {
        gradesPanel = new JPanel(new BorderLayout());
        gradesPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        gradesPanel.setBorder(border);
        setTreeNode();
        gradesPanel.setToolTipText("Grades");
//        gradesPanel.add(treeForGradePanel, BorderLayout.NORTH);
    }

    private void setComponentsForInformationPanel() {
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
        treeForGradePanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer());
//        treeForGradePanel.addMouseListener(new GradesTreeNodeMouseListener(treeForGradePanel, rootForPupilsTree,
//                 nodesForPupilsPanel, pupilsTreeModel, informationLabelForPupilPanel, pupilsPanel));
        treeForGradePanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForGradePanel));

//        DefaultTreeCellRenderer renderer =
//                (DefaultTreeCellRenderer) treeForGradePanel.getCellRenderer();
//        renderer.setTextSelectionColor(Color.white);
//        renderer.setBackgroundSelectionColor(Color.blue);
//        renderer.setBorderSelectionColor(Color.red);

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

    private void setPanels() {
        rightPanel = new JPanel(new BorderLayout());

        gradesPanel.setPreferredSize(new Dimension(250,10));
        pupilsPanel.setPreferredSize(new Dimension(350, 10));
        informationPanel.setPreferredSize(new Dimension(385,10));

        this.add(informationPanel, BorderLayout.EAST);
        this.add(gradesPanel, BorderLayout.WEST);
        this.add(pupilsPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }

//    public void showPupilsOfCertainGrade (ArrayList<Pupil> list, DefaultMutableTreeNode node) {
//        rootForPupilsTree.removeAllChildren();
//        if (list.size()!=0) {
//            for (int i = 0; i < list.size(); i++) {
//                String nameNode = String.format("%d. %s", i+1, PupilsDataList.getIdNamesSurname(list.get(i)));
//                nodesForPupilsPanel[i] = new DefaultMutableTreeNode
//                        (nameNode);
//                rootForPupilsTree.add(nodesForPupilsPanel[i]);
//            }
//            informationLabelForPupilPanel.setText(node.toString().concat(":"));
//        } else {
//            String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>", node.toString());
//            informationLabelForPupilPanel.setText(textForLabel);
//        }
//        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
//        pupilsPanel.repaint();
//    }
    public void showPupilInformation (int id) {
        pupilInformationLabel.setText(PupilsDataList.getPupilInformation
                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
        System.out.println(PupilsDataList.getPupilInformation
                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
        pupilInformationLabel.repaint();
//        scrollPaneForInformationLabel.repaint();
        informationPanel.repaint();


    }
}
