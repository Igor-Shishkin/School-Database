package examples;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Add components using GridBagLayout constraints
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(new JLabel("Name:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        mainPanel.add(new JTextField(20), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        mainPanel.add(new JLabel("Age:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(new JTextField(5), constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        mainPanel.add(new JLabel("Gender:"), constraints);

        constraints.gridx = 3;
        constraints.gridy = 1;
        mainPanel.add(new JComboBox<>(new String[]{"Male", "Female"}), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        mainPanel.add(new JSeparator(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        mainPanel.add(new JButton("Submit"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        mainPanel.add(new JButton("Cancel"), constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        mainPanel.add(new JButton("Reset"), constraints);

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
