package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsRightPhoneNumberDocumentListener implements DocumentListener {
        JTextField telephoneField;

        public IsRightPhoneNumberDocumentListener(JTextField telephoneField) {
            this.telephoneField = telephoneField;
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
            Matcher matcher = phonePattern.matcher(telephoneField.getText());
            telephoneField.setBackground((matcher.matches())
                    ? ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT : ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
        }

}
