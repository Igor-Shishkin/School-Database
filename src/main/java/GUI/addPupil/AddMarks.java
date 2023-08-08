package GUI.addPupil;

import GUI.styleStorage.ConstantsOfColors;
import database.Marks;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;



public class AddMarks extends JDialog implements ActionListener {
    Marks marks;
    boolean awardBar, promotionToNextGrade;
    int grade;
    JLabel mathLabel, polishLabel, englishLabel, informationLabel, peLabel, musicLabel, religionLabel, natureLabel,
            biologyLabel, physicsLabel, geographyLabel, behaviorLabel;
    JComboBox mathIComboBox, mathIIComboBox, mathIIIComboBox, mathYearComboBox,
            polishIComboBox, polishIIComboBox, polishIIIComboBox, polishYearComboBox,
            englishIComboBox, englishIIComboBox, englishIIIComboBox, englishYearComboBox,
            informationIComboBox, informationIIComboBox, informationIIIComboBox, informationYearComboBox,
            peIComboBox, peIIComboBox, peIIIComboBox, peYearComboBox,
            musicIComboBox, musicIIComboBox, musicIIIComboBox, musicYearComboBox,
            religionIComboBox, religionIIComboBox, religionIIIComboBox, religionYearComboBox,
            natureIComboBox, natureIIComboBox, natureIIIComboBox, natureYearComboBox,
            biologyIComboBox, biologyIIComboBox, biologyIIIComboBox, biologyYearComboBox,
            physicsIComboBox, physicsIIComboBox, physicsIIIComboBox, physicsYearComboBox,
            geographyIComboBox, geographyIIComboBox, geographyIIIComboBox, geographyYearComboBox,
            behaviorIComboBox, behaviorIIComboBox, behaviorIIIComboBox, behaviorYearComboBox;
    JPanel panel;
    Font font;
    AddMarks (JFrame parentFrame, Marks marks, Boolean awardBar, Boolean promotionToNextGrade, int grade) {
        super(parentFrame, "Marks", true);
        this.marks=marks;
        this.awardBar = awardBar;
        this.promotionToNextGrade = promotionToNextGrade;
        this.grade = grade;

        font = ConstantsOfColors.THE_MAIN_FONT.deriveFont(Font.PLAIN, 19);
        setWindowCloseListener();
        this.setLayout(new BorderLayout());

        setComboBoxesAndLabels();
        addComponentsOfPanel();
        setFontForComponents(panel, font);
        panel.setBorder(new EmptyBorder(10,10,10,10));



        this.add(panel, BorderLayout.NORTH);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write pupil's data");
        this.setVisible(true);
    }

    private void setFontForComponents(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JComboBox || component instanceof JButton) {
                component.setFont(font);
            }
            if (component instanceof Container) {
                setFontForComponents((Container) component, font);
            }
        }
    }

    private void addComponentsOfPanel() {
        if (grade>3 && grade<7) {
            panel = new JPanel(new GridLayout(9,5,10,10));

            panel.add(mathLabel);
            panel.add(mathIComboBox);
            panel.add(mathIIComboBox);
            panel.add(mathIIIComboBox);
            panel.add(mathYearComboBox);

            panel.add(polishLabel);
            panel.add(polishIComboBox);
            panel.add(polishIIComboBox);
            panel.add(polishIIIComboBox);
            panel.add(polishYearComboBox);

            panel.add(englishLabel);
            panel.add(englishIComboBox);
            panel.add(englishIIComboBox);
            panel.add(englishIIIComboBox);
            panel.add(englishYearComboBox);

            panel.add(informationLabel);
            panel.add(informationIComboBox);
            panel.add(informationIIComboBox);
            panel.add(informationIIIComboBox);
            panel.add(informationYearComboBox);

            panel.add(peLabel);
            panel.add(peIComboBox);
            panel.add(peIIComboBox);
            panel.add(peIIIComboBox);
            panel.add(peYearComboBox);

            panel.add(musicLabel);
            panel.add(musicIComboBox);
            panel.add(musicIIComboBox);
            panel.add(musicIIIComboBox);
            panel.add(musicYearComboBox);

            panel.add(religionLabel);
            panel.add(religionIComboBox);
            panel.add(religionIIComboBox);
            panel.add(religionIIIComboBox);
            panel.add(religionYearComboBox);

            panel.add(natureLabel);
            panel.add(natureIComboBox);
            panel.add(natureIIComboBox);
            panel.add(natureIIIComboBox);
            panel.add(natureYearComboBox);

            panel.add(behaviorLabel);
            panel.add(behaviorIComboBox);
            panel.add(behaviorIIComboBox);
            panel.add(behaviorIIIComboBox);
            panel.add(behaviorYearComboBox);
        } else {
            panel = new JPanel(new GridLayout(11,5,10,10));

            panel.add(mathLabel);
            panel.add(mathIComboBox);
            panel.add(mathIIComboBox);
            panel.add(mathIIIComboBox);
            panel.add(mathYearComboBox);

            panel.add(polishLabel);
            panel.add(polishIComboBox);
            panel.add(polishIIComboBox);
            panel.add(polishIIIComboBox);
            panel.add(polishYearComboBox);

            panel.add(englishLabel);
            panel.add(englishIComboBox);
            panel.add(englishIIComboBox);
            panel.add(englishIIIComboBox);
            panel.add(englishYearComboBox);

            panel.add(informationLabel);
            panel.add(informationIComboBox);
            panel.add(informationIIComboBox);
            panel.add(informationIIIComboBox);
            panel.add(informationYearComboBox);

            panel.add(peLabel);
            panel.add(peIComboBox);
            panel.add(peIIComboBox);
            panel.add(peIIIComboBox);
            panel.add(peYearComboBox);

            panel.add(musicLabel);
            panel.add(musicIComboBox);
            panel.add(musicIIComboBox);
            panel.add(musicIIIComboBox);
            panel.add(musicYearComboBox);

            panel.add(religionLabel);
            panel.add(religionIComboBox);
            panel.add(religionIIComboBox);
            panel.add(religionIIIComboBox);
            panel.add(religionYearComboBox);

            panel.add(biologyLabel);
            panel.add(biologyIComboBox);
            panel.add(biologyIIComboBox);
            panel.add(biologyIIIComboBox);
            panel.add(biologyYearComboBox);

            panel.add(physicsLabel);
            panel.add(physicsIComboBox);
            panel.add(physicsIIComboBox);
            panel.add(physicsIIIComboBox);
            panel.add(physicsYearComboBox);

            panel.add(geographyLabel);
            panel.add(geographyIComboBox);
            panel.add(geographyIIComboBox);
            panel.add(geographyIIIComboBox);
            panel.add(geographyYearComboBox);

            panel.add(behaviorLabel);
            panel.add(behaviorIComboBox);
            panel.add(behaviorIIComboBox);
            panel.add(behaviorIIIComboBox);
            panel.add(behaviorYearComboBox);
        }

    }

    private void setComboBoxesAndLabels() {
        mathLabel = new JLabel("Math");
        polishLabel = new JLabel("Polish");
        englishLabel = new JLabel("English");
        informationLabel = new JLabel("Information");
        peLabel = new JLabel("PE");
        musicLabel = new JLabel("Music");
        religionLabel = new JLabel("Religion");
        natureLabel = new JLabel("Nature");
        biologyLabel = new JLabel("Biology");
        physicsLabel = new JLabel("Physics");
        geographyLabel = new JLabel("Geography");
        behaviorLabel = new JLabel("Behavior");

        mathIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        mathIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        mathIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        mathYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        polishIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        polishIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        polishIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        polishYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        englishIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        englishIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        englishIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        englishYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        informationIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        informationIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        informationIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        informationYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        peIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        peIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        peIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        peYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        musicIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        musicIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        musicIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        musicYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        religionIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        religionIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        religionIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        religionYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        natureIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        natureIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        natureIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        natureYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        biologyIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        biologyIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        biologyIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        biologyYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        physicsIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        physicsIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        physicsIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        physicsYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        geographyIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        geographyIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        geographyIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        geographyYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        behaviorIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        behaviorIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        behaviorIIIComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
        behaviorYearComboBox = new JComboBox<>(new Integer[]{null,1,2,3,4,5,6});
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

    }
}
