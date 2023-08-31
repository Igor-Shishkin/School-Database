package school.database.gui.listeners;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class IsRightYearDocumentListener implements DocumentListener {
    private final JTextField yearTextField, monthTextField, dayTextField, peselTextField;
    private final JComboBox<String> comboBox;
    private final ConstantsOfStyle styleConstants;

    public IsRightYearDocumentListener(JTextField yearTextField, JTextField monthTextField, JTextField dayTextField,
                                       JTextField peselTextField, JComboBox<String> comboBox,
                                       ConstantsOfStyle styleConstants) {
        this.yearTextField = yearTextField;
        this.monthTextField = monthTextField;
        this.dayTextField = dayTextField;
        this.peselTextField = peselTextField;
        this.comboBox = comboBox;
        this.styleConstants = styleConstants;
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
        isYearRight();
        isDateRight();
        isPeselRight();

    }

    private void isYearRight() {
        if (yearTextField.getText().trim().isEmpty()) {
            yearTextField.setBackground(styleConstants.getCOLOR_NEUTRAL_FORMAT());
        } else {
            try {
                int yearNumber = Integer.parseInt(this.yearTextField.getText().trim());
                if (yearNumber > LocalDate.now().getYear()-20 && yearNumber <= LocalDate.now().getYear() - 5) {
                    yearTextField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
                } else {
                    yearTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                }
            } catch (NumberFormatException ex) {
                yearTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        }
    }

    private void isPeselRight() {
        if (peselTextField.getText().length() == 11) {
            if (Objects.equals(yearTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                    Objects.equals(yearTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                    Objects.equals(monthTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                    Objects.equals(dayTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                    !Objects.equals(comboBox.getSelectedItem(), "")) {
                int yearNumber = Integer.parseInt(yearTextField.getText().trim());
                int monthNumber = Integer.parseInt(monthTextField.getText().trim());
                String endOfYear = String.format("%02d", Integer.parseInt(yearTextField.getText().trim())%100);
                String month = String.format("%02d",getMonthForPesel(monthNumber, yearNumber));
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
                    peselTextField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
                } else {
                    peselTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                }
            } else {
                peselTextField.setBackground(styleConstants.getCOLOR_NEUTRAL_FORMAT());
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
    private void isDateRight() {
        if (yearTextField.getBackground()==styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                monthTextField.getBackground()==styleConstants.getCOLOR_FOR_RIGHT_FORMAT() &&
                dayTextField.getBackground()==styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) {
            try {
                LocalDate.of(Integer.parseInt(yearTextField.getText().trim()),
                        Integer.parseInt(monthTextField.getText().trim()),
                        Integer.parseInt(dayTextField.getText().trim()));
            } catch (DateTimeException e) {
                yearTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                monthTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                dayTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        }
    }
}
