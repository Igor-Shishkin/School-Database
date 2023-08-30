package school.database.GUI.listeners;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class HandCursorForMouseMotionAdapter extends MouseMotionAdapter {
    final JTree tree;
    public HandCursorForMouseMotionAdapter(JTree tree) {
        this.tree = tree;
    }
    public void mouseMoved(MouseEvent e) {
        int x = (int) e.getPoint().getX();
        int y = (int) e.getPoint().getY();
        TreePath path = tree.getPathForLocation(x, y);
        if (path == null) {
            tree.setCursor(Cursor.getDefaultCursor());
        } else {
            tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }
}
