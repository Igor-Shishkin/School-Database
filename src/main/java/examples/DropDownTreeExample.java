package examples;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropDownTreeExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DropDownTreeExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Drop-down Tree Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a sample tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode folder1 = new DefaultMutableTreeNode("Folder 1");
        DefaultMutableTreeNode folder2 = new DefaultMutableTreeNode("Folder 2");
        DefaultMutableTreeNode subfolder1 = new DefaultMutableTreeNode("Subfolder 1");
        folder1.add(subfolder1);
        root.add(folder1);
        root.add(folder2);

        JTree tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setRootVisible(false); // Hide the root node

        // Customize the tree node icons (optional)
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        Icon folderIcon = UIManager.getIcon("FileView.directoryIcon");
        renderer.setClosedIcon(folderIcon);
        renderer.setOpenIcon(folderIcon);
        renderer.setLeafIcon(folderIcon);
        tree.setCellRenderer(renderer);

        JScrollPane treeScrollPane = new JScrollPane(tree);

        // Create the drop-down component
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Select a folder");
        comboBox.addItem("Folder 1");
        comboBox.addItem("Folder 2");

        // Add action listener to show/hide the tree when the combo box is clicked
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedIndex() == 0) {
                    treeScrollPane.setVisible(false);
                } else {
                    treeScrollPane.setVisible(true);
                }
            }
        });

        // Set the initial state to hide the tree
        treeScrollPane.setVisible(false);

        // Create the main panel to hold the components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(comboBox, BorderLayout.NORTH);
        mainPanel.add(treeScrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
