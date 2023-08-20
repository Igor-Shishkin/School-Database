package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (textField.getText().equals("")) {
            textField.setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
        } else {
            String regex = "^[1-9]{1}[0-9]?[0-9]?[a-zA-Z]?";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(textField.getText().trim());
            textField.setBackground( (matcher.matches())
                    ? ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT :  ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);

        }
    }
}
