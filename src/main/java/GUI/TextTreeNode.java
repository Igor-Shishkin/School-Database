package GUI;

import javax.swing.tree.DefaultMutableTreeNode;

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