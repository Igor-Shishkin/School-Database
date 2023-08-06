package GUI.listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;

public class IsRightYearDocumentListener implements DocumentListener {
    JTextField textField;

    public IsRightYearDocumentListener(JTextField textField) {
        this.textField = textField;
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
        if (textField.getText().trim().isEmpty()) {
            textField.setBackground(new Color(0xFFFFFF));
        } else {
            try {
                int number = Integer.parseInt(this.textField.getText().trim());
                if (number/1000>0) {
                    if (number > LocalDate.now().getYear() - 20 && number <= LocalDate.now().getYear() - 5) {
                        textField.setBackground(new Color(0xD2FFD2));
                    } else {
                        textField.setBackground(new Color(0xEAD1DC));
                    }
                }
            } catch (NumberFormatException ex) {
                textField.setBackground(new Color(0xEAD1DC));
            }
        }
    }
}
