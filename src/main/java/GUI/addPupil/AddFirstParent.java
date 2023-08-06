package GUI.addPupil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddFirstParent extends JFrame implements ActionListener {
    JLabel nameLabel, surnameLabel, secondNameLabel, eMailLabel, telephoneLabel, genderLabel, dateOfBirth, yearLabel,
            monthLabel, dayLabel, addressLabel, countryLabel, provinceLabel, townLabel,
            streetLabel, houseLabel, localLabel, postCodeLabel;
    JTextField nameField, surnameField, secondNameField, eMailField, telephoneField, genderField, yearField, monthField, dayField,
            countryField, provinceField, townField, streetField, houseField,
            localField, postCodeField;
    JComboBox<String> genderComboBox;

    JButton makeAddressLikePupils, fatherDataButton, markButton, addButton, cancelButton;

    Font remRegular;
    Font font;

    String country, province, town, street, postCode,house, local;


    public AddFirstParent(String country, String province, String town, String street, String house, String local,
                          String postCode) throws IOException, FontFormatException {
        this.country = country;
        this.province = province;
        this.town = town;
        this.street = street;
        this.house = house;
        this.local = local;
        this.postCode = postCode;
        Path workDir = Paths.get("src", "main", "resources");
        File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
        remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        font = remRegular.deriveFont(Font.PLAIN, 19);

        this.setLayout(new GridBagLayout());
        this.setFont(font);

        setItemsToFrame();
        setFontForComponents(this, font);
        setStyleForWindow();
        setActionListener();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write pupil's data");
        this.setVisible(true);

    }

    private void setActionListener() {
        makeAddressLikePupils.addActionListener(this);
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

        eMailLabel = new JLabel("*Pesel: ");
        telephoneLabel = new JLabel("*ID: ");
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
        dateOfBirth = new JLabel("Date of birth: ");
        addressLabel = new JLabel("Address: ");

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

        genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});

        makeAddressLikePupils = new JButton("Make an address like the pupil's");
        fatherDataButton = new JButton("Enter father's data");
        markButton = new JButton("Enter marks");
        addButton = new JButton("Add pupil");
        cancelButton = new JButton("Cancel");


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
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
        this.add(makeAddressLikePupils, c);

        c.gridx = 0;
        c.gridy = 9;
        this.add(fatherDataButton, c);

        c.gridx = 0;
        c.gridy = 10;
        this.add(markButton, c);

        c.insets = new Insets(2, 40, 15, 10);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 12;
        this.add(addButton, c);

        c.insets = new Insets(2, 2, 15, 0);
        c.gridx = 2;
        c.gridy = 12;
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
        if(e.getSource()==makeAddressLikePupils) {
            countryField.setText(country);
            provinceField.setText(province);
            townField.setText(town);
            streetField.setText(street);
            houseField.setText(house);
            localField.setText(local);
            postCodeField.setText(postCode);
        }
    }
}
