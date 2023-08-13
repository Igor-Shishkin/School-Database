package GUI.listeners;

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
    }
}
