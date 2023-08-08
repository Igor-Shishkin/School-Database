package GUI.listeners;

import GUI.styleStorage.ConstantsOfColors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GenderComboBoxListener implements ActionListener {
    JTextField yearTextField, monthTextField, dayTextField, peselTextField;
    JComboBox<String> comboBox;

    public GenderComboBoxListener(JTextField yearTextField, JTextField monthTextField, JTextField dayTextField,
                                  JTextField peselTextField, JComboBox<String> comboBox) {
        this.yearTextField = yearTextField;
        this.monthTextField = monthTextField;
        this.dayTextField = dayTextField;
        this.peselTextField = peselTextField;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(comboBox.getSelectedItem(), "")){
            comboBox.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
        } else {
            comboBox.setBackground(ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT);
        }
        boolean isNumber = true;
        for (char c : peselTextField.getText().toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
            }
        }
        if (peselTextField.getText().trim().isEmpty()) {
            peselTextField.setBackground(ConstantsOfColors.COLOR_NEUTRAL_FORMAT);
        } else if (!isNumber|| peselTextField.getText().length()>11) {
            peselTextField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
        }else if (peselTextField.getText().length() < 11) {
            peselTextField.setBackground(ConstantsOfColors.COLOR_NEUTRAL_FORMAT);
        }
        if (peselTextField.getText().length() == 11) {
            if (Objects.equals(yearTextField.getBackground(), ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(yearTextField.getBackground(), ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(monthTextField.getBackground(), ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(dayTextField.getBackground(), ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT) &&
                    !Objects.equals(comboBox.getSelectedItem(), "")) {
                int yearNumber = Integer.parseInt(yearTextField.getText().trim());
                int monthNumber = Integer.parseInt(monthTextField.getText().trim());
                String endOfYear = String.format("%02d", Integer.parseInt(yearTextField.getText().trim())%100);
                String month = Integer.toString((yearNumber < 2000) ? monthNumber :
                        (yearNumber < 2100) ? monthNumber + 20 :
                                (yearNumber < 2200) ? monthNumber + 40 : monthNumber + 60);
                String day = String.format("%02d", Integer.parseInt(dayTextField.getText().trim()));
                String pesel = peselTextField.getText().trim();

                if (pesel.substring(0, 2).equals(endOfYear) &&
                        pesel.substring(2, 4).equals(month) &&
                        pesel.substring(4, 6).equals(day) &&
                        (Objects.equals(comboBox.getSelectedItem(), "Male") && (
                                pesel.charAt(9) == '1' ||
                                        pesel.charAt(9) == '3' ||
                                        pesel.charAt(9) == '5' ||
                                        pesel.charAt(9) == '7' ||
                                        pesel.charAt(9) == '9') ||
                                Objects.equals(comboBox.getSelectedItem(), "Female") && (
                                        pesel.charAt(9) == '0' ||
                                                pesel.charAt(9) == '2' ||
                                                pesel.charAt(9) == '4' ||
                                                pesel.charAt(9) == '6' ||
                                                pesel.charAt(9) == '8')
                        )
                ) {
                    peselTextField.setBackground(ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT);
                } else {
                    peselTextField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
                }
            }else {
                peselTextField.setBackground(ConstantsOfColors.COLOR_NEUTRAL_FORMAT);
            }
        }
    }
}