package school.database.gui.listeners;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenderForParentComboBoxListener implements ActionListener {
    private final JTextField peselField;
    private final JComboBox<String> genderComboBox;
    private final ConstantsOfStyle styleConstants;

    public GenderForParentComboBoxListener(JTextField peselField, JComboBox<String> genderComboBox,
                                           ConstantsOfStyle styleConstants) {
        this.peselField = peselField;
        this.genderComboBox = genderComboBox;
        this.styleConstants = styleConstants;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        genderComboBox.setBackground((genderComboBox.getSelectedIndex()>0)
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_NEUTRAL_FORMAT());

        if (peselField.getText().length() == 11 && genderComboBox.getSelectedIndex() > 0) {
            String regex = "";
            if (genderComboBox.getSelectedIndex() == 1) {
                regex = "^[0-9]{2}(2[1-9]|3[012]|0[1-9]||1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{3}[13579][0-9]$";
            } else {
                regex = "^[0-9]{2}(2[1-9]|3[012]|0[1-9]||1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{3}[24680][0-9]$";
            }
            Pattern peselPattern = Pattern.compile(regex);
            Matcher matcher = peselPattern.matcher(peselField.getText().trim());
            peselField.setBackground((matcher.matches())
                    ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }
    }
}
