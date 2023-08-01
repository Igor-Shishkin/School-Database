package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        // Set the background color for all nodes to Color.YELLOW
        component.setBackground(Color.YELLOW);
        component.setForeground(Color.red);
        component.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));

        ((JComponent) component).setOpaque(true);

        return component;
    }
}
