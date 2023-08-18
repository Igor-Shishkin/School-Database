package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeComboBoxListener implements ActionListener {

    JComboBox gradeComboBox;
    JButton addMarksMutton;

    public GradeComboBoxListener(JComboBox gradeComboBox, JButton addMarksMutton) {
        this.gradeComboBox = gradeComboBox;
        this.addMarksMutton = addMarksMutton;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        addMarksMutton.setEnabled(gradeComboBox.getSelectedIndex() > 4);
       gradeComboBox.setBackground((gradeComboBox.getSelectedIndex()>0)
               ? ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT : ConstantsOfStyle.COLOR_FOR_WRONG_FORMAT);
    }
}
