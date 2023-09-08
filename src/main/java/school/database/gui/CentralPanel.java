package school.database.gui;

import school.database.gui.add_edit_windows.AddEditAchievement;
import school.database.gui.add_edit_windows.AddEditMarks;
import school.database.gui.add_edit_windows.AddEditPupil;
import school.database.gui.listeners.GradesTreeNodeMouseListener;
import school.database.gui.listeners.HandCursorForMouseMotionAdapter;
import school.database.gui.styleStorage.ConstantsOfStyle;
import school.database.data.Data;
import school.database.data.objects.Pupil;
import school.database.gui.listeners.PupilsTreeNodeMouseListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CentralPanel extends JPanel implements ActionListener{
    private JPanel gradesPanel, pupilsPanel, informationPanel, basisForGradesPanel,
            basisForInformationPanel;
    private final JPanel panelForFilterRadioButtons;
    private final DefaultMutableTreeNode rootForGradePanel, rootForPupilsTree;
    private final List<DefaultMutableTreeNode> nodesForPupilsPanel;
    private final JTree treeForGradePanel, treeForPupilsPanel;
    private final DefaultTreeModel pupilsTreeModel, gradesTreeModel;
    private JLabel informationLabelForPupilPanel;
    private JLabel pupilInformationLabel;
    private JLabel gradesCapitalLabel;
    private final JLabel awardBarLabel;
    private final JTextField currentStatusField;
    private JRadioButton getAllPupilsRadioButton, getPupilsWithBirthdayInThisMonthRadioButton, getPupilsWithAwardBarRadioButton,
            getNoPromotedPupilsRadioButton, getPupilsWithAchievementRadioButton;
    private JButton showEditMarksButton;
    private JButton showEditAchievementButton;
    private JButton editDataButton;
    private final JButton addPupilButton;
    private JButton deletePupilButton;
    private final JFrame parentFrame;
    private final JScrollPane paneForGradesTree;
    private final Data dataList;
    private final transient ConstantsOfStyle  styleConstants;
    private final transient ActualElements actualElements;

    public CentralPanel(JFrame parentFrame, JTextField currentStatusField, JTree treeForGradePanel,
                        DefaultMutableTreeNode rootForGradePanel, DefaultTreeModel gradesTreeModel,
                        JPanel panelForFilterRadioButtons, JButton addPupilButton,
                        JScrollPane paneForGradesTree, Data dataList,
                        DefaultTreeModel pupilsTreeModel, List<DefaultMutableTreeNode> nodesForPupilsPanel,
                        DefaultMutableTreeNode rootForPupilsTree,
                        ConstantsOfStyle styleConstants, JTree treeForPupilsPanel, ActualElements actualElements,
                        JLabel awardBarLabel) {
        this.currentStatusField = currentStatusField;
        this.parentFrame = parentFrame;
        this.treeForGradePanel = treeForGradePanel;
        this.rootForGradePanel = rootForGradePanel;
        this.gradesTreeModel = gradesTreeModel;
        this.panelForFilterRadioButtons = panelForFilterRadioButtons;
        this.addPupilButton = addPupilButton;
        this.paneForGradesTree = paneForGradesTree;
        this.dataList = dataList;
        this.pupilsTreeModel = pupilsTreeModel;
        this.nodesForPupilsPanel = nodesForPupilsPanel;
        this.rootForPupilsTree = rootForPupilsTree;
        this.styleConstants = styleConstants;
        this.treeForPupilsPanel = treeForPupilsPanel;
        this.actualElements = actualElements;
        this.awardBarLabel = awardBarLabel;

        this.setLayout(new BorderLayout());

        setComponentsForGradePanel();
        setPupilPanel();
        setComponentsForInformationPanel();

        setListeners();


        setPanels();
        this.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
    }

    private void setListeners() {
        treeForGradePanel.addMouseListener(new GradesTreeNodeMouseListener(treeForGradePanel, rootForPupilsTree,
                nodesForPupilsPanel, pupilsTreeModel, informationLabelForPupilPanel, pupilsPanel,
                getAllPupilsRadioButton, showEditAchievementButton, showEditMarksButton, editDataButton,
                deletePupilButton, dataList, actualElements));
        treeForPupilsPanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForPupilsPanel));
        treeForPupilsPanel.addMouseListener(new PupilsTreeNodeMouseListener(treeForPupilsPanel, pupilInformationLabel,
                informationPanel, showEditAchievementButton, showEditMarksButton, editDataButton, deletePupilButton,
                dataList, actualElements, awardBarLabel));
    }

    private void setComponentsForGradePanel() {
        gradesPanel = new JPanel(new GridBagLayout());
        gradesPanel.setPreferredSize(new Dimension(10, 568));
        gradesPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        gradesPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        setComponentsToGradePanel();
        gradesPanel.setToolTipText("Grades");
        treeForGradePanel.setVisible(false);

        basisForGradesPanel = new JPanel(new BorderLayout(5, 5));
        basisForGradesPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        basisForGradesPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
        basisForGradesPanel.add(gradesCapitalLabel, BorderLayout.NORTH);
        basisForGradesPanel.add(gradesPanel, BorderLayout.SOUTH);
    }

    private void setComponentsForInformationPanel() {
        pupilInformationLabel = new JLabel();
        pupilInformationLabel.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 19));

        showEditMarksButton = new JButton("MARKS");
        showEditMarksButton.addActionListener(this);
        showEditMarksButton.setPreferredSize(new Dimension(10, 17));
        showEditMarksButton.setBorder(BorderFactory.createLineBorder(styleConstants.getACTUAL_SET_OF_COLORS().get(0), 3));
        showEditMarksButton.setVisible(false);

        showEditAchievementButton = new JButton("ACHIEVEMENT");
        showEditAchievementButton.addActionListener(this);
        showEditAchievementButton.setPreferredSize(new Dimension(10, 17));
        showEditAchievementButton.setBorder(BorderFactory.createLineBorder
                (styleConstants.getACTUAL_SET_OF_COLORS().get(0), 3));
        showEditAchievementButton.setVisible(false);

        editDataButton = new JButton("DATE");
        editDataButton.addActionListener(this);
        editDataButton.setPreferredSize(new Dimension(10, 17));
        editDataButton.setBorder(BorderFactory.createLineBorder(styleConstants.getACTUAL_SET_OF_COLORS().get(0), 3));
        editDataButton.setVisible(false);

        deletePupilButton = new JButton("DELETE PUPIL");
        deletePupilButton.addActionListener(this);
        deletePupilButton.setPreferredSize(new Dimension(10, 17));
        deletePupilButton.setBorder(BorderFactory.createLineBorder(styleConstants.getACTUAL_SET_OF_COLORS().get(0), 3));
        deletePupilButton.setBackground(Color.darkGray);
        deletePupilButton.setForeground(Color.red);
        deletePupilButton.setVisible(false);


        JPanel buttonInformationPanel = new JPanel(new GridLayout(4, 1, 0, 0));
        buttonInformationPanel.setPreferredSize(new Dimension(10, 120));
        buttonInformationPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        buttonInformationPanel.add(editDataButton);
        buttonInformationPanel.add(showEditAchievementButton);
        buttonInformationPanel.add(showEditMarksButton);
        buttonInformationPanel.add(deletePupilButton);

        buttonInformationPanel.setOpaque(false);
        pupilInformationLabel.setOpaque(false);

        JLayeredPane layeredPane = new JLayeredPane();
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
        informationPanel.add(pupilInformationLabel, BorderLayout.NORTH);
        informationPanel.add(buttonInformationPanel, BorderLayout.SOUTH);
        informationPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        informationPanel.setBounds(0,0,370, 568);
        informationPanel.setOpaque(false);
        awardBarLabel.setBounds(280,0,35,600);
        awardBarLabel.setFont(new Font(null, Font.BOLD, 40));
        awardBarLabel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        awardBarLabel.setVisible(false);

        layeredPane.setPreferredSize(new Dimension(10, 568));
        layeredPane.add(bottomPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(informationPanel, Integer.valueOf(2));
        layeredPane.add(awardBarLabel, Integer.valueOf(1));

        JLabel informationCapitalLabel = new JLabel("Pupil");
        informationCapitalLabel.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.BOLD, 19));

        basisForInformationPanel = new JPanel(new BorderLayout(5, 5));
        basisForInformationPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        basisForInformationPanel.add(informationCapitalLabel, BorderLayout.NORTH);
        basisForInformationPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        basisForInformationPanel.add(layeredPane, BorderLayout.SOUTH);
    }

    private void setPupilPanel() {
        pupilsPanel = new JPanel(new BorderLayout());
        pupilsPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        pupilsPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        informationLabelForPupilPanel = new JLabel("List of pupils");
        informationLabelForPupilPanel.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.BOLD, 18));
        pupilsPanel.add(informationLabelForPupilPanel, BorderLayout.NORTH);

        treeForPupilsPanel.setCellRenderer(new CustomTreeCellRenderer(styleConstants));
        treeForPupilsPanel.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 17));
        treeForPupilsPanel.setRootVisible(false);
        treeForPupilsPanel.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));

        JScrollPane scrollPaneForPupilTree = new JScrollPane(treeForPupilsPanel);
        scrollPaneForPupilTree.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPaneForPupilTree.setPreferredSize(new Dimension(10, 568));
        pupilsPanel.add(scrollPaneForPupilTree);
    }

    private void setComponentsToGradePanel() {

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

        treeForGradePanel.setFont(new Font("MV Boli", Font.BOLD, 19));
        treeForGradePanel.setCellRenderer(new CustomTreeCellRenderer(styleConstants));

        treeForGradePanel.addMouseMotionListener(new HandCursorForMouseMotionAdapter(treeForGradePanel));

        paneForGradesTree.setPreferredSize(new Dimension(230, 330));
        paneForGradesTree.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        panelForFilterRadioButtons.setBorder(BorderFactory.createTitledBorder("Filters"));
        panelForFilterRadioButtons.setVisible(false);

        gradesCapitalLabel = new JLabel("Grades");
        gradesCapitalLabel.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.BOLD, 19));

        setComponentsForFilterPanel();
        setStyleForComponentsFilterPane
                (panelForFilterRadioButtons, styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 15));
        panelForFilterRadioButtons.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));

        addPupilButton.setPreferredSize(new Dimension(10, 25));
        addPupilButton.setBackground(Color.DARK_GRAY);
        addPupilButton.setForeground(Color.GREEN);
        addPupilButton.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.BOLD, 18));
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
        getPupilsWithAchievementRadioButton = new JRadioButton("With achievement");

        ButtonGroup radioFilterGroup = new ButtonGroup();
        radioFilterGroup.add(getAllPupilsRadioButton);
        radioFilterGroup.add(getPupilsWithBirthdayInThisMonthRadioButton);
        radioFilterGroup.add(getPupilsWithAwardBarRadioButton);
        radioFilterGroup.add(getNoPromotedPupilsRadioButton);
        radioFilterGroup.add(getPupilsWithAchievementRadioButton);

        panelForFilterRadioButtons.add(getAllPupilsRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithBirthdayInThisMonthRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithAwardBarRadioButton);
        panelForFilterRadioButtons.add(getNoPromotedPupilsRadioButton);
        panelForFilterRadioButtons.add(getPupilsWithAchievementRadioButton);

        getAllPupilsRadioButton.addActionListener(this);
        getPupilsWithBirthdayInThisMonthRadioButton.addActionListener(this);
        getPupilsWithAwardBarRadioButton.addActionListener(this);
        getNoPromotedPupilsRadioButton.addActionListener(this);
        getPupilsWithAchievementRadioButton.addActionListener(this);
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

        if (e.getSource() == getAllPupilsRadioButton) {
            getAllPupilsMethod();
        }
        if (e.getSource() == getPupilsWithBirthdayInThisMonthRadioButton) {
            getPupilsWithBirthdayInThisMonthMethod();
        }
        if (e.getSource() == getNoPromotedPupilsRadioButton) {
            getNoPromotedPupilsMethod();
        }
        if (e.getSource() == getPupilsWithAchievementRadioButton) {
            getPupilsWithAchievementMethod();
        }
        if (e.getSource() == getPupilsWithAwardBarRadioButton) {
            getPupilsWithAwardBarMethod();
        }
        if (e.getSource() == showEditAchievementButton) {
            editAchievementMethod();
        }
        if (e.getSource() == editDataButton) {
            editDataMethod();
        }
        if (e.getSource() == deletePupilButton) {
            deletePupilMethod();
        }
        if (e.getSource() == showEditMarksButton) {
            showEditMarkMethod();
        }
        if (e.getSource() == addPupilButton) {
            addPupilMethod();
        }
    }

    private void getAllPupilsMethod() {
        List<Pupil> list = (actualElements.getCurrentGrade() < 0)
                ? dataList.getListOfAllPupils()
                : dataList.getListOfPupilsOfCertainGrade(actualElements.getCurrentGrade());
        buildPupilsTree(list);
    }

    private void getPupilsWithBirthdayInThisMonthMethod() {

        List<Pupil> list = dataList.getPupilsWithBirthdayInThisMonth(actualElements.getCurrentGrade());
        buildPupilsTree(list, true);
    }

    private void getNoPromotedPupilsMethod() {

        List<Pupil> list = dataList.getNoPromotedPupilsList(actualElements.getCurrentGrade());
        buildPupilsTree(list);

    }

    private void getPupilsWithAchievementMethod() {
        List<Pupil> list = dataList.getPupilsWithAchievementList(actualElements.getCurrentGrade());
        buildPupilsTree(list);
    }

    private void getPupilsWithAwardBarMethod() {

        List<Pupil> list = dataList.getPupilsWithAwardBarList(actualElements.getCurrentGrade());
        buildPupilsTree(list);

    }

    private void editAchievementMethod() {
        AddEditAchievement addEditAchievement = new AddEditAchievement(parentFrame,
                actualElements.getCurrentPupil().getAchievement(), currentStatusField, styleConstants,
                actualElements);
        actualElements.getCurrentPupil().setAchievement(addEditAchievement.showDialogAndGetInput());
    }

    private void editDataMethod() {
        Pupil chosenPupil = actualElements.getCurrentPupil();
        new AddEditPupil(parentFrame, chosenPupil, currentStatusField, false, dataList,
                styleConstants, actualElements);
        assert chosenPupil != null;
        pupilInformationLabel.setText(chosenPupil.getPupilInformation());
        awardBarLabel.setVisible(actualElements.getCurrentPupil().isAwardBar());
        refreshPupilsTree();
    }

    private void deletePupilMethod() {
        String[] responses = {"Yes, I do", "No, I've changed my mind"};
        if (JOptionPane.showOptionDialog(parentFrame, "Do you want to delete this pupil?", "ARE YOU SURE?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                responses, responses[0]) == 0) {
            dataList.removePupil(actualElements.getCurrentPupil());
            showEditMarksButton.setVisible(false);
            showEditAchievementButton.setVisible(false);
            editDataButton.setVisible(false);
            deletePupilButton.setVisible(false);
            if (getAllPupilsRadioButton.isSelected()) {
                buildPupilsTree(dataList.getListOfPupilsOfCertainGrade(actualElements.getCurrentGrade()));
            } else if (getPupilsWithAchievementRadioButton.isSelected()) {
                buildPupilsTree(dataList.getPupilsWithAchievementList(actualElements.getCurrentGrade()));
            } else if (getNoPromotedPupilsRadioButton.isSelected()) {
                buildPupilsTree(dataList.getNoPromotedPupilsList(actualElements.getCurrentGrade()));
            } else if (getPupilsWithBirthdayInThisMonthRadioButton.isSelected()) {
                buildPupilsTree(dataList.getPupilsWithBirthdayInThisMonth(actualElements.getCurrentGrade()));
            } else {
                buildPupilsTree(dataList.getPupilsWithAwardBarList(actualElements.getCurrentGrade()));
            }
            pupilInformationLabel.setText("Pupil is removed");
            currentStatusField.setText("Pupil is removed");
        }
    }

    private void showEditMarkMethod() {
        AddEditMarks addEditMarks = null;
        Pupil chosenPupil = dataList.getPupilWithCertainID(actualElements.getCurrentID());
        assert chosenPupil != null;
        addEditMarks = new AddEditMarks(parentFrame, chosenPupil.getMarks(), chosenPupil.isAwardBar(),
                chosenPupil.isPromotionToNextGrade(), chosenPupil.getGrade(), currentStatusField, false,
                styleConstants, actualElements);
        chosenPupil.setMarks(addEditMarks.showDialogAndGetInput());
        chosenPupil.setPromotionToNextGrade(chosenPupil.getMarks().getPromotion(chosenPupil.getGrade()));
        chosenPupil.setAwardBar(chosenPupil.getMarks().isAwardBar(chosenPupil.isPromotionToNextGrade(),
                chosenPupil.getGrade()));
    }

    private void addPupilMethod() {
        Pupil tempPupil = new Pupil();
        int gradeNumber = (actualElements.getCurrentGrade()!=-1) ? actualElements.getCurrentGrade() : 0;
        tempPupil.setGrade(gradeNumber);
        new AddEditPupil(parentFrame, tempPupil, currentStatusField, true, dataList, styleConstants,
                actualElements);
        if (tempPupil.getSurname() != null) {
            dataList.addPupilToList(tempPupil);
            actualElements.setCurrentPupil(tempPupil);
            actualElements.setCurrentGrade(tempPupil.getGrade());
            actualElements.setCurrentID(tempPupil.getId());
            getAllPupilsRadioButton.setSelected(true);
            currentStatusField.setText(String.format("Pupil '%s' if added",
                    actualElements.getCurrentPupil().getNamesAndSurname()));
            pupilInformationLabel.setText(actualElements.getCurrentPupil().getPupilInformation());
            informationLabelForPupilPanel.setText(getTextForPupilsPanelLabel(tempPupil.getGrade()));
            refreshPupilsTree();
            TreePath path = treeForGradePanel.getSelectionPath();
            assert path != null;
            DefaultMutableTreeNode gradeNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            informationLabelForPupilPanel.setText(gradeNode.toString());
        }

    }

    private void refreshPupilsTree() {
        rootForPupilsTree.removeAllChildren();
        List<Pupil> list;

        if (getAllPupilsRadioButton.isSelected()) {
            list = dataList.getListOfPupilsOfCertainGrade(actualElements.getCurrentGrade());
        } else if (getPupilsWithAchievementRadioButton.isSelected()) {
            list = dataList.getPupilsWithAchievementList(actualElements.getCurrentGrade());
        } else if (getNoPromotedPupilsRadioButton.isSelected()) {
            list = dataList.getNoPromotedPupilsList(actualElements.getCurrentGrade());
        } else if (getPupilsWithBirthdayInThisMonthRadioButton.isSelected()) {
            list = dataList.getPupilsWithBirthdayInThisMonth(actualElements.getCurrentGrade());
        } else {
            list = dataList.getPupilsWithAwardBarList(actualElements.getCurrentGrade());
        }

        int pupilNumberInTheList = 0;

        for (int i = 0; i < list.size(); i++) {
            String nameNode = String.format("%d. %s", i + 1, list.get(i).getIdNamesSurname());
            nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
            rootForPupilsTree.add(nodesForPupilsPanel.get(i));

            if (list.get(i).equals(actualElements.getCurrentPupil())) {
                pupilNumberInTheList = i;
            }
        }


        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        treeForPupilsPanel.setSelectionRow(pupilNumberInTheList);
        treeForGradePanel.setSelectionRow(actualElements.getCurrentGrade() + 1);
        pupilsPanel.repaint();
    }

    public void buildPupilsTree(List<Pupil> list) {
        rootForPupilsTree.removeAllChildren();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                String nameNode = (actualElements.getCurrentGrade() > -1)
                        ? String.format("%d. %s", i + 1, list.get(i).getIdNamesSurname())
                        : list.get(i).getGradeIdNamesSurname();
                nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                rootForPupilsTree.add(nodesForPupilsPanel.get(i));
            }

        } else {
            String textForLabel = String.format
                    ("<html>Class %d<br>there are no pupils with chosen parameters in this class</html>",
                            actualElements.getCurrentGrade());
            informationLabelForPupilPanel.setText(textForLabel);
        }
        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        pupilsPanel.repaint();
    }
    public void buildPupilsTree(List<Pupil> list, boolean withDateOfBirthday) {
        rootForPupilsTree.removeAllChildren();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                String nameNode;
                if (withDateOfBirthday) {
                    nameNode = (actualElements.getCurrentGrade() > -1)
                            ? String.format("%d. %d/%d/%d. %s", i + 1, list.get(i).getDateOfBirth().getDayOfMonth(),
                            list.get(i).getDateOfBirth().getMonthValue(),
                            list.get(i).getDateOfBirth().getYear(), list.get(i).getIdNamesSurname())
                            : list.get(i).getGradeBirthdayDayIdNamesSurname();
                    nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                    rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                } else {
                    nameNode = (actualElements.getCurrentGrade() > -1)
                            ? String.format("%d. %s", i + 1, list.get(i).getIdNamesSurname())
                            : list.get(i).getGradeIdNamesSurname();
                    nodesForPupilsPanel.add(i, new DefaultMutableTreeNode(nameNode));
                    rootForPupilsTree.add(nodesForPupilsPanel.get(i));
                }
            }

        } else {
            String textForLabel = String.format
                    ("<html>Class %d<br>there are no pupils with chosen parameters in this class</html>",
                            actualElements.getCurrentGrade());
            informationLabelForPupilPanel.setText(textForLabel);
        }
        pupilsTreeModel.nodeStructureChanged(rootForPupilsTree);
        pupilsPanel.repaint();
    }


    private void setStyleForComponentsFilterPane(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JRadioButton) {
                component.setFont(font);
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
        }
    }
    private String getTextForPupilsPanelLabel(int grade) {
        if (grade == 0) {
            return "Class zero: ";
        } else if (grade == 1) {
            return "The firth class";
        } else if (grade == 2) {
            return "The second class";
        } else if (grade == 3) {
            return "The third class";
        } else if (grade == 4) {
            return "The fourth class";
        } else if (grade == 5) {
            return "The fifth class";
        } else if (grade == 6) {
            return "The sixth class";
        } else if (grade == 7) {
            return "The seventh class";
        } else if (grade == 8) {
            return "The eighth class";
        } else {
            return "";
        }
    }
}
