package examples;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicTreeExample {
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DynamicTreeExample treeExample = new DynamicTreeExample();
            treeExample.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Dynamic Tree Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        root = new DefaultMutableTreeNode("Root");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        JButton addButton = new JButton("Add Node");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewNode();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        JScrollPane scrollPane = new JScrollPane(tree);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addNewNode() {
        // Prompt the user for the new node name (you can customize this input process)
        String nodeName = JOptionPane.showInputDialog(tree, "Enter new node name:");

        if (nodeName != null && !nodeName.isEmpty()) {
            // Create the new node with the provided name
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);

            // Add the new node to the root node
            root.add(newNode);

            // Notify the tree model that the structure has changed
            treeModel.nodeStructureChanged(root);

            // Optionally, expand the parent node to show the newly added node
            tree.expandPath(tree.getSelectionPath());
        }
    }
}
