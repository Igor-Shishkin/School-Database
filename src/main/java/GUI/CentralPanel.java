package GUI;

import GUI.addEditPupil.AddEditAchievement;
import GUI.addEditPupil.AddEditMarks;
import GUI.addEditPupil.AddEditPupil;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CentralPanel extends JPanel implements ActionListener, TreeSelectionListener {
    public static int CURRENT_ID, CURRENT_GRADE;
    public static Pupil CURRENT_PUPIL;
    private JPanel gradesPanel, pupilsPanel, informationPanel, panelForFilterRadioButtons, basisForGradesPanel,
            basisForInformationPanel;
    private final Border border;
    private final DefaultMutableTreeNode rootForGradePanel;
    private DefaultMutableTreeNode rootForPupilsTree;
    private DefaultMutableTreeNode currentNode;
    private final ArrayList<DefaultMutableTreeNode> nodesForPupilsPanel;
    private final JTree treeForGradePanel;
    private JTree treeForPupilsPanel;
    private DefaultTreeModel pupilsTreeModel;
    private final DefaultTreeModel gradesTreeModel;
    private JLabel informationLabelForPupilPanel;
    private JLabel pupilInformationLabel;
    private JLabel gradesCapitalLabel;
    private Font remRegular;
    private final JTextField currentStatusField;
    private JRadioButton getAllPupilsRadioButton, getPupilsWithBirthdayInThisMonthRadioButton, getPupilsWithAwardBarRadioButton,
            getNoPromotedPupilsRadioButton, getPupilsWithAchievement;
    private JButton showEditMarksButton;
    private JButton showEditAchievementButton;
    private JButton editDateButton;
    private final JButton addPupilButton;
    private JButton deletePupilButton;
    private final JFrame parentFrame;
    private final JScrollPane paneForGradesTree;
    private final PupilsDataList dataList;
//    private String id;
//    HashMap<String,User> loginInfo;

    public CentralPanel(JFrame parentFrame, JTextField currentStatusField, JTree treeForGradePanel,
                        DefaultMutableTreeNode rootForGradePanel, DefaultTreeModel gradesTreeModel,
                        JPanel panelForFilterRadioButtons, JButton addPupilButton,
                        JScrollPane paneForGradesTree, PupilsDataList dataList) throws IOException, FontFormatException {
        this.currentStatusField = currentStatusField;
        this.parentFrame = parentFrame;
        this.treeForGradePanel = treeForGradePanel;
        this.rootForGradePanel = rootForGradePanel;
        this.gradesTreeModel = gradesTreeModel;
        this.panelForFilterRadioButtons = panelForFilterRadioButtons;
        this.addPupilButton = addPupilButton;
        this.paneForGradesTree = paneForGradesTree;
        this.dataList = dataList;
//        this.id = id;
//        this.loginInfo = loginInfo;

        this.setLayout(new BorderLayout());
//        this.setLayout(new GridBagLayout());

        border = BorderFactory.createLoweredBevelBorder();

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
                nodesForPupilsPanel, pupilsTreeModel, informationLabelForPupilPanel, pupilsPanel,
                getAllPupilsRadioButton, showEditAchievementButton, showEditMarksButton, editDateButton,
                deletePupilButton, dataList));
        treeForPupilsPanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForPupilsPanel));
        treeForPupilsPanel.addMouseListener(new PupilsTreeNodeMouseListener(treeForPupilsPanel, pupilInformationLabel,
                informationPanel, showEditAchievementButton, showEditMarksButton, editDateButton, deletePupilButton,
                dataList));
    }

    private void setComponentsForGradePanel() {
        gradesPanel = new JPanel(new GridBagLayout());
        gradesPanel.setPreferredSize(new Dimension(10, 568));
        gradesPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        gradesPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        setComponentsToGradePanel();
        gradesPanel.setToolTipText("Grades");
        treeForGradePanel.setVisible(false);

        basisForGradesPanel = new JPanel(new BorderLayout(5, 5));
        basisForGradesPanel.setBorder(border);
        basisForGradesPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        basisForGradesPanel.add(gradesCapitalLabel, BorderLayout.NORTH);
        basisForGradesPanel.add(gradesPanel, BorderLayout.SOUTH);
    }

    private void setComponentsForInformationPanel() {
        pupilInformationLabel = new JLabel();
        pupilInformationLabel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        pupilInformationLabel.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 19));

        showEditMarksButton = new JButton("MARKS");
        showEditMarksButton.addActionListener(this);
        showEditMarksButton.setPreferredSize(new Dimension(10, 21));
        showEditMarksButton.setBorder(BorderFactory.createLineBorder(ColorsSets.ACTUAL_SET_OF_COLORS.get(2), 3));
        showEditMarksButton.setVisible(false);

        showEditAchievementButton = new JButton("ACHIEVEMENT");
        showEditAchievementButton.addActionListener(this);
        showEditAchievementButton.setPreferredSize(new Dimension(10, 21));
        showEditAchievementButton.setBorder(BorderFactory.createLineBorder
                (ColorsSets.ACTUAL_SET_OF_COLORS.get(2), 3));
        showEditAchievementButton.setVisible(false);

        editDateButton = new JButton("DATE");
        editDateButton.addActionListener(this);
        editDateButton.setPreferredSize(new Dimension(10, 21));
        editDateButton.setBorder(BorderFactory.createLineBorder(ColorsSets.ACTUAL_SET_OF_COLORS.get(2), 3));
        editDateButton.setVisible(false);

        deletePupilButton = new JButton("DELETE PUPIL");
        deletePupilButton.addActionListener(this);
        deletePupilButton.setPreferredSize(new Dimension(10, 21));
        deletePupilButton.setBorder(BorderFactory.createLineBorder(ColorsSets.ACTUAL_SET_OF_COLORS.get(2), 3));
        deletePupilButton.setBackground(Color.darkGray);
        deletePupilButton.setForeground(Color.red);
        deletePupilButton.setVisible(false);


        JPanel buttonInformationPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        buttonInformationPanel.setPreferredSize(new Dimension(10,150));
        buttonInformationPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        buttonInformationPanel.add(editDateButton);
        buttonInformationPanel.add(showEditAchievementButton);
        buttonInformationPanel.add(showEditMarksButton);
        buttonInformationPanel.add(deletePupilButton);

        informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
        informationPanel.add(pupilInformationLabel, BorderLayout.NORTH);
        informationPanel.add(buttonInformationPanel, BorderLayout.SOUTH);
        informationPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        informationPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        informationPanel.setPreferredSize(new Dimension(10, 568));

        JLabel informationCapitalLabel = new JLabel("Pupil");
        informationCapitalLabel.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.BOLD, 19));


        basisForInformationPanel = new JPanel(new BorderLayout(5, 5));
        basisForInformationPanel.setBorder(border);
        basisForInformationPanel.add(informationCapitalLabel, BorderLayout.NORTH);
        basisForInformationPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        basisForInformationPanel.add(informationPanel, BorderLayout.SOUTH);


    }

    private void setPupilPanel() {
        pupilsPanel = new JPanel(new BorderLayout());
        pupilsPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        pupilsPanel.setBorder(border);

        informationLabelForPupilPanel = new JLabel("List of pupils");
        informationLabelForPupilPanel.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.BOLD, 18));
        pupilsPanel.add(informationLabelForPupilPanel, BorderLayout.NORTH);

        rootForPupilsTree = new DefaultMutableTreeNode("root");
        pupilsTreeModel = new DefaultTreeModel(rootForPupilsTree);
        treeForPupilsPanel = new JTree(pupilsTreeModel);
        treeForPupilsPanel.setCellRenderer(new CustomTreeCellRenderer());
        treeForPupilsPanel.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 17));
        treeForPupilsPanel.setRootVisible(false);
        treeForPupilsPanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));

        JScrollPane scrollPaneForPupilTree = new JScrollPane(treeForPupilsPanel);
        scrollPaneForPupilTree.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPaneForPupilTree.setPreferredSize(new Dimension(10, 568));
        pupilsPanel.add(scrollPaneForPupilTree);
    }

    private void setComponentsToGradePanel() {
//        rootForGradePanel = new DefaultMutableTreeNode("School");

        DefaultMutableTreeNode gradeZero = new DefaultMutableTreeNode("Zero class");
        DefaultMutableTreeNode gradeFirst = new DefaultMutableTreeNode("First class");
        DefaultMutableTreeNode gradeSecond = new DefaultMutableTreeNode("Second class");
        DefaultMutableTreeNode gradeThird = new DefaultMutableTreeNode("Third class");
        DefaultMutableTreeNode gradeFourth = new DefaultMutableTreeNode("Fourth class");
        DefaultMutableTreeNode gradeFifth = new DefaultMutableTreeNode("Fifth class");
        DefaultMutableTreeNode gradeSixth = new DefaultMutableTreeNode("Sixth class");
        DefaultMutableTreeNode gradeSeventh = new DefaultMutableTreeNode("Seventh class");
        DefaultMutableTreeNode gradeEighth = new DefaultMutableTreeNode("Eighth class");

        rootForGradePanel.add(gradeZero);
        rootForGradePanel.add(gradeFirst);
        rootForGradePanel.add(gradeSecond);
        rootForGradePanel.add(gradeThird);
        rootForGradePanel.add(gradeFourth);
        rootForGradePanel.add(gradeFifth);
        rootForGradePanel.add(gradeSixth);
        rootForGradePanel.add(gradeSeventh);
        rootForGradePanel.add(gradeEighth);

        gradesTreeModel.nodeStructureChanged(rootForGradePanel);

        treeForGradePanel.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeForGradePanel.addTreeSelectionListener(this);

//        treeForGradePanel.putClientProperty("JTree.lineStyle", "Angled");
        treeForGradePanel.setFont(new Font("MV Boli", Font.BOLD, 19));
        treeForGradePanel.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer());

        treeForGradePanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForGradePanel));

        paneForGradesTree.setPreferredSize(new Dimension(230,330));
        paneForGradesTree.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

//        panelForFilterRadioButtons = new JPanel(new GridLayout(5, 1, 4, 4));
        panelForFilterRadioButtons.setBorder(BorderFactory.createTitledBorder("Filters"));
        panelForFilterRadioButtons.setVisible(false);

        gradesCapitalLabel = new JLabel("Grades");
        gradesCapitalLabel.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.BOLD, 19));

        setComponentsForFilterPanel();
        setStyleForComponentsFilterPane
                (panelForFilterRadioButtons, ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 15));
        panelForFilterRadioButtons.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));

        addPupilButton.setPreferredSize(new Dimension(10,25));
        addPupilButton.setBackground(Color.DARK_GRAY);
        addPupilButton.setForeground(Color.GREEN);
        addPupilButton.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.BOLD, 18));
        addPupilButton.addActionListener(this);


        c.insets = new Insets(2, 5, 2, 3);
        c.gridy = 0;
        c.gridx = 0;
        c.gridheight = 3;
        gradesPanel.add(paneForGradesTree, c);

        c.insets = new Insets(5, 3, 5, 3);
        c.gridy = 3;
        c.gridx = 0;
        c.gridheight = 1;
        gradesPanel.add(new JSeparator(), c);

        c.insets = new Insets(2, 5, 2, 3);
        c.gridy = 4;
        c.gridx = 0;
        gradesPanel.add(panelForFilterRadioButtons, c);

        c.gridy = 5;
        c.gridx = 0;
        gradesPanel.add(addPupilButton, c);

    }

    private void setComponentsForFilterPanel() {
        getAllPupilsRadioButton = new JRadioButton("All pupils");
        getPupilsWithBirthdayInThisMonthRadioButton = new JRadioButton("With birthday in this month");
        getPupilsWithAwardBarRadioButton = new JRadioButton("With award bar");
        getNoPromotedPupilsRadioButton = new JRadioButton("Not promoted");
        getPupilsWithAchievement = new JRadioButton("With achievement");

        ButtonGroup radioFilterGroup = new ButtonGroup();
        radioFilterGroup.add(getAllPupilsRadioButton);
        radioFilterGroup.add(getPupilsWithBirthdayInThisMonthRadioButton);
        radioFilterGroup.add(getPupilsWithAwardBarRadioButton);
        radioFilterGroup.add(getNoPromotedPupilsRadioButton);
        radioFilterGroup.add(getPupilsWithAchievement);

        panelForFilterRadioButtons.add(getAllPupilsRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithBirthdayInThisMonthRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithAwardBarRadioButton);
        panelForFilterRadioButtons.add(getNoPromotedPupilsRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithAchievement);

        getAllPupilsRadioButton.addActionListener(this);
        getPupilsWithBirthdayInThisMonthRadioButton.addActionListener(this);
        getPupilsWithAwardBarRadioButton.addActionListener(this);
        getNoPromotedPupilsRadioButton.addActionListener(this);
        getPupilsWithAchievement.addActionListener(this);
    }


    private void setPanels() {

        pupilsPanel.setPreferredSize(new Dimension(350, 10));
        basisForInformationPanel.setPreferredSize(new Dimension(385, 10));
        basisForGradesPanel.setPreferredSize(new Dimension(250, 10));

        this.add(basisForInformationPanel, BorderLayout.EAST);
        this.add(basisForGradesPanel, BorderLayout.WEST);
        this.add(pupilsPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (dataList.getPupilsDataList() != null) {
            if (e.getSource() == getAllPupilsRadioButton) {
                TreePath path = treeForGradePanel.getSelectionPath();
                if (path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    CURRENT_GRADE = getSelectedGrade(node);
                    ArrayList<Pupil> list;
                    list = (CURRENT_GRADE < 0)
                            ? dataList.getListOfAllPupils()
                            : dataList.getListOfPupilsOfCertainGrade(CURRENT_GRADE);
                    buildPupilsTree(list, node);
                }
            }
            if (e.getSource() == getPupilsWithBirthdayInThisMonthRadioButton) {
                TreePath path = treeForGradePanel.getSelectionPath();
                if (path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    int grade = getSelectedGrade(node);
                    ArrayList<Pupil> list = dataList.getPupilsWithBirthdayInThisMonth(grade);
                    rootForPupilsTree.removeAllChildren();
                    if (list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            String nameNode = String.format("%tD. %s", list.get(i).getDateOfBirth(),
                                    dataList.getIdNamesSurname(list.get(i)));
                            nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                            rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                        }

                        informationLabelForPupilPanel.setText(node.toString().concat(":"));
                    } else {
                        String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>",
                                node.toString());
                        informationLabelForPupilPanel.setText(textForLabel);
                    }
                    pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
                    pupilsPanel.repaint();

                }
            }
            if (e.getSource() == getNoPromotedPupilsRadioButton) {
                TreePath path = treeForGradePanel.getSelectionPath();
                if (path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    int grade = getSelectedGrade(node);
                    ArrayList<Pupil> list;
                    list = dataList.getNoPromotedPupilsList(grade);
                    buildPupilsTree(list, node);
                }
            }
            if (e.getSource() == getPupilsWithAchievement) {
                TreePath path = treeForGradePanel.getSelectionPath();
                if (path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    int grade = getSelectedGrade(node);
                    ArrayList<Pupil> list;
                    list = dataList.getPupilsWithAchievementList(grade);
                    buildPupilsTree(list, node);
                }
            }
            if (e.getSource() == getPupilsWithAwardBarRadioButton) {
                TreePath path = treeForGradePanel.getSelectionPath();
                if (path != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    int grade = getSelectedGrade(node);
                    ArrayList<Pupil> list;
                    list = dataList.getPupilsWithAwardBarList(grade);
                    buildPupilsTree(list, node);
                }
            }
            if (e.getSource() == showEditAchievementButton) {
//                TreePath path = treeForPupilsPanel.getSelectionPath();
//                if (path != null) {
//                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
//                    String nodeName = node.toString();
//                    int id = 0;
//                    for (int i = nodeName.length() - 1, k = 0; i > 0; i--, k++) {
//                        if (Character.isDigit(nodeName.charAt(i))) {
//                            id += Character.digit(nodeName.charAt(i), 10) * Math.pow(10, k);
//                        } else {
//                            break;
//                        }
//                    }
                AddEditAchievement addEditAchievement = new AddEditAchievement(parentFrame,
                        CURRENT_PUPIL.getAchievement(), currentStatusField);
                CURRENT_PUPIL.setAchievement(addEditAchievement.showDialogAndGetInput());

//                    AchievementDialog achievementDialog =  new AchievementDialog(parentFrame,
//                            Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id)).getAchievement());

            }

            if (e.getSource() == editDateButton) {

                Pupil chosenPupil = CURRENT_PUPIL;
                try {
                    new AddEditPupil(parentFrame, chosenPupil, currentStatusField, false, dataList);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
                assert chosenPupil != null;
                pupilInformationLabel.setText(chosenPupil.getPupilInformation());
                refreshPupilsTree();
            }
        }
        if (e.getSource() == deletePupilButton) {
            String[] responses = {"Yes, I do", "No, I've changed my mind"};
            if (JOptionPane.showOptionDialog(parentFrame, "Do you want to delete this pupil?", "ARE YOU SURE?",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    responses, responses[0]) == 0) {
                dataList.removePupil(CURRENT_PUPIL);
                showEditMarksButton.setVisible(false);
                showEditAchievementButton.setVisible(false);
                editDateButton.setVisible(false);
                deletePupilButton.setVisible(false);
                if (getAllPupilsRadioButton.isSelected()) {
                    buildPupilsTree(dataList.getListOfPupilsOfCertainGrade(CURRENT_GRADE), null);
                } else if (getPupilsWithAchievement.isSelected()) {
                    buildPupilsTree(dataList.getPupilsWithAchievementList(CURRENT_GRADE), null);
                } else if (getNoPromotedPupilsRadioButton.isSelected()) {
                    buildPupilsTree(dataList.getNoPromotedPupilsList(CURRENT_GRADE), null);
                } else if (getPupilsWithBirthdayInThisMonthRadioButton.isSelected()) {
                    buildPupilsTree(dataList.getPupilsWithBirthdayInThisMonth(CURRENT_GRADE), null);
                } else {
                    buildPupilsTree(dataList.getPupilsWithAwardBarList(CURRENT_GRADE), null);
                }
                pupilInformationLabel.setText("Pupil is removed");
                currentStatusField.setText("Pupil is removed");
            }
        }
        if (e.getSource() == showEditMarksButton) {
            AddEditMarks addEditMarks = null;
            Pupil chosenPupil = dataList.getPupilWithCertainID(CURRENT_ID);
            try {
                assert chosenPupil != null;
                addEditMarks = new AddEditMarks(parentFrame, chosenPupil.getMarks(), chosenPupil.isAwardBar(),
                        chosenPupil.isPromotionToNextGrade(), chosenPupil.getGrade(), currentStatusField, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            chosenPupil.setMarks(addEditMarks.showDialogAndGetInput());
            chosenPupil.setPromotionToNextGrade(chosenPupil.getMarks().getPromotion(chosenPupil.getGrade()));
            chosenPupil.setAwardBar(chosenPupil.getMarks().isAwardBar(chosenPupil.isPromotionToNextGrade(),
                    chosenPupil.getGrade()));

        }
        if (e.getSource()==addPupilButton) {
            try {
                Pupil tempPupil = new Pupil();
                new AddEditPupil(parentFrame, tempPupil, currentStatusField, true, dataList);
                if (tempPupil.getSurname()!=null) {
                    dataList.addPupilToList(tempPupil);
                    CURRENT_PUPIL = tempPupil;
                    CURRENT_GRADE = tempPupil.getGrade();
                    CURRENT_ID = tempPupil.getId();
                    getAllPupilsRadioButton.setSelected(true);
                    currentStatusField.setText(String.format("Pupil '%s' if added", CURRENT_PUPIL.getNamesAndSurname()));
                    pupilInformationLabel.setText(CURRENT_PUPIL.getPupilInformation());
                    refreshPupilsTree();
                    TreePath path = treeForGradePanel.getSelectionPath();
                    assert path != null;
                    DefaultMutableTreeNode gradeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                    informationLabelForPupilPanel.setText(gradeNode.toString());
                }

            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    private void refreshPupilsTree() {
//        int[] selectionRows = treeForPupilsPanel.getSelectionRows();
        rootForPupilsTree.removeAllChildren();
        ArrayList<Pupil> list = new ArrayList<>();

        if (getAllPupilsRadioButton.isSelected()) {
            list = dataList.getListOfPupilsOfCertainGrade(CURRENT_GRADE);
        } else if (getPupilsWithAchievement.isSelected()) {
            list = dataList.getPupilsWithAchievementList(CURRENT_GRADE);
        } else if (getNoPromotedPupilsRadioButton.isSelected()) {
            list = dataList.getNoPromotedPupilsList(CURRENT_GRADE);
        } else if (getPupilsWithBirthdayInThisMonthRadioButton.isSelected()) {
            list = dataList.getPupilsWithBirthdayInThisMonth(CURRENT_GRADE);
        } else {
            list = dataList.getPupilsWithAwardBarList(CURRENT_GRADE);
        }

        int pupilNumberInTheList = 0;

        for (int i = 0; i < list.size(); i++) {
            String nameNode = String.format("%d. %s", i + 1, dataList.getIdNamesSurname(list.get(i)));
            nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
            rootForPupilsTree.add(nodesForPupilsPanel.get(i));

            if (list.get(i).equals(CURRENT_PUPIL)) { pupilNumberInTheList = i; }
        }


        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
//        assert selectionRows !=null;
        treeForPupilsPanel.setSelectionRow(pupilNumberInTheList);
        treeForGradePanel.setSelectionRow(CURRENT_GRADE+1);
        pupilsPanel.repaint();
}

    public void buildPupilsTree(ArrayList<Pupil> list, DefaultMutableTreeNode node) {
        rootForPupilsTree.removeAllChildren();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                String nameNode = (CURRENT_GRADE > -1)
                        ? String.format("%d. %s", i + 1, dataList.getIdNamesSurname(list.get(i)))

                        : dataList.getGradeIdNamesSurname(list.get(i));
                nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                rootForPupilsTree.add(nodesForPupilsPanel.get(i));
            }

//            informationLabelForPupilPanel.setText(node.toString().concat(":"));
        } else {
            if (node != null) {
                String textForLabel = String.format("<html>%s<br>there are no pupils in this class</html>",
                        node.toString());
                informationLabelForPupilPanel.setText(textForLabel);
            }
        }
        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        pupilsPanel.repaint();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }

    public void showPupilInformation(int id) {
        pupilInformationLabel.setText(dataList.getPupilInformation
                (Objects.requireNonNull(dataList.getPupilWithCertainID(id))));
//        System.out.println(PupilsDataList.getPupilInformation
//                (Objects.requireNonNull(PupilsDataList.getPupilWithCertainID(id))));
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

    private int getSelectedGrade(DefaultMutableTreeNode node) {

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

    public void setGradeForTreeVisible() {
        treeForGradePanel.setVisible(!treeForGradePanel.isVisible());
    }



}
