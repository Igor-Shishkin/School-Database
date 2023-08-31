package school.database.gui;

import school.database.gui.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CustomTreeCellRenderer extends DefaultTreeCellRenderer {

    private final transient ConstantsOfStyle styleConstants;
    public CustomTreeCellRenderer(ConstantsOfStyle styleConstants) {
        this.styleConstants = styleConstants;
    }
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        Component component;
        component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        ((JComponent) component).setOpaque(false);

        this.setTextSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(1));
        this.setTextNonSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(2));

        this.setBackgroundNonSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(0));
        this.setBackgroundSelectionColor(styleConstants.getACTUAL_SET_OF_COLORS().get(0));

        return component;
    }
}


