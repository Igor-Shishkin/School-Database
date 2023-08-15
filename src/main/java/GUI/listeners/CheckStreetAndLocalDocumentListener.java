package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CheckStreetAndLocalDocumentListener implements DocumentListener {
    JTextField textField;

    public CheckStreetAndLocalDocumentListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isInt();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isInt();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isInt();
    }
    private void isInt () {
        try {
            int number = Integer.parseInt(this.textField.getText());
            if (number<500) {
                textField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
            } else {
                textField.setBackground(Color.WHITE);
            }
        } catch (NumberFormatException ex) {
            textField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
        }
    }
}
