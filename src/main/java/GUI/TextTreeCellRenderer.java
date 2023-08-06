package GUI;

import GUI.styleStorage.ColorsSets;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

class TextTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        if (value instanceof TextTreeNode) {
            TextTreeNode node = (TextTreeNode) value;
            setText(node.getUserObject().toString() + " - " + node.getText());
        }

        component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
        component.setForeground(Color.black);
        ((JComponent) component).setOpaque(true);

        return component;
    }
}