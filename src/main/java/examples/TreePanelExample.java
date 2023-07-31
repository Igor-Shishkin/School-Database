package examples;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreePanelExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tree Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a sample tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Node 1");
        node1.add(new DefaultMutableTreeNode("Subitem 1.1"));
        node1.add(new DefaultMutableTreeNode("Subitem 1.2"));
        root.add(node1);

        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Node 2");
        node2.add(new DefaultMutableTreeNode("Subitem 2.1"));
        node2.add(new DefaultMutableTreeNode("Subitem 2.2"));
        root.add(node2);

        JTree tree = new JTree(root);
        tree.setRootVisible(false); // Hide the root node

        // Customize the tree node icons (optional)
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        Icon folderIcon = UIManager.getIcon("FileView.directoryIcon");
        renderer.setClosedIcon(folderIcon);
        renderer.setOpenIcon(folderIcon);
        renderer.setLeafIcon(folderIcon);
        tree.setCellRenderer(renderer);

        // Add a tree selection listener to show/hide subitems when nodes are clicked
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) {
                    return;
                }

                if (selectedNode.getChildCount() > 0) {
                    // Node has children (subitems), expand/collapse the node
                    if (tree.isExpanded(e.getPath())) {
                        tree.collapsePath(e.getPath());
                    } else {
                        tree.expandPath(e.getPath());
                    }
                }
            }
        });

        JScrollPane treeScrollPane = new JScrollPane(tree);

        // Create the main panel to hold the tree
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(treeScrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
