package school.database.gui.listeners;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckStreetAndLocalDocumentListener implements DocumentListener {
    private JTextField textField;
    private ConstantsOfStyle styleConstants;

    public CheckStreetAndLocalDocumentListener(JTextField textField, ConstantsOfStyle styleConstants) {
        this.textField = textField;
        this.styleConstants = styleConstants;
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
            textField.setBackground(styleConstants.getCOLOR_NEUTRAL_FORMAT());
        } else {
            String regex = "^[1-9]\\d?\\d?[a-zA-Z]?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(textField.getText().trim());
            textField.setBackground( (matcher.matches())
                    ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() :  styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }
}
