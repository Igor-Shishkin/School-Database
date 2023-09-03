package school.database.gui.add_edit_windows;

import school.database.gui.ActualElements;
import school.database.gui.styleStorage.ConstantsOfStyle;
import school.database.gui.listeners.MarkComboBoxListener;
import school.database.data.objects.Marks;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class AddEditMarks extends JDialog implements ActionListener {
    private int QUANTITY_OF_MARK_COMBOBOX;
    private transient Marks marks;
    private final boolean awardBar;
    private final boolean promotionToNextGrade;
    int grade;
    private JLabel flagLabel;
    private JLabel promotionLabel;
    private final JComboBox[] markComboBox = new JComboBox[44];
    private JPanel panelForComboBox, panelForLabels,  centralPanel;
    private JLayeredPane promotionLayeredPane;
    private final JLabel[] averageScoreLabel = new JLabel[4];
    private final Font fontForAverage;
    private JButton addButton, cancelButton;
    private final JTextField currentStatusField;
    private final boolean isNewPupil;
    private final transient ConstantsOfStyle styleConstants;
    private final transient ActualElements actualElements;

    public AddEditMarks(JFrame parentFrame, Marks marks, Boolean awardBar, Boolean promotion, int grade,
                        JTextField currentStatusField, boolean isNewPupil, ConstantsOfStyle styleConstants,
                        ActualElements actualElements) {
        super(parentFrame, "Marks", true);
        this.marks = marks;
        this.grade = grade;
        this.promotionToNextGrade = promotion;
        this.awardBar = awardBar;
        this.currentStatusField = currentStatusField;
        this.isNewPupil = isNewPupil;
        this.styleConstants = styleConstants;
        this.actualElements = actualElements;

        this.setLayout(new BorderLayout());

        Font font = styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 19);
        fontForAverage = styleConstants.getTHE_MAIN_FONT().deriveFont(Font.BOLD, 20);

        setWindowCloseListener();
        setPanels();
        setButtons();
        setEnabledForYearMarks();


        this.add(addButton, BorderLayout.PAGE_START);
        this.add(centralPanel, BorderLayout.CENTER);
        this.add(cancelButton, BorderLayout.AFTER_LAST_LINE);

        setFontForComponents(this, font);
        setHorizontalAlignment(this);
        promotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        setStyleForWindow(this);

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Enter marks");
    }

    private void setPanels() {
        promotionLayeredPane = new JLayeredPane();
        promotionLayeredPane.setBorder(BorderFactory.createSoftBevelBorder(0, Color.GRAY, Color.DARK_GRAY));
        flagLabel = new JLabel();
        flagLabel.setIcon(new ImageIcon(styleConstants.getHORIZONTAL_FLAG_IMAGE()));
        flagLabel.setBounds(0,0,500,60);
        flagLabel.setVisible(awardBar);
        flagLabel.setBorder(BorderFactory.createSoftBevelBorder(0, Color.GRAY, Color.DARK_GRAY));
        promotionLayeredPane.add(flagLabel);
        promotionLabel = new JLabel("<html>The pupil has been promoted" +
                "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to the next class</html>");
        promotionLabel.setOpaque(false);
        promotionLabel.setBounds(0,0,400,60);
        promotionLayeredPane.add(promotionLabel,
                Integer.valueOf(1));
        promotionLayeredPane.setPreferredSize(new Dimension(10,60));
        promotionLayeredPane.setVisible(promotionToNextGrade);

        addComponentsOfPanels();
        setMarksInComboBox();
        setActionListenerForBackGroundOfCombobox();
        panelForComboBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelForComboBox.setPreferredSize(new Dimension(300, 400));
        panelForLabels.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelForLabels.setPreferredSize(new Dimension(130, 400));


        centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(panelForLabels, BorderLayout.WEST);
        centralPanel.add(panelForComboBox, BorderLayout.EAST);
        centralPanel.add(promotionLayeredPane, BorderLayout.SOUTH);
    }

    private void setButtons() {
        addButton = new JButton("Done!");
        cancelButton = new JButton("Cancel");
        addButton.setPreferredSize(new Dimension(10, 50));
        addButton.setBorder(BorderFactory.createLineBorder(this.getBackground(),3));
        cancelButton.setPreferredSize(new Dimension(10, 50));
        cancelButton.setBorder(BorderFactory.createLineBorder(this.getBackground(),3));
        cancelButton.addActionListener(this);
        addButton.addActionListener(this);
    }

    public Marks showDialogAndGetInput() {
        this.setVisible(true);
        return marks;
    }

    private void setMarksInComboBox() {
        if (marks != null) {
            int[] arrayOfMarks = marks.getArrayOfMarks();
            int jumpIndex = (grade>6) ? 27 : 31;
            for (int i = 0, j = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++, j++) {
                markComboBox[i].setSelectedIndex(arrayOfMarks[j]);
                if (j==jumpIndex) {
                    if (grade>6) {
                        j+=4;
                    } else {
                        j+=12;
                    }
                }
            }
            setEnabledForYearMarks();
            setAvarageLabels();

            for (int j = QUANTITY_OF_MARK_COMBOBOX-4; j < QUANTITY_OF_MARK_COMBOBOX; j++) {
                markComboBox[j].setBackground(
                        (markComboBox[j].getSelectedItem()!=null)? styleConstants.getCOLOR_FOR_RIGHT_FORMAT()
                                : this.getBackground());
            }
        }


    }

    private void setAvarageLabels() {
        for (int i = 0; i < 4; i++) {
            boolean isAllMarksInColumn = true;
            double sum = 0;
            int quantityEntered = 0;
            for (int j = i; j < QUANTITY_OF_MARK_COMBOBOX-4; j+=4) {
                markComboBox[j].setBackground(
                        (markComboBox[j].getSelectedItem()!=null)? styleConstants.getCOLOR_FOR_RIGHT_FORMAT():
                                this.getBackground());
                if (markComboBox[j].getSelectedItem()==null || !markComboBox[j].isEnabled()) {
                    isAllMarksInColumn = false;
                } else {
                    sum += markComboBox[j].getSelectedIndex();
                    quantityEntered++;
                }
                averageScoreLabel[i].setText(String.format("%02.1f",
                        (quantityEntered!=0&&sum!=0)?sum/quantityEntered:0f));
                averageScoreLabel[i].setBackground((isAllMarksInColumn &&
                        markComboBox[QUANTITY_OF_MARK_COMBOBOX-4+(i)].getSelectedIndex()!=-1)
                        ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : this.getBackground());
            }
        }
    }

    private void setHorizontalAlignment(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel) {
                ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
                ((JLabel) component).setHorizontalTextPosition(SwingConstants.RIGHT);
            }
            if (component instanceof Container) {
                setHorizontalAlignment((Container) component);
            }
        }
    }

    private void setEnabledForYearMarks() {
        for (int i = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++) {
            markComboBox[i + 3].setEnabled(markComboBox[i].getSelectedItem() != null &&
                    markComboBox[i + 1].getSelectedItem() != null && markComboBox[i + 2].getSelectedItem() != null);
            i += 3;
        }

    }

    private void setActionListenerForBackGroundOfCombobox() {
        for (int i = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++) {
            markComboBox[i].addActionListener(new MarkComboBoxListener(markComboBox, i, QUANTITY_OF_MARK_COMBOBOX,
                    averageScoreLabel, panelForComboBox.getBackground(), promotionLayeredPane, flagLabel, styleConstants));
        }
    }

    private void setFontForComponents(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JComboBox || component instanceof JButton ||
                    component instanceof JPanel) {
                component.setFont(font);
            }
            if (component instanceof Container) {
                setFontForComponents((Container) component, font);
            }
        }
    }

    private void addComponentsOfPanels() {

        if (grade > 3 && grade < 7) {
            QUANTITY_OF_MARK_COMBOBOX = 36;
            panelForComboBox = new JPanel(new GridLayout(10, 4, 10, 10));
            panelForLabels = new JPanel(new GridLayout(10, 1, 10, 10));

            panelForLabels.add(new JLabel("Math"));
            panelForLabels.add(new JLabel("Polish"));
            panelForLabels.add(new JLabel("English"));
            panelForLabels.add(new JLabel("Information"));
            panelForLabels.add(new JLabel("PE"));
            panelForLabels.add(new JLabel("Music"));
            panelForLabels.add(new JLabel("Religion"));
            panelForLabels.add(new JLabel("Nature"));
            panelForLabels.add(new JLabel("Behavior"));
            panelForLabels.add(new JLabel("AVERAGE"));

            for (int i = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++) {
                markComboBox[i] = new JComboBox<>(new String[]{null, "1", "2", "3", "4", "5", "6"});
                panelForComboBox.add(markComboBox[i]);
            }
            for (int i = 0; i < 4; i++) {
                averageScoreLabel[i] = new JLabel();
                panelForComboBox.add(averageScoreLabel[i]);
                averageScoreLabel[i].setOpaque(true);
                averageScoreLabel[i].setFont(fontForAverage);
            }
        } else {
            QUANTITY_OF_MARK_COMBOBOX = 44;
            panelForComboBox = new JPanel(new GridLayout(12, 4, 10, 10));
            panelForLabels = new JPanel(new GridLayout(12, 1, 10, 10));
            panelForLabels.add(new JLabel("Math"));
            panelForLabels.add(new JLabel("Polish"));
            panelForLabels.add(new JLabel("English"));
            panelForLabels.add(new JLabel("Information"));
            panelForLabels.add(new JLabel("PE"));
            panelForLabels.add(new JLabel("Music"));
            panelForLabels.add(new JLabel("Religion"));
            panelForLabels.add(new JLabel("Biology"));
            panelForLabels.add(new JLabel("Physics"));
            panelForLabels.add(new JLabel("Geography"));
            panelForLabels.add(new JLabel("Behavior"));
            panelForLabels.add(new JLabel("AVERAGE"));

            for (int i = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++) {
                markComboBox[i] = new JComboBox<>(new String[]{null, "1", "2", "3", "4", "5", "6"});
                panelForComboBox.add(markComboBox[i]);
            }

            for (int i = 0; i < 4; i++) {
                averageScoreLabel[i] = new JLabel();
                panelForComboBox.add(averageScoreLabel[i]);
                averageScoreLabel[i].setOpaque(true);
                averageScoreLabel[i].setFont(fontForAverage);
            }
        }
    }

    private void setWindowCloseListener() {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        null, "Do you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        };
        this.addWindowListener(windowListener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
        }
        if (e.getSource() == addButton) {
            int[] arrayOfMarks = new int[48];
            int min, max;
            if (grade>6) {
                min = 27; max = 32;
            } else {
                min = 31; max = 44;
            }
            for (int i = 0, k = 0; i < 48; i++) {
                if (i>min && i<max) {
                    arrayOfMarks[i] = -1;
                } else {
                    arrayOfMarks[i] = markComboBox[k].getSelectedIndex();
                    k++;
                }
            }
            marks = new Marks(arrayOfMarks);
            if (!isNewPupil) {
                currentStatusField.setText(String.format("Changes are saved (%s: MARK)",
                        actualElements.getCurrentPupil().getGradeIdNamesSurname()));
            }

            dispose();

        }
    }
    private void setStyleForWindow(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof JLabel || component instanceof JPanel) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof JComboBox) {
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(11));
            }
            if (component instanceof Container) {
                setStyleForWindow((Container) component);
            }
        }
    }
}
