package GUI.addPupil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPupil extends JFrame implements ActionListener {
    JLabel nameLabel, surnameLabel, secondNameLabel, peselLabel, idLabel, genderLabel, dateOfBirth, yearLabel, monthLabel, dayLabel,
            parent1Label, parent2Label, addressLabel, countryLabel, provinceLabel, townLabel, streetLabel, houseLabel,
            localLabel, postCodeLabel, marksLabel, achievementLabel;
    JTextField nameField,surnameField,secondNameField, peselField, idField, genderField, yearField, monthField, dayField,
            parent1Field, parent2Field, addressField, countryField, provinceField, townField, streetField, houseField,
            localField, postCodeField, marksField, achievementField;
    JComboBox<String> genderComboBox;

    JButton motherDataButton, fatherDataButton, markButton, addButton, cancelButton;



    public AddPupil() {
        this.setLayout(new GridBagLayout());
        this.setFont(new Font(null, Font.BOLD, 20));

        setItemsToFrame();
        setFontForComponents(this, new Font("Arial", Font.PLAIN, 19));
        setStyleForWindow();
        setActionListener();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Write pupil's data");
        this.setVisible(true);

    }

    private void setActionListener() {
        motherDataButton.addActionListener(this);
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
        dateOfBirth = new JLabel("Date of birth: ");
        addressLabel = new JLabel("Address: ");

        peselField = new JTextField(13);
        idField = new JTextField(13);
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

        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});

        motherDataButton  =new JButton("Enter mother's data");
        fatherDataButton = new JButton("Enter father's data");
        markButton = new JButton("Enter marks");
        addButton = new JButton("Add pupil");
        cancelButton = new JButton("Cancel");


        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx=0;
        c.gridy=0;
        this.add(nameLabel, c);

        c.gridx=1;
        c.gridy=0;
        c.weightx = 1;
        this.add(nameField, c);

        c.gridx=0;
        c.gridy=1;
        this.add(secondNameLabel, c);

        c.gridx=1;
        c.gridy=1;
        this.add(secondNameField, c);

        c.gridx=0;
        c.gridy=2;
        this.add(surnameLabel, c);

        c.gridx=1;
        c.gridy=2;
        this.add(surnameField, c);

        c.gridx=2;
        c.gridy=0;
        this.add(idLabel, c);

        c.gridx=3;
        c.gridy=0;
        c.gridwidth =1;
        this.add(idField, c);

        c.gridx=2;
        c.gridy=1;
        this.add(peselLabel, c);

        c.gridx=3;
        c.gridy=1;
        this.add(peselField, c);

        c.gridx=2;
        c.gridy=2;
        this.add(genderLabel, c);

        c.gridx=3;
        c.gridy=2;
        this.add(genderComboBox, c);

        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        this.add(new JSeparator(), c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        this.add(dateOfBirth, c);

        c.gridx=0;
        c.gridy=5;
        this.add(yearLabel, c);

        c.gridx=1;
        c.gridy=5;
        this.add(yearField, c);

        c.gridx=0;
        c.gridy=6;
        this.add(monthLabel, c);

        c.gridx=1;
        c.gridy=6;
        this.add(monthField, c);

        c.gridx=0;
        c.gridy=7;
        this.add(dayLabel, c);

        c.gridx=1;
        c.gridy=7;
        this.add(dayField, c);

        c.gridx=2;
        c.gridy=4;
        this.add(addressLabel, c);

        c.gridx=2;
        c.gridy=5;
        this.add(countryLabel, c);

        c.gridx=3;
        c.gridy=5;
        this.add(countryField, c);

        c.gridx=2;
        c.gridy=6;
        this.add(provinceLabel, c);

        c.gridx=3;
        c.gridy=6;
        this.add(provinceField, c);

        c.gridx=2;
        c.gridy=7;
        this.add(townLabel, c);

        c.gridx=3;
        c.gridy=7;
        this.add(townField, c);

        c.gridx=2;
        c.gridy=8;
        this.add(streetLabel, c);

        c.gridx=3;
        c.gridy=8;
        this.add(streetField, c);

        c.gridx=2;
        c.gridy=9;
        this.add(houseLabel, c);

        c.gridx=3;
        c.gridy=9;
        this.add(houseField, c);

        c.gridx=2;
        c.gridy=10;
        this.add(localLabel, c);

        c.gridx=3;
        c.gridy=10;
        this.add(localField, c);

        c.gridx=2;
        c.gridy=11;
        this.add(postCodeLabel, c);

        c.gridx=3;
        c.gridy=11;
        this.add(postCodeField, c);

        c.insets = new Insets(2,40,2,40);
        c.gridwidth = 2;
        c.gridx=0;
        c.gridy=8;
        this.add(motherDataButton, c);

        c.gridx=0;
        c.gridy=9;
        this.add(fatherDataButton, c);

        c.gridx=0;
        c.gridy=10;
        this.add(markButton, c);

        c.insets = new Insets(2,40,15,10);
        c.gridwidth = 1;
        c.gridx=1;
        c.gridy=12;
        this.add(addButton, c);

        c.insets = new Insets(2,2,15,0);
        c.gridx=2;
        c.gridy=12;
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
        if (e.getSource() == motherDataButton) {
            motherDataButton.setBackground(Color.CYAN);
        }
    }
}
