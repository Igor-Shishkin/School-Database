package examples;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

class TextTreeNode extends DefaultMutableTreeNode {
    private final String text;

    public TextTreeNode(Object userObject, String text) {
        super(userObject);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
