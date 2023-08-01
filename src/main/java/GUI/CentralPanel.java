package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CentralPanel extends JPanel implements ActionListener {
    JPanel gradesPanel;
    JPanel pupilsPanel;
    JPanel informationPanel;
    Border border;
    GridBagConstraints constraints;
    DefaultMutableTreeNode root;
    DefaultMutableTreeNode gradeZero;
    DefaultMutableTreeNode gradeFirst;
    DefaultMutableTreeNode gradeSecond;
    DefaultMutableTreeNode gradeThird;
    DefaultMutableTreeNode gradeFourth;
    DefaultMutableTreeNode gradeFifth;
    DefaultMutableTreeNode gradeSixth;
    DefaultMutableTreeNode gradeSeventh;
    DefaultMutableTreeNode gradeEighth;
    JTree tree;
    Font font;
    public CentralPanel() {
        this.setLayout(new GridBagLayout());

        border = BorderFactory.createLoweredBevelBorder();
        font = new Font("MV Boli",Font.PLAIN,16);


        gradesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gradesPanel.setBackground(MainWindow.actualSetColor.get(2));
        gradesPanel.setBorder(border);
        pupilsPanel = new JPanel();
        pupilsPanel.setBackground(MainWindow.actualSetColor.get(2));
        pupilsPanel.setBorder(border);
        informationPanel = new JPanel();
        informationPanel.setBackground(MainWindow.actualSetColor.get(2));
        informationPanel.setBorder(border);

        setTreeNode();
        gradesPanel.setToolTipText("Grades");
        gradesPanel.add(tree);


        setGridBagConstraints();
        this.setBackground(MainWindow.actualSetColor.get(0));
    }

    private void setTreeNode() {
        root = new DefaultMutableTreeNode("School");

        gradeZero = new DefaultMutableTreeNode("Zero class");
        gradeFirst = new DefaultMutableTreeNode("First class");
        gradeSecond = new DefaultMutableTreeNode("Second class");
        gradeThird = new DefaultMutableTreeNode("Third class");
        gradeFourth = new DefaultMutableTreeNode("Fourth class");
        gradeFifth = new DefaultMutableTreeNode("Fifth class");
        gradeSixth = new DefaultMutableTreeNode("Sixth class");
        gradeSeventh = new DefaultMutableTreeNode("Seventh class");
        gradeEighth = new DefaultMutableTreeNode("Eighth class");

        root.add(gradeZero);
        root.add(gradeFirst);
        root.add(gradeSecond);
        root.add(gradeThird);
        root.add(gradeFourth);
        root.add(gradeFifth);
        root.add(gradeSixth);
        root.add(gradeSeventh);
        root.add(gradeEighth);

        tree = new JTree(root);
        tree.setFont(font);
        tree.setBackground(MainWindow.actualSetColor.get(2));
        tree.setCellRenderer(new CustomTreeCellRenderer());

        JScrollPane pane = new JScrollPane(tree);
        pane.setPreferredSize(new Dimension(250, 350));

//        getContentPane().add(pane);
//        setBackground(root, MainWindow.actualSetColor.get(2));

    }

//

//        public void setBackground (DefaultMutableTreeNode node, Color color) {
//        node.setParent(color);
//        if (node instanceof Container) {
//            for (Component child : ((Container) component).getComponents()) {
//                setParent(child, color);
//            }
//        }
//    }

    private void setGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        constraints.insets = new Insets(5,3,5,2);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(gradesPanel, constraints);

        constraints.weightx = 0.65;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(pupilsPanel, constraints);

        constraints.weightx = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        this.add(informationPanel, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
