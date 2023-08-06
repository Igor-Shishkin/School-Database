package GUI.listeners;

import GUI.styleStorage.ConstantsOfColors;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class IsEmptyDocumentListener implements DocumentListener {
    JTextField textField;

    public IsEmptyDocumentListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isEmpty();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isEmpty();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isEmpty();
    }
    private void isEmpty(){
        String text = textField.getText().replaceAll("\\s+", "");
        if (text.isEmpty()) {
            textField.setBackground(ConstantsOfColors.COLOR_FOR_WRONG_FORMAT);
        } else {
            textField.setBackground(ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT);
        }
    }

}
