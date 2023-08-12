package GUI;

import GUI.listeners.GradesTreeNodeMouseListener;
import GUI.listeners.HandCursorForMouseMotionAdapter;
import GUI.listeners.PupilsTreeNodeMouseListener;
import GUI.styleStorage.ColorsSets;
import GUI.styleStorage.ConstantsOfStyle;
import database.Pupil;
import database.PupilsDataList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
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
    JPanel gradesPanel, pupilsPanel, informationPanel, panelForFilterRadioButtons;
    Border border;
    DefaultMutableTreeNode rootForGradePanel, rootForPupilsTree;
    DefaultMutableTreeNode gradeZero, gradeFirst, gradeSecond, gradeThird, gradeFourth, gradeFifth, gradeSixth,
                 gradeSeventh, gradeEighth;
    ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel;
    JTree treeForGradePanel, treeForPupilsPanel;
    Font font;
    DefaultTreeModel pupilsTreeModel;
    JLabel informationLabelForPupilPanel, pupilInformationLabel;
    Font remRegular;
    JTextField  currentStatusField;
    JRadioButton getAllPupilsRadioButton, getPupilsWithBirthdayInThisMonthRadioButton, getPupilsWithAwardBarRadioButton,
            getNoPromotedPupilsAwardBarRadioButton, getPupilsWithAchievement;

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
        gradesPanel = new JPanel(new GridBagLayout());
        gradesPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        gradesPanel.setBorder(border);
        setTreeNode();
        gradesPanel.setToolTipText("Grades");
    }

    private void setComponentsForInformationPanel() {
        pupilInformationLabel = new JLabel();
        pupilInformationLabel.setFont(font);
        pupilInformationLabel.setText("No student selected");
        pupilInformationLabel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        pupilsPanel.setFont(font);

        informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
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
        treeForGradePanel.setFont(new Font("MV Boli",Font.BOLD,20));
        treeForGradePanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        treeForGradePanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer());

        treeForGradePanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForGradePanel));
//        JScrollPane paneForGradesTree = new JScrollPane(treeForGradePanel);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;






        panelForFilterRadioButtons = new JPanel(new GridLayout(5,1,4,4));
        panelForFilterRadioButtons.setBorder(BorderFactory.createTitledBorder("Filters"));

        setComponentsForFilterPanel();




        setStyleForComponentsFilterPane
                (panelForFilterRadioButtons, ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 15));
        panelForFilterRadioButtons.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        gradesPanel.add(treeForGradePanel, c);

        c.insets = new Insets(15,3,20,3);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        gradesPanel.add(new JSeparator(), c);

        c.insets = new Insets(2,5,2,3);
        c.gridx = 0;
        c.gridy = 4;
        gradesPanel.add(panelForFilterRadioButtons, c);


    }

    private void setComponentsForFilterPanel() {
        getAllPupilsRadioButton = new JRadioButton("All pupils");
        getPupilsWithBirthdayInThisMonthRadioButton = new JRadioButton("With birthday in this month");
        getPupilsWithAwardBarRadioButton = new JRadioButton("With award bar");
        getNoPromotedPupilsAwardBarRadioButton = new JRadioButton("Not promoted");
        getPupilsWithAchievement = new JRadioButton("With achievement");

        ButtonGroup radioFilterGroup = new ButtonGroup();
        radioFilterGroup.add(getAllPupilsRadioButton);
        radioFilterGroup.add(getPupilsWithBirthdayInThisMonthRadioButton);
        radioFilterGroup.add(getPupilsWithAwardBarRadioButton);
        radioFilterGroup.add(getNoPromotedPupilsAwardBarRadioButton);
        radioFilterGroup.add(getPupilsWithAchievement);

        panelForFilterRadioButtons.add(getAllPupilsRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithBirthdayInThisMonthRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithAwardBarRadioButton);
        panelForFilterRadioButtons.add(getNoPromotedPupilsAwardBarRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithAchievement);

        getAllPupilsRadioButton.addActionListener(this);
        getPupilsWithBirthdayInThisMonthRadioButton.addActionListener(this);
        getPupilsWithAwardBarRadioButton.addActionListener(this);
        getNoPromotedPupilsAwardBarRadioButton.addActionListener(this);
        getPupilsWithAchievement.addActionListener(this);
    }


    private void setPanels() {
        gradesPanel.setPreferredSize(new Dimension(250,10));
        pupilsPanel.setPreferredSize(new Dimension(350, 10));
        informationPanel.setPreferredSize(new Dimension(385,10));

        this.add(informationPanel, BorderLayout.EAST);
        this.add(gradesPanel, BorderLayout.WEST);
        this.add(pupilsPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PupilsDataList.getPupilsDataList()!=null) {
            if (e.getSource() == getAllPupilsRadioButton) {
                int grade = getSelectedGrade(treeForGradePanel);
                ArrayList<Pupil> list;
                if (grade<0) {
                    list = PupilsDataList.getListOfAllPupils();
                } else {
                    list = PupilsDataList.getListOfPupilsOfCertainGrade(grade);
                }
//                TreePath path = treeForGradePanel.getSelectionPath();
//                assert path != null;
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                buildPupilsTree(list);
            }
            if (e.getSource()==getPupilsWithBirthdayInThisMonthRadioButton) {
                int grade = getSelectedGrade(treeForGradePanel);
                ArrayList<Pupil> list = PupilsDataList.getPupilsWithBirthdayInThisMonth(grade);

//                TreePath path = treeForGradePanel.getSelectionPath();
//                assert path != null;
//                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                buildPupilsTree(list);
            }
        }

    }
    public void buildPupilsTree(ArrayList<Pupil> list) {
        rootForPupilsTree.removeAllChildren();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                String nameNode = String.format("%d. %s", i + 1, PupilsDataList.getIdNamesSurname(list.get(i)));
                nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                rootForPupilsTree.add(nodesForPupilsPanel.get(i));
            }
//            informationLabelForPupilPanel.setText(node.toString().concat(":"));
        } else {
//            String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>", node.toString());
//            informationLabelForPupilPanel.setText(textForLabel);
        }
        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        pupilsPanel.repaint();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }

    public void showPupilInformation (int id) {
        pupilInformationLabel.setText(PupilsDataList.getPupilInformation
                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
        System.out.println(PupilsDataList.getPupilInformation
                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
        pupilInformationLabel.repaint();
//        scrollPaneForInformationLabel.repaint();
        informationPanel.repaint();


    }

    private void setStyleForComponentsFilterPane(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JRadioButton) {
                component.setFont(font);
                component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
            }
        }
    }

    private int getSelectedGrade(JTree tree) {
        TreePath path = tree.getSelectionPath();
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            return (Objects.equals(node.toString(), "Zero class")) ? 0 :
                    (Objects.equals(node.toString(), "First class")) ? 1 :
                            (Objects.equals(node.toString(), "Second class")) ? 2 :
                                    (Objects.equals(node.toString(), "Third class")) ? 3 :
                                            (Objects.equals(node.toString(), "Fourth class")) ? 4 :
                                                    (Objects.equals(node.toString(), "Fifth class")) ? 5 :
                                                            (Objects.equals(node.toString(), "Sixth class")) ? 6 :
                                                                    (Objects.equals(node.toString(), "Seventh class")) ? 7 :
                                                                            (Objects.equals(node.toString(), "Eighth class")) ? 8 : -1;
        }
        return -1;
    }
}
