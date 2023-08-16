package GUI.addEditPupil;

import GUI.listeners.*;
import GUI.styleStorage.ConstantsOfStyle;
import database.Address;
import database.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class AddEditParent extends JDialog implements ActionListener {
    JFrame parentFrame;
    JLabel nameLabel, surnameLabel, secondNameLabel, eMailLabel, telephoneLabel, genderLabel, dateOfBirth, yearLabel,
            monthLabel, dayLabel, addressLabel, countryLabel, provinceLabel, townLabel,
            streetLabel, houseLabel, localLabel, postCodeLabel, peselJLabel;
    JTextField nameField, surnameField, secondNameField, eMailField, telephoneField, genderField, yearField, monthField, dayField,
            countryField, provinceField, townField, streetField, houseField, peselField,
            localField, postCodeField;
    JComboBox<String> genderComboBox;

    JButton addDataButton, cancelButton;

    Font remRegular;
    Font font;

    String country, province, town, street, postCode, house, local;
    JCheckBox setAddressCheckBox;
    Parent parent;
    boolean newParent = false;


    public AddEditParent(JFrame parentFrame, Parent parent, String country, String province, String town, String street, String house, String local,
                         String postCode) throws IOException, FontFormatException {
        super(parentFrame, "Parent's data", true); // Make it modal
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.parent = parent;
        this.country = country;
        this.province = province;
        this.town = town;
        this.street = street;
        this.house = house;
        this.local = local;
        this.postCode = postCode;
        this.parentFrame = parentFrame;

        if (parent == null) {
            newParent = true;
            parent = new Parent();
        }

        font = ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 19);

        this.setLayout(new GridBagLayout());
        this.setFont(font);

        setItemsToFrame();
        setFontForComponents(this, font);
        setStyleForWindow();

        setDateToJFields();
        setBackgroundForItems();

        setListener();


//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write parent's data");
        this.setVisible(true);

    }

    private void setBackgroundForItems() {
        if (newParent) {
            nameField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            surnameField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            telephoneField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            eMailField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            dayField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            yearField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            monthField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            genderComboBox.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            peselField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
        } else {
            nameField.setBackground((nameField.getText().trim().equals(""))
                    ? ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT : ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            surnameField.setBackground((surnameField.getText().trim().equals(""))
                    ? ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT : ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            telephoneField.setBackground((telephoneField.getText().trim().equals(""))
                    ? ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT : ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            eMailField.setBackground((eMailField.getText().trim().equals(""))
                    ? ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT : ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            genderComboBox.setBackground((genderComboBox.getSelectedIndex() < 1)
                    ? ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT : ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            try {
                int number = Integer.parseInt(dayField.getText().trim());
                if (number < 32 && number > 0) {
                    dayField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                } else {
                    dayField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            } catch (NumberFormatException ex) {
                dayField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            }

            try {
                int yearNumber = Integer.parseInt(this.yearField.getText().trim());
                if (yearNumber > LocalDate.now().getYear() - 100 && yearNumber <= LocalDate.now().getYear() - 18) {
                    yearField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                } else {
                    yearField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            } catch (NumberFormatException ex) {
                yearField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            }
            try {
                int number = Integer.parseInt(this.monthField.getText().trim());
                if (number < 13 && number > 0) {
                    monthField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                } else {
                    monthField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            } catch (NumberFormatException ex) {
                monthField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            }
            if (yearField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    monthField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    dayField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) {
                try {
                    LocalDate.of(Integer.parseInt(yearField.getText().trim()),
                            Integer.parseInt(monthField.getText().trim()),
                            Integer.parseInt(dayField.getText().trim()));
                } catch (DateTimeException e) {
                    yearField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                    monthField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                    dayField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            }
            if (peselField.getText().length() == 11 &&
                    Objects.equals(yearField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(yearField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(monthField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(dayField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    !Objects.equals(genderComboBox.getSelectedItem(), "")) {
                int yearNumber = Integer.parseInt(yearField.getText().trim());
                int monthNumber = Integer.parseInt(monthField.getText().trim());
                String endOfYear = String.format("%02d", Integer.parseInt(yearField.getText().trim()) % 100);
                String month = String.format("%02d", (yearNumber < 2000)
                        ? monthNumber :
                        (yearNumber < 2100) ? monthNumber + 20 :
                                (yearNumber < 2200) ? monthNumber + 40 : monthNumber + 60);
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
                    peselField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                } else {
                    peselField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            } else {
                peselField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            }
        }
    }

    private void setDateToJFields() {
        if (!newParent) {
            nameField.setText((parent.getName() != null) ? parent.getName() : "");
            secondNameField.setText((parent.getSecondName() != null) ? parent.getSecondName() : "");
            surnameField.setText((parent.getSurname() != null) ? parent.getSurname() : "");
            eMailField.setText((parent.geteMail() != null) ? parent.geteMail() : "");
            telephoneField.setText((parent.getTelephone() != null) ? parent.getTelephone() : "");
            yearField.setText((parent.getDateOfBirth() != null) ? Integer.toString(parent.getDateOfBirth().getYear()) : "");
            monthField.setText((parent.getDateOfBirth() != null) ? Integer.toString(parent.getDateOfBirth().getMonthValue()) : "");
            dayField.setText((parent.getDateOfBirth() != null) ? Integer.toString(parent.getDateOfBirth().getDayOfMonth()) : "");
            peselField.setText((parent.getPesel() != null) ? parent.getPesel() : "");
            genderComboBox.setSelectedIndex((parent.getGender() == 'M') ? 1 : (parent.getGender() == 'F') ? 2 : 0);
            if (parent.getAddress() != null) {
                countryField.setText((parent.getAddress().getCountry() != null) ? parent.getAddress().getCountry() : "");
                provinceField.setText((parent.getAddress().getProvince() != null) ? parent.getAddress().getProvince() : "");
                townField.setText((parent.getAddress().getTown() != null) ? parent.getAddress().getTown() : "");
                streetField.setText((parent.getAddress().getStreet() != null) ? parent.getAddress().getStreet() : "");
                houseField.setText(Integer.toString(parent.getAddress().getHouse()));
                localField.setText(Integer.toString(parent.getAddress().getLocal()));
                postCodeField.setText((parent.getAddress().getCountry() != null) ? parent.getAddress().getPostCode() : "");
            }
        }

    }

    private void setListener() {
        yearField.getDocument().addDocumentListener(new IsRightYearForParentDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        monthField.getDocument().addDocumentListener(new IsRightMonthDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        dayField.getDocument().addDocumentListener(new IsRightDayDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        peselField.getDocument().addDocumentListener(new IsRightPeselDocumentListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        genderComboBox.addActionListener(new GenderComboBoxListener(yearField, monthField, dayField,
                peselField, genderComboBox));
        houseField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(houseField));
        localField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(localField));
        nameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(nameField));
        surnameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(surnameField));
        setAddressCheckBox.addActionListener(this);
        eMailField.getDocument().addDocumentListener(new IsRightEMailDocumentListener(eMailField));
        telephoneField.getDocument().addDocumentListener(new IsRightPhoneNumberDocumentListener(telephoneField));
        addDataButton.addActionListener(this);
        cancelButton.addActionListener(this);
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

        eMailLabel = new JLabel("*eMail: ");
        telephoneLabel = new JLabel("*Telephone: ");
        genderLabel = new JLabel("*Gender: ");
        yearLabel = new JLabel("*Year: ");
        monthLabel = new JLabel("*Month: ");
        dayLabel = new JLabel("*Day: ");
        countryLabel = new JLabel("Country: ");
        provinceLabel = new JLabel("Province: ");
        townLabel = new JLabel("Town: ");
        streetLabel = new JLabel("Street: ");
        houseLabel = new JLabel("House: ");
        localLabel = new JLabel("Local: ");
        postCodeLabel = new JLabel("Postcode: ");
        dateOfBirth = new JLabel("Date of birth: ");
        addressLabel = new JLabel("Address: ");
        peselJLabel = new JLabel("*Pesel: ");

        yearLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        monthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        peselJLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        eMailField = new JTextField(13);
        telephoneField = new JTextField(13);
        genderField = new JTextField(13);
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
        peselField = new JTextField(13);

        genderComboBox = new JComboBox<>(new String[]{"", "Male", "Female"});

        setAddressCheckBox = new JCheckBox("Make an address like the pupil's");
        addDataButton = new JButton("Save");
//        addDataButton.setFont(remRegular.deriveFont(Font.BOLD, 19));
        addDataButton.setForeground(new Color(0x043100));
        cancelButton = new JButton("Cancel");

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

        c.gridx = 2;
        c.gridy = 0;
        this.add(telephoneLabel, c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        this.add(telephoneField, c);

        c.gridx = 2;
        c.gridy = 1;
        this.add(eMailLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        this.add(eMailField, c);

        c.gridx = 2;
        c.gridy = 2;
        this.add(genderLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        this.add(genderComboBox, c);

        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        this.add(new JSeparator(), c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        this.add(dateOfBirth, c);

        c.gridx = 0;
        c.gridy = 5;
        this.add(yearLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        this.add(yearField, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(monthLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        this.add(monthField, c);

        c.gridx = 0;
        c.gridy = 7;
        this.add(dayLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        this.add(dayField, c);

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        this.add(new JSeparator(), c);

        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        this.add(peselJLabel, c);

        c.gridx = 1;
        c.gridy = 9;
        this.add(peselField, c);

        c.gridx = 2;
        c.gridy = 4;
        this.add(addressLabel, c);

        c.gridx = 2;
        c.gridy = 5;
        this.add(countryLabel, c);

        c.gridx = 3;
        c.gridy = 5;
        this.add(countryField, c);

        c.gridx = 2;
        c.gridy = 6;
        this.add(provinceLabel, c);

        c.gridx = 3;
        c.gridy = 6;
        this.add(provinceField, c);

        c.gridx = 2;
        c.gridy = 7;
        this.add(townLabel, c);

        c.gridx = 3;
        c.gridy = 7;
        this.add(townField, c);

        c.gridx = 2;
        c.gridy = 8;
        this.add(streetLabel, c);

        c.gridx = 3;
        c.gridy = 8;
        this.add(streetField, c);

        c.gridx = 2;
        c.gridy = 9;
        this.add(houseLabel, c);

        c.gridx = 3;
        c.gridy = 9;
        this.add(houseField, c);

        c.gridx = 2;
        c.gridy = 10;
        this.add(localLabel, c);

        c.gridx = 3;
        c.gridy = 10;
        this.add(localField, c);

        c.gridx = 2;
        c.gridy = 11;
        this.add(postCodeLabel, c);

        c.gridx = 3;
        c.gridy = 11;
        this.add(postCodeField, c);

//        c.insets = new Insets(2, 3, 2, 3);
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 4;
        this.add(setAddressCheckBox, c);

        c.insets = new Insets(2, 55, 0, 40);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 10;
        this.add(addDataButton, c);

        c.insets = new Insets(2, 55, 5, 40);
        c.gridx = 0;
        c.gridy = 11;
        this.add(cancelButton, c);
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
        setAddressCheckBox.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDataButton) {
            if (nameField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    surnameField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    telephoneField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    eMailField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    genderComboBox.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    peselField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    yearField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    monthField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    dayField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                    nameField.getBackground() == ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT
            ) {
                String secondName = (secondNameField.getText().trim().equals("")) ? null : secondNameField.getText().trim();
                char gender = (Objects.equals(genderComboBox.getSelectedItem(), "Male")) ? 'M' : 'F';
                parent.setName(nameField.getText().trim());
                parent.setSecondName(secondName);
                parent.setSurname(surnameField.getText().trim());
                parent.setDateOfBirth(LocalDate.of(Integer.parseInt(yearField.getText().trim()),
                        Integer.parseInt(monthField.getText().trim()),
                        Integer.parseInt(dayField.getText().trim())));
                parent.setPesel(peselField.getText().trim());
                parent.setTelephone(telephoneField.getText().trim());
                parent.seteMail(eMailField.getText().trim());
                parent.setAddress(new Address(
                        countryField.getText().trim(),
                        provinceField.getText().trim(),
                        townField.getText().trim(),
                        streetField.getText().trim(),
                        Integer.parseInt(houseField.getText().trim()),
                        Integer.parseInt(localField.getText().trim()),
                        postCodeField.getText().trim()));
                dispose();
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Some data are wrong.", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
        if (e.getSource() == cancelButton) {
            String[] responses = {"Close without saving", "Return to editing"};
            int answer = JOptionPane.showOptionDialog(parentFrame,
                    "Would you like to exit? \n Changes won't be saved",
                    "Are you sure?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    responses, responses[0]);
            if (answer == 0) { dispose(); }
        }
        if (e.getSource() == setAddressCheckBox) {
            if (setAddressCheckBox.isSelected()) {
                countryField.setText(country);
                provinceField.setText(province);
                townField.setText(town);
                streetField.setText(street);
                houseField.setText(house);
                localField.setText(local);
                postCodeField.setText(postCode);

                countryField.setEnabled(false);
                provinceField.setEnabled(false);
                townField.setEnabled(false);
                streetField.setEnabled(false);
                houseField.setEnabled(false);
                localField.setEnabled(false);
                postCodeField.setEnabled(false);
            } else {
                countryField.setEnabled(true);
                provinceField.setEnabled(true);
                townField.setEnabled(true);
                streetField.setEnabled(true);
                houseField.setEnabled(true);
                localField.setEnabled(true);
                postCodeField.setEnabled(true);
            }
        }
    }
}
