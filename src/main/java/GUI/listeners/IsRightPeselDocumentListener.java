package GUI.listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

public class IsRightPeselDocumentListener implements DocumentListener {
    JTextField yearField;
    JTextField monthField;
    JTextField dayField;
    JTextField thisJField;
    JComboBox<String> comboBox;

    public IsRightPeselDocumentListener(JTextField yearField, JTextField monthField, JTextField dayField,
                                        JTextField thisJField, JComboBox<String> comboBox) {
        this.yearField = yearField;
        this.monthField = monthField;
        this.dayField = dayField;
        this.thisJField = thisJField;
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

    private void isRight() {
        boolean isNumber = true;
        for (char c : thisJField.getText().toCharArray()) {
            if (!Character.isDigit(c)) {
                isNumber = false;
            }
        }
        if (thisJField.getText().trim().isEmpty()) {
            thisJField.setBackground(new Color(0xFFFFFF));
        } else if (!isNumber||thisJField.getText().length()>11) {
            thisJField.setBackground(new Color(0xEAD1DC));
        }else if (thisJField.getText().length() < 11) {
            thisJField.setBackground(new Color(0xFFFFFF));
        }
        if (thisJField.getText().length() == 11) {
            if (Objects.equals(yearField.getBackground(), new Color(0xD2FFD2)) &&
                    Objects.equals(yearField.getBackground(), new Color(0xD2FFD2)) &&
                    Objects.equals(monthField.getBackground(), new Color(0xD2FFD2)) &&
                    Objects.equals(dayField.getBackground(), new Color(0xD2FFD2)) &&
                    thisJField.getText().trim().length() == 11) {
                int yearNumber = Integer.parseInt(yearField.getText().trim());
                int monthNumber = Integer.parseInt(monthField.getText().trim());
                String endOfYear = String.format("%02d", Integer.parseInt(yearField.getText().trim())%100);
                String month = Integer.toString((yearNumber < 2000) ? monthNumber :
                        (yearNumber < 2100) ? monthNumber + 20 :
                                (yearNumber < 2200) ? monthNumber + 40 : monthNumber + 60);
                String day = String.format("%02d", Integer.parseInt(dayField.getText().trim()));
                String pesel = thisJField.getText().trim();

                System.out.println(pesel.substring(0, 2));
                System.out.println(pesel.substring(2, 4));
                System.out.println(pesel.substring(4, 6));

                System.out.println("\n");

                System.out.println(endOfYear);
                System.out.println(month);
                System.out.println(day);


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
                    thisJField.setBackground(new Color(0xD2FFD2));
                } else {
                    thisJField.setBackground(new Color(0xEAD1DC));
                }
            }
        }
    }

}
