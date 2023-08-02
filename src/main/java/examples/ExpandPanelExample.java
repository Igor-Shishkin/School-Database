package examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExpandPanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExpandPanelExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Expand Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the button
        JButton expandButton = new JButton("Expand");
        frame.add(expandButton, BorderLayout.NORTH);

        // Create the additional panel
        JPanel additionalPanel = new JPanel();
        additionalPanel.setBackground(Color.YELLOW);
        additionalPanel.setPreferredSize(new Dimension(200, 200));
        additionalPanel.setVisible(false); // Initially hide the panel

        // Add the additional panel to the center of the frame
        frame.add(additionalPanel, BorderLayout.CENTER);

        // Register a MouseListener to the button
        expandButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Toggle the visibility of the additional panel
                additionalPanel.setVisible(!additionalPanel.isVisible());
                frame.pack(); // Resize the frame to fit the new panel state
            }

            // Other MouseListener methods
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
