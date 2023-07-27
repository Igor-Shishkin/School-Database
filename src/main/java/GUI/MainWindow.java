package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class MainWindow extends JFrame implements ActionListener {

    JMenu fileMenu;
    JMenu styleMenu;
    JMenu helpMenu;
    JMenuItem openFileItem;
    JMenuItem newDatabaseItem;
    JMenuItem saveItem;
    JMenuItem closeItem;
    JMenu userMenu;
    JMenuItem logOutItem;
    JMenuItem changeStyle;
    JMenuItem changeAdmissionItem;
    JMenuItem helpItem;


    JButton button;
    JButton button2;
    JPanel panel;
    JLabel label;
    JTextField textField;
    JMenuBar menuBar;
    Font fontJMenuBar;

    MainWindow(){
        fontJMenuBar = new Font("Josefin Sans", Font.BOLD, 15);



        menuBar = new JMenuBar();


        setJMenuBar();

        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(900,600);
        this.setVisible(true);











//        button = new JButton("Submit");
//        button.setBackground(Color.LIGHT_GRAY);
//        button.addActionListener(this);
//        button.setFont(new Font("Consolas", Font.PLAIN, 25));
//
//
//        button2 = new JButton();
//        button2.addActionListener(this);
//        button2.setText("Open file");
//        button2.setFont(new Font("Consolas", Font.PLAIN, 25));
//
//
//        textField = new JTextField();
//        textField.setPreferredSize(new Dimension(250,40));
//        textField.setFont(new Font("Consolas", Font.PLAIN, 35));
//        textField.setForeground(new Color(0x00FF00));
//        textField.setBackground(Color.black);
//
//        textField.setText("username");
//
//        this.add(button);
//        this.add(button2);
//        this.add(textField);
//        this.pack();
//        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {
            System.out.println("Hello!: " + textField.getText());
        }
        if (e.getSource()==button2) {
            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(null);
            int response = fileChooser.showOpenDialog(null);

            if(response==JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(file);
          }
        }
    }

//    public void setFonts (Component component, Font font) {
//        component.setFont(font);
//        if (component instanceof Container) {
//            for (Component child : ((Container) component).getComponents()) {
//                setFonts(child, font);
//            }
//        }
//    }

        public void setJMenuBar() {

            SwingUtilities.invokeLater(() -> {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }});

            fileMenu = new JMenu("File");
            styleMenu = new JMenu("Style");
            helpMenu = new JMenu("Help");
            userMenu = new JMenu("User");

            newDatabaseItem = new JMenuItem("New");
            openFileItem = new JMenuItem("Open");
            saveItem = new JMenuItem("Save");
            closeItem = new JMenuItem("Close");
            logOutItem = new JMenuItem("Log out");
            changeStyle = new JMenuItem("Change style");
            changeAdmissionItem = new JMenuItem("Change admission");
            helpItem = new JMenuItem("Help");




            fileMenu.add(newDatabaseItem);
            fileMenu.add(openFileItem);
            fileMenu.add(saveItem);
            fileMenu.add(closeItem);


            styleMenu.add(changeStyle);
            helpMenu.add(helpItem);
            userMenu.add(changeAdmissionItem);
            userMenu.add(logOutItem);

            menuBar.add(fileMenu);
            menuBar.add(userMenu);
            menuBar.add(styleMenu);
            menuBar.add(helpMenu);

            fileMenu.setMnemonic(KeyEvent.VK_F);
            styleMenu.setMnemonic(KeyEvent.VK_S);
            userMenu.setMnemonic(KeyEvent.VK_U);
            helpMenu.setMnemonic(KeyEvent.VK_H);

            openFileItem.addActionListener(this);
            newDatabaseItem.addActionListener(this);
            saveItem.addActionListener(this);
            changeAdmissionItem.addActionListener(this);
            helpItem.addActionListener(this);
            logOutItem.addActionListener(this);
            changeAdmissionItem.addActionListener(this);
            closeItem.addActionListener(this);

        }

}


