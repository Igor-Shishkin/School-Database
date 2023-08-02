package examples;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TreeWithPanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tree with Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode node1 = new TextTreeNode("Node 1", "This is Node 1.");
        DefaultMutableTreeNode node2 = new TextTreeNode("Node 2", "This is Node 2.");

        root.add(node1);
        root.add(node2);

        JTree tree = new JTree(root);
        tree.setCellRenderer(new TextTreeCellRenderer());
        tree.addMouseListener(new TextTreeNodeMouseListener(tree));

        JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
