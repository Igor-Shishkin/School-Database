package GUI.listeners;

import GUI.styleStorage.ConstantsOfColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IsMarkEnteredListener implements ActionListener {
    JComboBox[] comboBox;
    JLabel[] averageLabel;
    int i, quantity;
    Color backgroundColor;

    public IsMarkEnteredListener(JComboBox[] comboBox, int i, int quantity, JLabel[] averageLabel,
                                    Color backgroundColor) {
        this.comboBox = comboBox;
        this.i = i;
        this.quantity = quantity;
        this.averageLabel = averageLabel;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox[i].getSelectedItem()!=null) {
            comboBox[i].setBackground(ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT);
        } else {
            comboBox[i].setBackground(ConstantsOfColors.COLOR_NEUTRAL_FORMAT);
        }
        boolean isAllMarksInColumn = true;
        double sum = 0;
        int quantityEntered = 0;
        for (int j = i%4; j < quantity; j+=4) {
            if (comboBox[j].getSelectedItem()!=null) {
                String value = (String) comboBox[j].getSelectedItem();
                assert value != null;
                sum += Integer.parseInt(value);
                quantityEntered++;
            } else {
                isAllMarksInColumn = false;
            }
        }
        System.out.println(i%4);
        averageLabel[i%4].setText(String.format("%02.1f", (quantity!=0&&sum!=0)?sum/quantityEntered:0f));
        averageLabel[i%4].setBackground((isAllMarksInColumn)?ConstantsOfColors.COLOR_FOR_RIGHT_FORMAT:
                backgroundColor);
    }

}
