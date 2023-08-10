package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IsMarkEnteredListener implements ActionListener {
    JComboBox[] comboBox;
    JLabel[] averageLabel;
    JLabel flagLabel;
    JLayeredPane promotionPane;
    int i, quantity;
    Color backgroundColor;


    public IsMarkEnteredListener(JComboBox[] comboBox, int i, int quantity, JLabel[] averageLabel,
                                    Color backgroundColor, JLayeredPane promotionPane, JLabel flagLabel) {
        this.comboBox = comboBox;
        this.i = i;
        this.quantity = quantity;
        this.averageLabel = averageLabel;
        this.backgroundColor = backgroundColor;
        this.promotionPane = promotionPane;
        this.flagLabel = flagLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox[i].getSelectedItem()!=null) {
            comboBox[i].setBackground(ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT);
        } else {
            comboBox[i].setBackground(ConstantsOfStyle.COLOR_NEUTRAL_FORMAT);
        }
        boolean isAllMarksInColumn = true;
        double sum = 0;
        int quantityEntered = 0;
        for (int j = i%4; j < quantity-4; j+=4) {
            if (comboBox[j].getSelectedItem()!=null && comboBox[j].isEnabled()) {
                String value = (String) comboBox[j].getSelectedItem();
                assert value != null;
                sum += Integer.parseInt(value);
                quantityEntered++;
            } else {
                isAllMarksInColumn = false;
            }
        }
        averageLabel[i%4].setText(String.format("%02.1f", (quantityEntered!=0&&sum!=0)?sum/quantityEntered:0f));
        averageLabel[i%4].setBackground((isAllMarksInColumn && comboBox[i].getSelectedItem()!=null)
                ? ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT :backgroundColor);
        int firstInLine = (i/4)*4;
        comboBox[firstInLine+3].setEnabled(comboBox[firstInLine].getSelectedItem()!=null &&
                comboBox[firstInLine + 1].getSelectedItem()!=null && comboBox[firstInLine + 2].getSelectedItem()!=null);
        boolean promotion = true;
        if (i%4!=3) {
            isAllMarksInColumn = true;
            sum = 0;
            quantityEntered = 0;
            for (int j = 3; j < quantity-4; j+=4) {
                if (comboBox[j].getSelectedItem()!=null && comboBox[j].isEnabled()) {
                    String value = (String) comboBox[j].getSelectedItem();
                    assert value != null;
                    sum += Integer.parseInt(value);
                    quantityEntered++;
                } else {
                    isAllMarksInColumn = false;
                }
                if (comboBox[j].getSelectedIndex()<2 || !comboBox[j].isEnabled()) {
                    promotion = false;
                }
            }
            averageLabel[3].setText(String.format("%02.1f", (quantityEntered!=0&&sum!=0)?sum/quantityEntered:0f));
            averageLabel[3].setBackground((isAllMarksInColumn && comboBox[quantity-4].getSelectedIndex()!=-1)
                    ? ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT :backgroundColor);
            promotionPane.setVisible(promotion);
            System.out.println(comboBox[i].getSelectedIndex());
            flagLabel.setVisible(sum/quantityEntered>4.65
                    && averageLabel[3].getBackground()==ConstantsOfStyle.COLOR_FOR_RIGHT_FORMAT
                    && comboBox[quantity-1].getSelectedIndex()>4);
        }
    }


}
