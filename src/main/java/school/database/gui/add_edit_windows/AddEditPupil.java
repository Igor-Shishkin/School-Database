package school.database.gui.add_edit_windows;

import school.database.gui.ActualElements;
import school.database.gui.listeners.*;
import school.database.gui.styleStorage.ConstantsOfStyle;
import school.database.data.Data;
import school.database.data.objects.Marks;
import school.database.data.objects.Pupil;
import school.database.data.objects.Address;
import school.database.data.objects.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class AddEditPupil extends JDialog implements ActionListener {
    private transient Parent parent1 = null, parent2 = null;
    private boolean awardBar;
    private boolean promotionToNextGrade;
    private final boolean isNewPupil;
    private transient Marks marks;


    private JTextField nameField, surnameField, secondNameField, peselField, idField, yearField, monthField, dayField,
            countryField, provinceField, townField, streetField, houseField,
            localField, postCodeField;
    private JComboBox<String> genderComboBox;
    private JComboBox<Integer> gradeComboBox;
    private JButton addFirstParentButton, addSecondParentButton, markButton, addButton, cancelButton, achievementButton;
    private final transient Pupil pupil;
    private String achievement;
    private final JTextField currentStatusField;
    private final Data dataList;
    private final transient ConstantsOfStyle styleConstants;
    private final transient ActualElements actualElements;
    private final JFrame parentFrame;


    public AddEditPupil(JFrame parentFrame, Pupil pupil, JTextField currentStatusField, boolean isNewPupil,
                        Data dataList, ConstantsOfStyle styleConstants, ActualElements actualElements) {
        super(parentFrame, "Achievement", true);
        this.pupil = pupil;
        this.currentStatusField = currentStatusField;
        this.isNewPupil = isNewPupil;
        this.dataList = dataList;
        this.styleConstants = styleConstants;
        this.actualElements = actualElements;
        this.parentFrame = parentFrame;

        if (isNewPupil) {
            pupil.setId(dataList.getMinPossibleID());
        } else {
            parent1 = new Parent(pupil.getParent1());
            parent2 = (pupil.getParent2() != null) ? new Parent(pupil.getParent2()) : null;
            marks = (pupil.getMarks() != null) ? new Marks(pupil.getMarks()) : null;
            promotionToNextGrade = pupil.isPromotionToNextGrade();
            awardBar = pupil.isAwardBar();
            achievement = pupil.getAchievement();
        }

        this.setLayout(new GridBagLayout());
        this.setFont(new Font(null, Font.BOLD, 20));

        setWindowCloseListener();
        setItemsToFrame();
        setFontForComponents(this, styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 19));
        setStyleForWindow(this);
        setListeners();

        this.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(10));
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write pupil's data");
        this.setVisible(true);


    }

    private void setTextFieldsStyle(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(11));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(6));
            }
            if (component instanceof Container) {
                setTextFieldsStyle((Container) component);
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

    private void setStyleForWindow(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton && !component.equals(addFirstParentButton)) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(5));
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
            }
            if (component instanceof JLabel || component instanceof JPanel ||
                    component instanceof JCheckBox) {
                component.setBackground(styleConstants.getACTUAL_SET_OF_COLORS().get(10));
            }
            if (component instanceof JLabel || component instanceof JTextField || component instanceof JComboBox) {
                component.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(6));
            }
            if (component instanceof Container) {
                setStyleForWindow((Container) component);
            }
        }
    }

    private void setItemsToFrame() {
        JLabel monthLabel;
        JLabel dateOfBirth;
        JLabel genderLabel;
        JLabel idLabel;
        JLabel peselLabel;
        JLabel secondNameLabel;
        JLabel nameLabel;
        JLabel yearLabel;
        JLabel dayLabel;
        JLabel addressLabel;
        JLabel countryLabel;
        JLabel provinceLabel;
        JLabel townLabel;
        JLabel localLabel;
        JLabel streetLabel;
        JLabel houseLabel;
        JLabel postCodeLabel;
        JLabel gradeLabel;
        JLabel surnameLabel;
        nameLabel = new JLabel("*Name: ");
        nameField = new JTextField();
        secondNameLabel = new JLabel("SecondName: ");
        secondNameField = new JTextField();
        surnameLabel = new JLabel("*Surname: ");
        surnameField = new JTextField();

        peselLabel = new JLabel("*Pesel: ");
        idLabel = new JLabel("*ID: ");
        genderLabel = new JLabel("*Gender: ");
        yearLabel = new JLabel("Year: ");
        monthLabel = new JLabel("Month: ");
        dayLabel = new JLabel("Day: ");
        countryLabel = new JLabel("Country: ");
        provinceLabel = new JLabel("Province: ");
        townLabel = new JLabel("Town: ");
        streetLabel = new JLabel("Street: ");
        houseLabel = new JLabel("House: ");
        localLabel = new JLabel("Local: ");
        postCodeLabel = new JLabel("Postcode: ");
        dateOfBirth = new JLabel("Date of birth ");
        addressLabel = new JLabel("Address ");
        gradeLabel = new JLabel("*Grade: ");

        peselField = new JTextField(13);
        idField = new JTextField(13);
        yearField = new JTextField(13);
        monthField = new JTextField(13);
        dayField = new JTextField(13);
        countryField = new JTextField(13);
        provinceField = new JTextField(13);
        townField = new JTextField(13);
        streetField = new JTextField(13);
        houseField = new JTextField(13);
        localField = new JTextField(13);
        postCodeField = new JTextField(13);

        genderComboBox = new JComboBox<>(new String[]{"", "Male", "Female"});
        gradeComboBox = new JComboBox<>(new Integer[]{null, 0, 1, 2, 3, 4, 5, 6, 7, 8});

        gradeComboBox.addActionListener(this);

        addFirstParentButton = new JButton("*First parent");
        addSecondParentButton = new JButton("Second parent");
        markButton = new JButton("Enter marks");
        addButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        achievementButton = new JButton("Add achievement");
        achievementButton.addActionListener(this);
        addFirstParentButton.setBackground((isNewPupil) ? styleConstants.getCOLOR_FOR_WRONG_FORMAT()
                : styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
        addFirstParentButton.setForeground(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
        markButton.addActionListener(this);

        markButton.setEnabled(pupil.getGrade() > 3);


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 3, 5, 3);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        this.add(nameLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        this.add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        this.add(secondNameLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        this.add(secondNameField, c);

        c.gridx = 0;
        c.gridy = 2;
        this.add(surnameLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        this.add(surnameField, c);

        c.insets = new Insets(5, 10, 5, 3);
        c.gridx = 2;
        c.gridy = 0;
        this.add(idLabel, c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        this.add(idField, c);

        c.gridx = 2;
        c.gridy = 1;
        this.add(peselLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        this.add(peselField, c);

        c.gridx = 2;
        c.gridy = 2;
        this.add(genderLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        this.add(genderComboBox, c);

        c.gridx = 2;
        c.gridy = 3;
        this.add(gradeLabel, c);

        c.gridx = 3;
        c.gridy = 3;
        this.add(gradeComboBox, c);

        c.insets = new Insets(2, 3, 2, 3);
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 4;
        this.add(new JSeparator(), c);

        c.insets = new Insets(5, 3, 5, 3);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 5;
        this.add(dateOfBirth, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(yearLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        this.add(yearField, c);

        c.gridx = 0;
        c.gridy = 7;
        this.add(monthLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        this.add(monthField, c);

        c.gridx = 0;
        c.gridy = 8;
        this.add(dayLabel, c);

        c.gridx = 1;
        c.gridy = 8;
        this.add(dayField, c);

        c.insets = new Insets(5, 10, 5, 3);
        c.gridx = 2;
        c.gridy = 5;
        this.add(addressLabel, c);

        c.gridx = 2;
        c.gridy = 6;
        this.add(countryLabel, c);

        c.gridx = 3;
        c.gridy = 6;
        this.add(countryField, c);

        c.gridx = 2;
        c.gridy = 7;
        this.add(provinceLabel, c);

        c.gridx = 3;
        c.gridy = 7;
        this.add(provinceField, c);

        c.gridx = 2;
        c.gridy = 8;
        this.add(townLabel, c);

        c.gridx = 3;
        c.gridy = 8;
        this.add(townField, c);

        c.gridx = 2;
        c.gridy = 9;
        this.add(streetLabel, c);

        c.gridx = 3;
        c.gridy = 9;
        this.add(streetField, c);

        c.gridx = 2;
        c.gridy = 10;
        this.add(houseLabel, c);

        c.gridx = 3;
        c.gridy = 10;
        this.add(houseField, c);

        c.gridx = 2;
        c.gridy = 11;
        this.add(localLabel, c);

        c.gridx = 3;
        c.gridy = 11;
        this.add(localField, c);

        c.gridx = 2;
        c.gridy = 12;
        this.add(postCodeLabel, c);

        c.gridx = 3;
        c.gridy = 12;
        this.add(postCodeField, c);

        c.insets = new Insets(2, 40, 2, 40);

        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 2;
        this.add(addFirstParentButton, c);

        c.gridx = 0;
        c.gridy = 10;
        this.add(addSecondParentButton, c);

        c.gridx = 0;
        c.gridy = 11;
        this.add(markButton, c);

        c.gridx = 0;
        c.gridy = 12;
        this.add(achievementButton, c);

        c.insets = new Insets(6, 10, 6, 10);
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 13;
        this.add(new JSeparator(), c);

        c.insets = new Insets(2, 40, 15, 10);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 14;
        this.add(addButton, c);

        c.insets = new Insets(2, 2, 15, 0);
        c.gridx = 2;
        c.gridy = 14;
        this.add(cancelButton, c);

        setTextFieldsStyle(this);
        setPupilDataInItems();
    }

    private void setPupilDataInItems() {
        if (!isNewPupil) {
            nameField.setText((pupil.getName() == null) ? "" : pupil.getName());
            secondNameField.setText((pupil.getSecondName() == null) ? "" : pupil.getSecondName());
            surnameField.setText((pupil.getSurname() == null) ? "" : pupil.getSurname());
            peselField.setText((pupil.getPesel() == null) ? "" : pupil.getPesel());
            idField.setText(Integer.toString(pupil.getId()));
            yearField.setText((pupil.getDateOfBirth() == null) ? "" : Integer.toString(pupil.getDateOfBirth().getYear()));
            monthField.setText((pupil.getDateOfBirth() == null) ? "" : Integer.toString(pupil.getDateOfBirth().getMonthValue()));
            dayField.setText((pupil.getDateOfBirth() == null) ? "" : Integer.toString(pupil.getDateOfBirth().getDayOfMonth()));
            countryField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getCountry());
            provinceField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getProvince());
            townField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getTown());
            streetField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getStreet());
            houseField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getHouse());
            localField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getLocal());
            postCodeField.setText((pupil.getAddress() == null) ? "" : pupil.getAddress().getPostCode());
            gradeComboBox.setSelectedIndex(pupil.getGrade() + 1);
            genderComboBox.setSelectedIndex((pupil.getGender() == 'M') ? 1 : (pupil.getGender() == 'F') ? 2 : 0);
            idField.setEnabled(false);

            checkIfDataAreRight();
        } else {

            nameField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            surnameField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            nameField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            peselField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            yearField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            monthField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            dayField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            genderComboBox.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            idField.setText(Integer.toString(dataList.getMinPossibleID()));
            idField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            gradeComboBox.setSelectedIndex(actualElements.getCurrentGrade() + 1);
            gradeComboBox.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
        }
    }

    private void checkIfDataAreRight() {

        if (pupil.getGender() == 'F' || pupil.getGender() == 'M') {
            genderComboBox.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
        } else {
            genderComboBox.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
        if (pupil.getDateOfBirth() != null) {
            checkDay();
            checkYear();
            checkMonth();
            checkDate();
        }
        checkPesel();

        nameField.setBackground((!nameField.getText().trim().equals(""))
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        surnameField.setBackground((!surnameField.getText().trim().equals(""))
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        gradeComboBox.setBackground((gradeComboBox.getSelectedIndex() > 0)
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        addFirstParentButton.setBackground((pupil.getParent1() != null)
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
    }

    private void checkPesel() {
        if (yearField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                monthField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                dayField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) {

            if (peselField.getText().length() == 11) {
                if (Objects.equals(yearField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                        Objects.equals(monthField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                        Objects.equals(dayField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                        !Objects.equals(genderComboBox.getSelectedItem(), "")) {
                    int yearNumber = Integer.parseInt(yearField.getText().trim());
                    int monthNumber = Integer.parseInt(monthField.getText().trim());
                    String endOfYear = String.format("%02d", Integer.parseInt(yearField.getText().trim()) % 100);
                    String month = String.format("%02d",getMonthForPesel(monthNumber, yearNumber));
                    String day = String.format("%02d", Integer.parseInt(dayField.getText().trim()));
                    String pesel = peselField.getText().trim();

                    if (pesel.substring(0, 2).equals(endOfYear) &&
                            pesel.substring(2, 4).equals(month) &&
                            pesel.substring(4, 6).equals(day) &&
                            (Objects.equals(genderComboBox.getSelectedItem(), "Male") && (
                                    pesel.charAt(9) == '1' ||
                                            pesel.charAt(9) == '3' ||
                                            pesel.charAt(9) == '5' ||
                                            pesel.charAt(9) == '7' ||
                                            pesel.charAt(9) == '9') ||
                                    Objects.equals(genderComboBox.getSelectedItem(), "Female") && (
                                            pesel.charAt(9) == '0' ||
                                                    pesel.charAt(9) == '2' ||
                                                    pesel.charAt(9) == '4' ||
                                                    pesel.charAt(9) == '6' ||
                                                    pesel.charAt(9) == '8')
                            )
                    ) {
                        peselField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
                    } else {
                        peselField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                    }
                }
            } else {
                peselField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        }
    }

    private int getMonthForPesel(int monthNumber, int yearNumber) {
        if (yearNumber < 2000) {
            return monthNumber;
        } else if (yearNumber < 2100) {
            return monthNumber + 20;
        } else if (yearNumber < 2200) {
            return monthNumber + 40;
        }
        return monthNumber + 60;
    }
    private void checkDate() {
        try {
            LocalDate.of(Integer.parseInt(yearField.getText().trim()),
                    Integer.parseInt(monthField.getText().trim()),
                    Integer.parseInt(dayField.getText().trim()));
        } catch (DateTimeException e) {
            yearField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            monthField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            monthField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }

    private void checkMonth() {
        try {
            int number = Integer.parseInt(this.monthField.getText().trim());
            if (number < 13 && number > 0) {
                monthField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            } else {
                monthField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        } catch (NumberFormatException ex) {
            monthField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }

    private void checkYear() {
        try {
            int yearNumber = Integer.parseInt(this.yearField.getText().trim());
            if (yearNumber > LocalDate.now().getYear() - 20 && yearNumber <= LocalDate.now().getYear() - 5) {
                yearField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            } else {
                yearField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        } catch (NumberFormatException ex) {
            yearField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }

    private void checkDay() {
        try {
            int number = Integer.parseInt(dayField.getText().trim());
            if (number < 32 && number > 0) {
                dayField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            } else {
                dayField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        } catch (NumberFormatException ex) {
            dayField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }

    private void setFontForComponents(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JTextField || component instanceof JButton
                    || component instanceof JCheckBox || component instanceof JComboBox) {
                component.setFont(font);
            }
            if (component instanceof Container) {
                setFontForComponents((Container) component, font);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == achievementButton) {
            addAcievementMethod();
        }
        if (e.getSource() == markButton) {
            addMarkMethod();

        }
        if (e.getSource() == cancelButton) {
            addCancelMethod();
        }
        if (e.getSource() == addFirstParentButton) {
            addFirstParentMethod();
        }
        if (e.getSource() == addSecondParentButton) {
            addSecondParentMethod();
        }
        if (e.getSource() == addButton) {
            addButtonMethod();
        }
    }

    private void addButtonMethod() {
        if (nameField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                surnameField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                yearField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                dayField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                monthField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                peselField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                genderComboBox.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                gradeComboBox.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                addFirstParentButton.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                houseField.getBackground() != styleConstants.getCOLOR_FOR_WRONG_FORMAT() &&
                localField.getBackground() != styleConstants.getCOLOR_FOR_WRONG_FORMAT() &&
                (idField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() ||
                        !idField.isEnabled())) {

            if (isNewPupil) {
                addNewPupilDataMethod();

            } else {
                addPupilDataMethod();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "\t\tI can't write this pupil!\nSome data was entered incorrectly", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPupilDataMethod() {
        String secondName = (secondNameField.getText().trim().equals(""))
                ? null : secondNameField.getText().trim();
        char gender = (Objects.equals(genderComboBox.getSelectedItem(), "Male")) ? 'M' : 'F';
        pupil.setName(nameField.getText().trim());
        pupil.setSecondName(secondName);
        pupil.setSurname(surnameField.getText().trim());
        pupil.setGender(gender);
        pupil.setDateOfBirth(LocalDate.of(Integer.parseInt(yearField.getText().trim()),
                Integer.parseInt(monthField.getText().trim()),
                Integer.parseInt(dayField.getText().trim())));
        pupil.setPesel(peselField.getText().trim());
        pupil.setId(Integer.parseInt(idField.getText().trim()));
        pupil.setAddress(new Address(
                countryField.getText().trim(),
                provinceField.getText().trim(),
                townField.getText().trim(),
                streetField.getText().trim(),
                houseField.getText().trim(),
                localField.getText().trim(),
                postCodeField.getText().trim()));
        pupil.setGrade(gradeComboBox.getSelectedIndex() - 1);
        pupil.setMarks((pupil.getGrade() < 4) ? null : marks);
        pupil.setPromotionToNextGrade(promotionToNextGrade);
        pupil.setAwardBar(awardBar);
        pupil.setAchievement(achievement);

        pupil.setParent1(parent1);
        pupil.setParent2(parent2);
        currentStatusField.setText(String.format("Changes are saved (%s)",
                actualElements.getCurrentPupil().getNamesAndSurname()));
        actualElements.setCurrentGrade(pupil.getGrade());

        dispose();
    }

    private void addNewPupilDataMethod() {
        try {
            String secondName = (secondNameField.getText().trim().equals(""))
                    ? null : secondNameField.getText().trim();
            char gender = (Objects.equals(genderComboBox.getSelectedItem(), "Male")) ? 'M' : 'F';
            pupil.setName(nameField.getText().trim());
            pupil.setSecondName(secondName);
            pupil.setSurname(surnameField.getText().trim());
            pupil.setGender(gender);
            pupil.setDateOfBirth(LocalDate.of(Integer.parseInt(yearField.getText().trim()),
                    Integer.parseInt(monthField.getText().trim()),
                    Integer.parseInt(dayField.getText().trim())));
            pupil.setPesel(peselField.getText().trim());
            pupil.setId(Integer.parseInt(idField.getText().trim()));
            pupil.setAddress(new Address(
                    countryField.getText().trim(),
                    provinceField.getText().trim(),
                    townField.getText().trim(),
                    streetField.getText().trim(),
                    houseField.getText().trim(),
                    localField.getText().trim(),
                    postCodeField.getText().trim()));
            pupil.setGrade(gradeComboBox.getSelectedIndex() - 1);
            pupil.setMarks((pupil.getGrade() < 4) ? null : marks);
            pupil.setPromotionToNextGrade(promotionToNextGrade);
            pupil.setAwardBar(awardBar);
            pupil.setAchievement(achievement);

            pupil.setParent1(parent1);
            pupil.setParent2(parent2);

            actualElements.setCurrentPupil(pupil);
            currentStatusField.setText(String.format("Changes are saved (%s)",
                    actualElements.getCurrentPupil().getNamesAndSurname()));
            actualElements.setCurrentGrade(pupil.getGrade());

            boolean isPupilAdded = dataList.addPupilToList(pupil);
            if (Boolean.TRUE.equals(isPupilAdded)) {
                JOptionPane.showMessageDialog(null, "Pupil is added to database :)",
                        "Success!", JOptionPane.PLAIN_MESSAGE);
                currentStatusField.setText("Pupil is added to database");
                actualElements.setCurrentGrade(pupil.getGrade());
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                        "\t\tI can't write this pupil!\nSome data was entered incorrectly", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                    "\t\tI can't write this pupil!\nSome data was entered incorrectly", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSecondParentMethod() {
        new AddEditParent(parentFrame, parent2,
                countryField.getText(),
                provinceField.getText(),
                townField.getText(),
                streetField.getText(),
                houseField.getText().trim(),
                localField.getText().trim(),
                postCodeField.getText(),
                currentStatusField,
                isNewPupil,
                styleConstants,
                actualElements);
    }

    private void addFirstParentMethod() {
        Parent temporaryParent = new Parent();
        new AddEditParent(parentFrame,
                (parent1 == null) ? temporaryParent : parent1,
                countryField.getText(),
                provinceField.getText(),
                townField.getText(),
                streetField.getText(),
                houseField.getText().trim(),
                localField.getText().trim(),
                postCodeField.getText(),
                currentStatusField,
                isNewPupil,
                styleConstants,
                actualElements);
        if (temporaryParent.getName() != null) {
            parent1 = temporaryParent;
        }
        addFirstParentButton.setBackground((parent1 != null)
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
    }

    private void addCancelMethod() {
        String[] responses = {"Close without saving", "Return to editing"};
        int answer = JOptionPane.showOptionDialog(parentFrame, "Would you like to exit? \n Changes won't be saved",
                "Are you sure?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                responses, responses[0]);
        if (answer == 0) {
            dispose();
        }
    }

    private void addMarkMethod() {
        AddEditMarks addEditMarks;
        addEditMarks = new AddEditMarks(parentFrame, marks, awardBar,
                promotionToNextGrade, gradeComboBox.getSelectedIndex() - 1, currentStatusField, isNewPupil,
                styleConstants, actualElements);
        marks = addEditMarks.showDialogAndGetInput();
        if (marks != null) {
            promotionToNextGrade = marks.getPromotion(pupil.getGrade());
            awardBar = marks.isAwardBar(promotionToNextGrade, gradeComboBox.getSelectedIndex() - 1);
        }
    }

    private void addAcievementMethod() {
        AddEditAchievement addEditAchievement = new AddEditAchievement(parentFrame, achievement, currentStatusField,
                styleConstants, actualElements);
        achievement = addEditAchievement.showDialogAndGetInput();
    }

    private void setListeners() {
        addFirstParentButton.addActionListener(this);
        addSecondParentButton.addActionListener(this);
        cancelButton.addActionListener(this);
        addButton.addActionListener(this);
        houseField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(houseField, styleConstants));
        localField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(localField, styleConstants));
        nameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(nameField, styleConstants));
        surnameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(surnameField, styleConstants));
        yearField.getDocument().addDocumentListener(new IsRightYearDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox, styleConstants));
        monthField.getDocument().addDocumentListener(new IsRightMonthDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox, styleConstants));
        dayField.getDocument().addDocumentListener(new IsRightDayDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox, styleConstants));
        peselField.getDocument().addDocumentListener(new IsRightPeselDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox, styleConstants));
        genderComboBox.addActionListener(new GenderComboBoxListener(yearField, monthField, dayField,
                peselField, genderComboBox, styleConstants));
        gradeComboBox.addActionListener(new GradeComboBoxListener(gradeComboBox, markButton, styleConstants));
        if (isNewPupil) {
            idField.getDocument().addDocumentListener(new IsRightIDDocumentListener(idField, dataList,
                    styleConstants));
        }
    }
}

