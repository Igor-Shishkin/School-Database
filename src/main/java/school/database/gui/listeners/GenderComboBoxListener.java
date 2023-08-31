package school.database.gui.listeners;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GenderComboBoxListener implements ActionListener {
    private final JTextField yearTextField;
    private final JTextField monthTextField;
    private final JTextField dayTextField;
    private final JTextField peselTextField;
    private final JComboBox<String> genderComboBox;
    private final ConstantsOfStyle styleConstants;


    public GenderComboBoxListener(JTextField yearTextField, JTextField monthTextField, JTextField dayTextField,
                                  JTextField peselTextField, JComboBox<String> genderComboBox, 
                                  ConstantsOfStyle styleConstants) {
        this.yearTextField = yearTextField;
        this.monthTextField = monthTextField;
        this.dayTextField = dayTextField;
        this.peselTextField = peselTextField;
        this.genderComboBox = genderComboBox;
        this.styleConstants = styleConstants;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(genderComboBox.getSelectedItem(), "")){
            genderComboBox.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        } else {
            genderComboBox.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
        }

        if (peselTextField.getText().length() == 11) {
            if (Objects.equals(yearTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT())  &&
                    Objects.equals(monthTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                    Objects.equals(dayTextField.getBackground(), styleConstants.getCOLOR_FOR_RIGHT_FORMAT()) &&
                    !Objects.equals(genderComboBox.getSelectedItem(), "")) {
                int yearNumber = Integer.parseInt(yearTextField.getText().trim());
                int monthNumber = Integer.parseInt(monthTextField.getText().trim());
                String endOfYear = String.format("%02d", Integer.parseInt(yearTextField.getText().trim())%100);
                String month = String.format("%02d", getMonthForPesel(monthNumber, yearNumber));

                String day = String.format("%02d", Integer.parseInt(dayTextField.getText().trim()));
                String pesel = peselTextField.getText().trim();

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
                    peselTextField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
                } else {
                    peselTextField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                }
            }else {
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
}
