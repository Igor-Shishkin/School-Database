package GUI;

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
    ConstantsOfStyle styleConstants;
    public CustomTreeCellRenderer(ConstantsOfStyle styleConstants) {
        this.styleConstants = styleConstants;
    }
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        ((JComponent) component).setOpaque(false);

        this.setTextSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(1));
        this.setTextNonSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(2));
//        this.setBorderSelectionColor(Color.blue);
//        this.setBackgroundSelectionColor(ColorsSets.ACTUAL_SET_OF_COLORS.get(1));

        this.setBackgroundNonSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
        this.setBackgroundSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(0));

        return component;
    }
}


