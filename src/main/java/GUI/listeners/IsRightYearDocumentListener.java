package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class IsRightYearDocumentListener implements DocumentListener {
    JTextField yearTextField, monthTextField, dayTextField, peselTextField;
    JComboBox<String> comboBox;

    public IsRightYearDocumentListener(JTextField yearTextField, JTextField monthTextField, JTextField dayTextField,
                                       JTextField peselTextField, JComboBox<String> comboBox) {
        this.yearTextField = yearTextField;
        this.monthTextField = monthTextField;
        this.dayTextField = dayTextField;
        this.peselTextField = peselTextField;
        this.comboBox = comboBox;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isRight();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isRight();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isRight();
    }
    void isRight () {
        if (yearTextField.getText().trim().isEmpty()) {
            yearTextField.setBackground(new Color(0xFFFFFF));
        } else {
            try {
                int yearNumber = Integer.parseInt(this.yearTextField.getText().trim());
                if (yearNumber>2000) {
                    if (yearNumber > LocalDate.now().getYear() - 20 && yearNumber <= LocalDate.now().getYear() - 5) {
                        yearTextField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                        String endOfYear = String.format("%02d", yearNumber%100);
                        String pesel = peselTextField.getText().trim();
                    } else {
                        yearTextField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                    }
                } else {
                    yearTextField.setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
                }
            } catch (NumberFormatException ex) {
                yearTextField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            }
        }
        if (yearTextField.getBackground()==ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                monthTextField.getBackground()==ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT &&
                dayTextField.getBackground()==ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) {
            try {
                LocalDate.of(Integer.parseInt(yearTextField.getText().trim()),
                        Integer.parseInt(monthTextField.getText().trim()),
                        Integer.parseInt(dayTextField.getText().trim()));
            } catch (DateTimeException e) {
                yearTextField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                monthTextField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                monthTextField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            }
        }

        boolean isNumber = true;
        for (char c : peselTextField.getText().toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
            }
        }
        if (peselTextField.getText().trim().isEmpty()) {
            peselTextField.setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
        } else if (!isNumber|| peselTextField.getText().length()>11) {
            peselTextField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
        }else if (peselTextField.getText().length() < 11) {
            peselTextField.setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
        }
        if (peselTextField.getText().length() == 11) {
            if (Objects.equals(yearTextField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(yearTextField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(monthTextField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
                    Objects.equals(dayTextField.getBackground(), ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT) &&
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
                    peselTextField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                } else {
                    peselTextField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            } else {
                peselTextField.setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
            }
        }
    }
}
