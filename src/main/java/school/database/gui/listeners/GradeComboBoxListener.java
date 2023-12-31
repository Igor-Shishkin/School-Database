package school.database.gui.listeners;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeComboBoxListener implements ActionListener {

    private final JComboBox gradeComboBox;
    private final JButton addMarksMutton;
    private final ConstantsOfStyle styleConstants;

    public GradeComboBoxListener(JComboBox gradeComboBox, JButton addMarksMutton, ConstantsOfStyle styleConstants) {
        this.gradeComboBox = gradeComboBox;
        this.addMarksMutton = addMarksMutton;
        this.styleConstants = styleConstants;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        addMarksMutton.setEnabled(gradeComboBox.getSelectedIndex() > 4);
       gradeComboBox.setBackground((gradeComboBox.getSelectedIndex()>0)
               ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() : styleConstants.getCOLOR_FOR_WRONG_FORMAT());
    }
}
