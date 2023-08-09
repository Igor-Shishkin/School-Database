package GUI.addPupil;

import GUI.listeners.IsMarkEnteredListener;
import GUI.styleStorage.ConstantsOfColors;
import database.GeneratePupilData;
import database.Marks;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class AddMarks extends JDialog implements ActionListener {
    int QUANTITY_OF_MARK_COMBOBOX;
    Marks marks;
    boolean awardBar, promotionToNextGrade;
    int grade;
    JLabel mathLabel, polishLabel, englishLabel, informationLabel, peLabel, musicLabel, religionLabel, natureLabel,
            biologyLabel, physicsLabel, geographyLabel, behaviorLabel, averageLabel;
    JComboBox[] markComboBox = new JComboBox[44];
    JPanel panelForComboBox, panelForLabels;
    JLabel[] averageScoreLabel = new JLabel[4];
    Font font, fontForAverage;
    JButton addButton, cancelButton;

    AddMarks(JFrame parentFrame, Marks marks, Boolean awardBar, Boolean promotionToNextGrade, int grade) {
        super(parentFrame, "Marks", true);
        GeneratePupilData generate = new GeneratePupilData();
        marks = generate.generateMarks456();
        this.marks = marks;
        this.awardBar = awardBar;
        this.promotionToNextGrade = promotionToNextGrade;
        this.grade = grade;

        font = ConstantsOfColors.THE_MAIN_FONT.deriveFont(Font.PLAIN, 19);
        fontForAverage = ConstantsOfColors.THE_MAIN_FONT.deriveFont(Font.BOLD, 20);
        setWindowCloseListener();
        this.setLayout(new BorderLayout());

        addComponentsOfPanels();
        setMarksInComboBox();
        setActionListenerForBackGroundOfCombobox();
        panelForComboBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelForComboBox.setPreferredSize(new Dimension(300, 400));
        panelForLabels.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelForLabels.setPreferredSize(new Dimension(130, 400));


        addButton = new JButton("Done!");
        cancelButton = new JButton("Cancel");
        addButton.setPreferredSize(new Dimension(10, 30));
        cancelButton.setPreferredSize(new Dimension(10, 30));
        cancelButton.addActionListener(this);
        addButton.addActionListener(this);

        setEnabledForYearMarks();


        this.add(panelForComboBox, BorderLayout.EAST);
        this.add(panelForLabels, BorderLayout.WEST);
        this.add(addButton, BorderLayout.PAGE_START);
        this.add(cancelButton, BorderLayout.AFTER_LAST_LINE);
        setFontForComponents(this, font);
        setHorizontalAlignment(this);


//        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Enter marks");
//        this.setVisible(true);
    }
    public Marks showDialogAndGetInput() {
        this.setVisible(true);
        return marks;
    }

    private void setMarksInComboBox() {
        if (marks != null) {
            int[] arrayOfMarks = marks.getArrayOfMarks();
            arrayOfMarks[0] = -1;
            arrayOfMarks[5] = -1;
            arrayOfMarks[6] = -1;
            System.out.println(Arrays.toString(arrayOfMarks));
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
            System.out.println(marks.toString());
            for (int i = 0; i < 4; i++) {
                boolean isAllMarksInColumn = true;
                double sum = 0;
                int quantityEntered = 0;
                for (int j = i; j < QUANTITY_OF_MARK_COMBOBOX; j+=4) {
                    markComboBox[j].setBackground(
                            (markComboBox[j].getSelectedItem()!=null)?ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT:
                            this.getBackground());
                    if (markComboBox[j].getSelectedItem()==null || !markComboBox[j].isEnabled()) {
                        isAllMarksInColumn = false;
                    } else {
                        sum += markComboBox[j].getSelectedIndex();
                        quantityEntered++;
                    }
                    averageScoreLabel[i%4].setText(String.format("%02.1f",
                            (quantityEntered!=0&&sum!=0)?sum/quantityEntered:0f));
                    averageScoreLabel[i].setBackground((isAllMarksInColumn)?ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT:
                            this.getBackground());
                }
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
            markComboBox[i].addActionListener(new IsMarkEnteredListener(markComboBox, i, QUANTITY_OF_MARK_COMBOBOX,
                    averageScoreLabel, this.getBackground()));
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

            panelForLabels.add(mathLabel = new JLabel("Math"));
            panelForLabels.add(polishLabel = new JLabel("Polish"));
            panelForLabels.add(englishLabel = new JLabel("English"));
            panelForLabels.add(informationLabel = new JLabel("Information"));
            panelForLabels.add(peLabel = new JLabel("PE"));
            panelForLabels.add(musicLabel = new JLabel("Music"));
            panelForLabels.add(religionLabel = new JLabel("Religion"));
            panelForLabels.add(natureLabel = new JLabel("Nature"));
            panelForLabels.add(behaviorLabel = new JLabel("Behavior"));
            panelForLabels.add(averageLabel = new JLabel("AVERAGE"));

            for (int i = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++) {
                panelForComboBox.add(markComboBox[i] = new JComboBox<>(new String[]{null, "1", "2", "3", "4", "5", "6"}));
            }
            for (int i = 0; i < 4; i++) {
                averageScoreLabel[i] = new JLabel();
                panelForComboBox.add(averageScoreLabel[i]);
                averageScoreLabel[i].setOpaque(true);

//                averageScoreLabel[i].setEnabled(false);
                averageScoreLabel[i].setFont(fontForAverage);
//                averageScoreLabel[i].setForeground(new Color(0x7B0000));
            }
        } else {
            QUANTITY_OF_MARK_COMBOBOX = 44;
            panelForComboBox = new JPanel(new GridLayout(12, 4, 10, 10));
            panelForLabels = new JPanel(new GridLayout(12, 1, 10, 10));
            panelForLabels.add(mathLabel = new JLabel("Math"));
            panelForLabels.add(polishLabel = new JLabel("Polish"));
            panelForLabels.add(englishLabel = new JLabel("English"));
            panelForLabels.add(informationLabel = new JLabel("Information"));
            panelForLabels.add(peLabel = new JLabel("PE"));
            panelForLabels.add(musicLabel = new JLabel("Music"));
            panelForLabels.add(religionLabel = new JLabel("Religion"));
            panelForLabels.add(biologyLabel = new JLabel("Biology"));
            panelForLabels.add(physicsLabel = new JLabel("Physics"));
            panelForLabels.add(geographyLabel = new JLabel("Geography"));
            panelForLabels.add(behaviorLabel = new JLabel("Behavior"));
            panelForLabels.add(averageLabel = new JLabel("AVERAGE"));

            for (int i = 0; i < QUANTITY_OF_MARK_COMBOBOX; i++) {
                panelForComboBox.add(markComboBox[i] = new JComboBox<>(new String[]{null, "1", "2", "3", "4", "5", "6"}));
            }

            for (int i = 0; i < 4; i++) {
                averageScoreLabel[i] = new JLabel();
                panelForComboBox.add(averageScoreLabel[i]);
                averageScoreLabel[i].setOpaque(true);
//                averageScoreLabel[i].setEnabled(false);
                averageScoreLabel[i].setFont(fontForAverage);
//                averageScoreLabel[i].setForeground(new Color(0x7B0000));
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

            dispose();

        }
    }
}
