package school.database.gui.listeners;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsRightPhoneNumberDocumentListener implements DocumentListener {
        private final JTextField telephoneField;
        private final  ConstantsOfStyle styleConstants;

        public IsRightPhoneNumberDocumentListener(JTextField telephoneField,
                                                  ConstantsOfStyle styleConstants) {
            this.telephoneField = telephoneField;
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
            String regex = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{3,4})(?: *x(\\d+))?\\s*$";
            Pattern phonePattern = Pattern.compile(regex);
            Matcher matcher = phonePattern.matcher(telephoneField.getText().trim());
            telephoneField.setBackground((matcher.matches())
                    ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
        }

}
