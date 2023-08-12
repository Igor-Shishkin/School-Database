package GUI;

import GUI.styleStorage.ColorsSets;
import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    Component component;
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

//        component.setBackground(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));
//        component.setForeground(Color.black);

        ((JComponent) component).setOpaque(true);

        this.setTextSelectionColor(new Color(0x011DD3));
//        this.setBorderSelectionColor(Color.blue);
//        this.setBackgroundSelectionColor(ColorsSets.ACTUAL_SET_OF_COLORS.get(1));
        this.setBackgroundNonSelectionColor(ColorsSets.ACTUAL_SET_OF_COLORS.get(2));

        return component;
    }
    public void setColorsForTree (Color background, Color foreground) {
        component.setBackground(background);
        component.setForeground(foreground);
    }
}


