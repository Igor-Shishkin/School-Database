package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentralPanel extends JPanel implements ActionListener {
    JPanel gradesPanel;
    JPanel pupilsPanel;
    JPanel informationPanel;
    Border border;
    public CentralPanel() {
        this.setLayout(new GridBagLayout());

        border = BorderFactory.createLoweredBevelBorder();
        GridBagConstraints constraints = new GridBagConstraints();

        gradesPanel = new JPanel();
        gradesPanel.setBackground(MainWindow.actualSetColor.get(2));
        gradesPanel.setBorder(border);
        pupilsPanel = new JPanel();
        pupilsPanel.setBackground(MainWindow.actualSetColor.get(2));
        pupilsPanel.setBorder(border);
        informationPanel = new JPanel();
//        informationPanel.setPreferredSize(new Dimension(200,200));
        informationPanel.setBackground(MainWindow.actualSetColor.get(2));
        informationPanel.setBorder(border);
        this.setPreferredSize(new Dimension(1000, 640));



        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(5,3,5,2);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(gradesPanel, constraints);

        constraints.weightx = 0.65;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(pupilsPanel, constraints);

        constraints.weightx = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        this.add(informationPanel, constraints);

        this.setBackground(MainWindow.actualSetColor.get(1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
