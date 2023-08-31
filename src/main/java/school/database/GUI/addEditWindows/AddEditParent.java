package school.database.GUI.addEditWindows;

import school.database.GUI.ActualElements;
import school.database.GUI.CentralPanel;
import school.database.GUI.listeners.*;
//import school.database.GUI.listeners.*;
import school.database.GUI.styleStorage.ConstantsOfStyle;
import school.database.data.objects.Address;
import school.database.data.objects.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEditParent extends JDialog implements ActionListener {
    JFrame parentFrame;
    JLabel nameLabel, surnameLabel, secondNameLabel, eMailLabel, telephoneLabel, genderLabel, contactsLabel,
            addressLabel, countryLabel, provinceLabel, townLabel,
            streetLabel, houseLabel, localLabel, postCodeLabel, peselJLabel;
    JTextField nameField, surnameField, secondNameField, eMailField, telephoneField, genderField,
            countryField, provinceField, townField, streetField, houseField, peselField,
            localField, postCodeField, currentStatusField;
    JComboBox<String> genderComboBox;
    JButton addDataButton, cancelButton;
    Font font;

    String country, province, town, street, postCode, house, local;
    JCheckBox setAddressCheckBox;
    Parent parent;
    boolean newParent = false, isNewPupil;
    ConstantsOfStyle styleConstants;
    ActualElements actualElements;


    public AddEditParent(JFrame parentFrame, Parent parent, String country, String province, String town, String street,
                         String house, String local, String postCode, JTextField currentStatusField, boolean isNewPupil,
                         ConstantsOfStyle styleConstants, ActualElements actualElements)
            throws IOException, FontFormatException {
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
        this.currentStatusField = currentStatusField;
        this.isNewPupil = isNewPupil;
        this.styleConstants = styleConstants;
        this.actualElements = actualElements;

        if (this.parent == null) {
            newParent = true;
            this.parent = new Parent();
        }
        font = styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 19);

        this.setLayout(new GridBagLayout());
        this.setFont(font);

        setItemsToFrame();
        setFontForComponents(this, font);

        setDateToJFields();
        setBackgroundForItems();

        setListener();

        setStyleForWindow(this);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.red);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write parent's data");
        this.setVisible(true);

    }

    private void setBackgroundForItems() {
        setTextFieldsStyle(this);
        if (newParent) {
            nameField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            surnameField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            telephoneField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            eMailField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            genderComboBox.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            peselField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        } else {
            nameField.setBackground((nameField.getText().trim().equals(""))
                    ? styleConstants.getCOLOR_FOR_WRONG_FORMAT() : styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            surnameField.setBackground((surnameField.getText().trim().equals(""))
                    ? styleConstants.getCOLOR_FOR_WRONG_FORMAT() : styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            telephoneField.setBackground((telephoneField.getText().trim().equals(""))
                    ? styleConstants.getCOLOR_FOR_WRONG_FORMAT() : styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            eMailField.setBackground((eMailField.getText().trim().equals(""))
                    ? styleConstants.getCOLOR_FOR_WRONG_FORMAT() : styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
            genderComboBox.setBackground((genderComboBox.getSelectedIndex() < 1)
                    ? styleConstants.getCOLOR_FOR_WRONG_FORMAT() : styleConstants.getCOLOR_FOR_RIGHT_FORMAT());

            String regex = "";
            if (genderComboBox.getSelectedIndex() == 1) {
                regex = "^[0-9]{2}(2[1-9]|3[012]|0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{3}[13579]{1}[0-9]{1}$";
            } else {
                regex = "^[0-9]{2}(2[1-9]|3[012]|0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{3}[24680]{1}[0-9]{1}$";
            }
            Pattern peselPattern = Pattern.compile(regex);
            Matcher matcher = peselPattern.matcher(peselField.getText().trim());
            peselField.setBackground((matcher.matches())
                    ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }

    private void setDateToJFields() {
        if (!newParent) {
            nameField.setText((parent.getName() != null) ? parent.getName() : "");
            secondNameField.setText((parent.getSecondName() != null) ? parent.getSecondName() : "");
            surnameField.setText((parent.getSurname() != null) ? parent.getSurname() : "");
            eMailField.setText((parent.geteMail() != null) ? parent.geteMail() : "");
            telephoneField.setText((parent.getTelephone() != null) ? parent.getTelephone() : "");
            peselField.setText((parent.getPesel() != null) ? parent.getPesel() : "");
            genderComboBox.setSelectedIndex((parent.getGender() == 'M') ? 1 : (parent.getGender() == 'F') ? 2 : 0);
            if (parent.getAddress() != null) {
                countryField.setText((parent.getAddress().getCountry() != null) ? parent.getAddress().getCountry() : "");
                provinceField.setText((parent.getAddress().getProvince() != null) ? parent.getAddress().getProvince() : "");
                townField.setText((parent.getAddress().getTown() != null) ? parent.getAddress().getTown() : "");
                streetField.setText((parent.getAddress().getStreet() != null) ? parent.getAddress().getStreet() : "");
                houseField.setText(parent.getAddress().getHouse());
                localField.setText(parent.getAddress().getLocal());
                postCodeField.setText((parent.getAddress().getCountry() != null) ? parent.getAddress().getPostCode() : "");
            }
        }

    }

    private void setListener() {
        peselField.getDocument().addDocumentListener(new IsRightParentPeselDocumentListener(peselField, genderComboBox,
                styleConstants));
        genderComboBox.addActionListener(new GenderForParentComboBoxListener(peselField, genderComboBox,
                styleConstants));
        houseField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(houseField,
                styleConstants));
        localField.getDocument().addDocumentListener(new CheckStreetAndLocalDocumentListener(localField,
                styleConstants));
        nameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(nameField, styleConstants));
        surnameField.getDocument().addDocumentListener(new IsEmptyDocumentListener(surnameField, styleConstants));
        setAddressCheckBox.addActionListener(this);
        eMailField.getDocument().addDocumentListener(new IsRightEMailDocumentListener(eMailField, styleConstants));
        telephoneField.getDocument().addDocumentListener(new IsRightPhoneNumberDocumentListener(telephoneField,
                styleConstants));
        addDataButton.addActionListener(this);
        cancelButton.addActionListener(this);
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
        countryLabel = new JLabel("Country: ");
        provinceLabel = new JLabel("Province: ");
        townLabel = new JLabel("Town: ");
        streetLabel = new JLabel("Street: ");
        houseLabel = new JLabel("House: ");
        localLabel = new JLabel("Local: ");
        postCodeLabel = new JLabel("Postcode: ");
        contactsLabel = new JLabel("Contacts: ");
        addressLabel = new JLabel("Address: ");
        peselJLabel = new JLabel("*Pesel: ");

        peselJLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        eMailField = new JTextField(13);
        telephoneField = new JTextField(13);
        genderField = new JTextField(13);
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

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        this.add(new JSeparator(), c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        this.add(contactsLabel, c);

        c.gridx = 0;
        c.gridy = 5;
        this.add(telephoneLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        this.add(telephoneField, c);

        c.gridx = 0;
        c.gridy = 6;
        this.add(eMailLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        this.add(eMailField, c);

        c.gridx = 0;
        c.gridy = 7;
        this.add(genderLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        this.add(genderComboBox, c);

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
        c.gridy = 0;
        this.add(addressLabel, c);

        c.gridx = 2;
        c.gridy = 1;
        this.add(countryLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        this.add(countryField, c);

        c.gridx = 2;
        c.gridy = 2;
        this.add(provinceLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        this.add(provinceField, c);

        c.gridx = 2;
        c.gridy = 3;
        this.add(townLabel, c);

        c.gridx = 3;
        c.gridy = 3;
        this.add(townField, c);

        c.gridx = 2;
        c.gridy = 4;
        this.add(streetLabel, c);

        c.gridx = 3;
        c.gridy = 4;
        this.add(streetField, c);

        c.gridx = 2;
        c.gridy = 5;
        this.add(houseLabel, c);

        c.gridx = 3;
        c.gridy = 5;
        this.add(houseField, c);

        c.gridx = 2;
        c.gridy = 6;
        this.add(localLabel, c);

        c.gridx = 3;
        c.gridy = 6;
        this.add(localField, c);

        c.gridx = 2;
        c.gridy = 7;
        this.add(postCodeLabel, c);

        c.gridx = 3;
        c.gridy = 7;
        this.add(postCodeField, c);

//        c.insets = new Insets(2, 3, 2, 3);
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 0;
        this.add(setAddressCheckBox, c);

        c.insets = new Insets(2, 55, 0, 40);
        c.gridwidth = 2;
        c.gridx = 2;
        c.gridy = 8;
        this.add(addDataButton, c);

        c.insets = new Insets(2, 55, 5, 40);
        c.gridx = 2;
        c.gridy = 9;
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
        setAddressCheckBox.setFont(styleConstants.getTHE_MAIN_FONT().deriveFont(Font.PLAIN, 15));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDataButton) {
            if (nameField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    surnameField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    telephoneField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    eMailField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    genderComboBox.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    peselField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    nameField.getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                    localField.getBackground() != styleConstants.getCOLOR_FOR_WRONG_FORMAT() &&
                    houseField.getBackground() != styleConstants.getCOLOR_FOR_WRONG_FORMAT()
            ) {
                String secondName = (secondNameField.getText().trim().equals("")) ? null : secondNameField.getText().trim();
                char gender = (Objects.equals(genderComboBox.getSelectedItem(), "Male")) ? 'M' : 'F';
                parent.setName(nameField.getText().trim());
                parent.setSecondName(secondName);
                parent.setSurname(surnameField.getText().trim());
                parent.setGender(gender);
                parent.setPesel(peselField.getText().trim());
                parent.setTelephone(telephoneField.getText().trim());
                parent.seteMail(eMailField.getText().trim());
                parent.setAddress(new Address(
                        countryField.getText().trim(),
                        provinceField.getText().trim(),
                        townField.getText().trim(),
                        streetField.getText().trim(),
                        houseField.getText().trim(),
                        localField.getText().trim(),
                        postCodeField.getText().trim()));
                if (!isNewPupil) {
                    currentStatusField.setText(String.format("Changes are saved (%s: PARENT)",
                            actualElements.getCurrentPupil().getNamesAndSurname()));
                }
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
            if (answer == 0) {
                dispose();
            }
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

    private void setStyleForWindow(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
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
}
