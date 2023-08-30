package school.database.GUI.listeners;

import school.database.GUI.styleStorage.ConstantsOfStyle;
import school.database.data.Data;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class IsRightIDDocumentListener implements DocumentListener {
    JTextField idField;
    Data dataList;
    ConstantsOfStyle styleConstants;

    public IsRightIDDocumentListener(JTextField idField, Data dataList, ConstantsOfStyle styleConstants) {

        this.idField = idField;
        this.dataList = dataList;
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
    public void isRight() {
        if (!idField.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                if (id > 0 && id < 1000) {
                    if (dataList.isNotThereID(id)) {
                        idField.setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
                    } else {
                        idField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                    }
                } else {
                    idField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
                }
            } catch ( NumberFormatException e) {
                idField.setBackground(styleConstants.getCOLOR_FOR_WRONG_FORMAT());
            }
        }   else {
            idField.setBackground(styleConstants.getCOLOR_NEUTRAL_FORMAT());
        }
    }
}
