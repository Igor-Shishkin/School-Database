package schoolDatabaseProgram.GUI.listeners;

import schoolDatabaseProgram.GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsRightEMailDocumentListener implements DocumentListener {
    JTextField eMailField;
    ConstantsOfStyle styleConstants;

    public IsRightEMailDocumentListener(JTextField eMailField, ConstantsOfStyle styleConstants) {
        this.eMailField = eMailField;
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
    private void isRight() {
        String regex = "^\\w*(\\-\\w)?(\\.\\w*)?@\\w*(-\\w*)?\\.\\w{2,3}(\\.\\w{2,3})?$";
        Pattern eMailPattern = Pattern.compile(regex);
        Matcher matcher = eMailPattern.matcher(eMailField.getText().trim());
        eMailField.setBackground((matcher.matches())
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
    }
}
