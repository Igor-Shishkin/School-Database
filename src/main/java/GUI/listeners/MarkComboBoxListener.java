package GUI.listeners;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarkComboBoxListener implements ActionListener {
    JComboBox[] comboBox;
    JLabel[] averageLabel;
    JLabel flagLabel;
    JLayeredPane promotionPane;
    int i, quantity;
    Color backgroundColor;
    ConstantsOfStyle styleConstants;


    public MarkComboBoxListener(JComboBox[] comboBox, int i, int quantity, JLabel[] averageLabel,
                                Color backgroundColor, JLayeredPane promotionPane, JLabel flagLabel,
                                ConstantsOfStyle styleConstants) {
        this.comboBox = comboBox;
        this.i = i;
        this.quantity = quantity;
        this.averageLabel = averageLabel;
        this.backgroundColor = backgroundColor;
        this.promotionPane = promotionPane;
        this.flagLabel = flagLabel;
        this.styleConstants = styleConstants;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (comboBox[i].getSelectedItem()!=null) {
            comboBox[i].setBackground(styleConstants.getCOLOR_FOR_RIGHT_FORMAT());
        } else {
            comboBox[i].setBackground(styleConstants.getCOLOR_NEUTRAL_FORMAT());
        }


        boolean promotion = true;
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
            if (comboBox[j].getSelectedIndex()<2 || !comboBox[j].isEnabled()) {
                promotion = false;
            }
        }
        averageLabel[i%4].setText(String.format("%02.1f", (quantityEntered!=0&&sum!=0)?sum/quantityEntered:0f));
        averageLabel[i%4].setBackground((isAllMarksInColumn && comboBox[i].getSelectedItem()!=null)
                ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() :backgroundColor);
        int firstInLine = (i/4)*4;
        comboBox[firstInLine+3].setEnabled(comboBox[firstInLine].getSelectedItem()!=null &&
                comboBox[firstInLine + 1].getSelectedItem()!=null && comboBox[firstInLine + 2].getSelectedItem()!=null);


        if (i%4!=3) {
            promotion = true;
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
                    ? styleConstants.getCOLOR_FOR_RIGHT_FORMAT() :backgroundColor);

        }
        isAllMarksInColumn = true;

            promotionPane.setVisible(promotion);
            if (promotion) {
                flagLabel.setVisible(sum / quantityEntered > 4.65
                        && averageLabel[3].getBackground() == styleConstants.getCOLOR_FOR_RIGHT_FORMAT()
                        && comboBox[quantity - 1].getSelectedIndex() > 4);
            }

    }
}
