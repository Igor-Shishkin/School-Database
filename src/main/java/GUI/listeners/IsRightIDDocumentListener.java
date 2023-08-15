package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;
import database.PupilsDataList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class IsRightIDDocumentListener implements DocumentListener {
    JTextField idField;

    public IsRightIDDocumentListener(JTextField idField) {
        this.idField = idField;
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
    public void isRight() {
        if (!idField.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                if (id > 0 && id < 1000) {
                    if (PupilsDataList.isNotThereID(id)) {
                        idField.setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
                    } else {
                        idField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                    }
                } else {
                    idField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
                }
            } catch ( NumberFormatException e) {
                idField.setBackground(ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
            }
        }   else {
            idField.setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
        }
    }
}
