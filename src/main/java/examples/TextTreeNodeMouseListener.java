package examples;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class TextTreeNodeMouseListener extends MouseAdapter {
    private final JTree tree;

    public TextTreeNodeMouseListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (node instanceof TextTreeNode) {
                TextTreeNode textNode = (TextTreeNode) node;
                String text = textNode.getText();

                // Show a panel with the text content
                JOptionPane.showMessageDialog(tree, text, "Node Text", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}