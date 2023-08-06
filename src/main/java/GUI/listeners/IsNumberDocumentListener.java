package GUI.listeners;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class IsNumberDocumentListener implements DocumentListener {
    JTextField textField;

    public IsNumberDocumentListener(JTextField textField) {
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
            Integer.parseInt(this.textField.getText());
            textField.setBackground(new Color(0xD2FFD2));
        } catch (NumberFormatException ex) {
            textField.setBackground(new Color(0xEAD1DC));
        }
    }
}
