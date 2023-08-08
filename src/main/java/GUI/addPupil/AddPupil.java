package GUI.addPupil;

import GUI.listeners.*;
import GUI.styleStorage.ConstantsOfColors;
import database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class AddPupil extends JFrame implements ActionListener {
    Boolean NEW_PUPIL = false;
    Parent parent1 = null, parent2 = null;
    String achievement = "assax";
    boolean awardBar, promotionToNextGrade;
    Marks marks;
    JLabel nameLabel, surnameLabel, secondNameLabel, peselLabel, idLabel, genderLabel, dateOfBirth, yearLabel, monthLabel, dayLabel,
            parent1Label, parent2Label, addressLabel, countryLabel, provinceLabel, townLabel, streetLabel, houseLabel,
            localLabel, postCodeLabel, requiredLabel, achievementLabel, gradeLabel;
    JTextField nameField, surnameField, secondNameField, peselField, idField, genderField, yearField, monthField, dayField,
            parent1Field, parent2Field, addressField, countryField, provinceField, townField, streetField, houseField,
            localField, postCodeField, marksField, achievementField;
    JComboBox<String> genderComboBox;
    JComboBox<Integer> gradeComboBox;
    JButton addFirstParentButton, addSecondParentButton, markButton, addButton, cancelButton, achievementButton;
    Font font;
    Pupil pupil;


    public AddPupil(Pupil p) throws IOException, FontFormatException {
        this.pupil = p;
        if (pupil==null) {
            pupil=new Pupil();
            pupil.setId(PupilsDataList.getMinPossibleID());
            NEW_PUPIL = true;
        }

        this.setLayout(new GridBagLayout());
        this.setFont(new Font(null, Font.BOLD, 20));

        font = ConstantsOfColors.THE_MAIN_FONT.deriveFont(Font.PLAIN, 19);

        setWindowCloseListener();
        setItemsToFrame();
        setFontForComponents(this, font);
        setStyleForWindow();
        setListeners();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write pupil's data");
        this.setVisible(true);

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

    private void setStyleForWindow() {
        //this.setBackground(MainWindow.actualSetColor.get(1));

    }

    private void setItemsToFrame() {
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

        peselField.setText((pupil.getPesel()==null)?"":pupil.getPesel());
        idField.setText(Integer.toString(pupil.getId()));
        yearField.setText((pupil.getDateOfBirth()==null)?"":Integer.toString(pupil.getDateOfBirth().getYear()));
        monthField.setText((pupil.getDateOfBirth()==null)?"":Integer.toString(pupil.getDateOfBirth().getMonthValue()));
        dayField.setText((pupil.getDateOfBirth()==null)?"":Integer.toString(pupil.getDateOfBirth().getDayOfMonth()));
        countryField.setText((pupil.getAddress()==null)?"":pupil.getAddress().getCountry());
        provinceField.setText((pupil.getAddress()==null)?"":pupil.getAddress().getProvince());
        townField.setText((pupil.getAddress()==null)?"":pupil.getAddress().getTown());
        streetField.setText((pupil.getAddress()==null)?"":pupil.getAddress().getStreet());
        houseField.setText((pupil.getAddress()==null)?"":Integer.toString(pupil.getAddress().getHouse()));
        localField.setText((pupil.getAddress()==null)?"":Integer.toString(pupil.getAddress().getLocal()));
        postCodeField.setText((pupil.getAddress()==null)?"":pupil.getAddress().getPostCode());

        genderComboBox = new JComboBox<>(new String[]{"", "Male", "Female"});
        gradeComboBox = new JComboBox<>(new Integer[]{null, 0, 1, 2, 3, 4, 5, 6, 7, 8});
        gradeComboBox.addActionListener(this);

        if (NEW_PUPIL) {
            nameField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            surnameField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            nameField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            peselField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            yearField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            monthField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            dayField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            genderComboBox.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
            gradeComboBox.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
        } else {
            gradeComboBox.setSelectedItem(pupil.getGrade());
            genderComboBox.setSelectedItem((pupil.getGender()=='M')?genderComboBox.getItemAt(2)
                    :genderComboBox.getItemAt(3));
        }

        addFirstParentButton = new JButton("*Enter parent's data");
        addSecondParentButton = new JButton("Enter father's data");
        markButton = new JButton("Enter marks");
        addButton = new JButton("Add pupil");
        cancelButton = new JButton("Cancel");
        achievementButton = new JButton("Add achievement");
        achievementButton.addActionListener(this);
        addFirstParentButton.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
        markButton.addActionListener(this);


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
    }

    private static void setFontForComponents(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JTextField || component instanceof JButton) {
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
            AddAchievement addAchievement = new AddAchievement(this, pupil.getAchievement());
            pupil.setAchievement(addAchievement.showDialogAndGetInput());
            System.out.println(pupil.getAchievement());
        }
        if (e.getSource() == cancelButton) {
            System.exit(1);
        }
        if (e.getSource() == addFirstParentButton) {
            try {
                new AddFirstParent(this, countryField.getText(), provinceField.getText(), townField.getText(),
                        streetField.getText(),
                        houseField.getText().trim(),
                        localField.getText().trim(),
                        postCodeField.getText());
//                System.out.println(countryField.getText() + provinceField.getText() + townField.getText() +
//                        streetField.getText() +
//                        postCodeField.getText());
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == addButton) {
            if (NEW_PUPIL) {
                String secondName = (secondNameField.getText().trim().equals("")) ? null : secondNameField.getText().trim();
                char gender = (Objects.equals(genderComboBox.getSelectedItem(), "Male")) ? 'M' : 'F';
                try {
                    if (PupilsDataList.addPupilToList
                            (new Pupil(nameField.getText().trim(), secondName, surnameField.getText().trim(), gender,
                                    Integer.parseInt(yearField.getText().trim()), Integer.parseInt(monthField.getText().trim()),
                                    Integer.parseInt(dayField.getText().trim()), new Address(countryField.getText().trim(),
                                    provinceField.getText().trim(), townField.getText().trim(), streetField.getText().trim(),
                                    Integer.parseInt(houseField.getText().trim()), Integer.parseInt(localField.getText().trim()),
                                    postCodeField.getText().trim()), peselField.getText().trim(),
                                    Integer.parseInt(idField.getText().trim()),
                                    Integer.parseInt((String) Objects.requireNonNull(gradeComboBox.getSelectedItem())),
                                    parent1, parent2, achievement, marks, awardBar, promotionToNextGrade))) {
                        JOptionPane.showMessageDialog(null, "Pupil is added to database :)",
                                "Success!", JOptionPane.PLAIN_MESSAGE);
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
            } else {

            }
        }
        if (e.getSource() == markButton) {
            new AddMarks(this, pupil.getMarks(), false, false, 8);
        }
        if (e.getSource() == addSecondParentButton) {
//            int a = 10+ Integer.toString(gradeComboBox.getSelectedItem());
            System.out.println(gradeComboBox.getSelectedItem());
        }
    }

    private void setListeners() {
        addFirstParentButton.addActionListener(this);
        addSecondParentButton.addActionListener(this);
        cancelButton.addActionListener(this);
        addButton.addActionListener(this);
        houseField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(houseField));
        localField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(localField));
        nameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(nameField));
        surnameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(surnameField));
        yearField.getDocument().addDocumentListener(new IsRightYearDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        monthField.getDocument().addDocumentListener(new IsRightMonthDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        dayField.getDocument().addDocumentListener(new IsRightDayDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        peselField.getDocument().addDocumentListener(new IsRightPeselDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        genderComboBox.addActionListener(new GenderComboBoxListener(yearField, monthField, dayField,
                peselField, genderComboBox));

    }

    private boolean isInt(String number) {
        try {
            Integer.parseInt(number.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

