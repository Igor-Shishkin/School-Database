package schoolDatabaseProgram.GUI.listeners;

import schoolDatabaseProgram.GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsRightParentPeselDocumentListener implements DocumentListener {
    JTextField peselField;
    JComboBox genderComboBox;
    ConstantsOfStyle styleConstants;

    public IsRightParentPeselDocumentListener(JTextField peselField, JComboBox genderComboBox,
                                              ConstantsOfStyle styleConstants) {
        this.peselField = peselField;
        this.genderComboBox = genderComboBox;
        this.styleConstants = styleConstants;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isPeselRight();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isPeselRight();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isPeselRight();
    }
    private void isPeselRight () {
        if (peselField.getText().length() == 11 && genderComboBox.getSelectedIndex() > 0) {
            String regex = "";
            if (genderComboBox.getSelectedIndex() == 1) {
                regex = "^[0-9]{2}(2[1-9]|3[012]|0[1-9]||1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{3}[13579]{1}[0-9]{1}$";
            } else {
                regex = "^[0-9]{2}(2[1-9]|3[012]|0[1-9]||1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{3}[24680]{1}[0-9]{1}$";
            }
            Pattern peselPattern = Pattern.compile(regex);
            Matcher matcher = peselPattern.matcher(peselField.getText().trim());
            peselField.setBackground((matcher.matches())
                    ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        } else if (peselField.getText().length() > 11) {
            peselField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        } else {
            peselField.setBackground(styleConstants.getCOLOR_NEUTRAL_FORMAT());
        }
    }
}
