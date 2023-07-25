package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class mainWindow extends JFrame implements ActionListener {
    JButton button;
    JButton button2;
    JPanel panel;
    JLabel label;
    JTextField textField;

    mainWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        button = new JButton("Submit");
        button.setBackground(Color.LIGHT_GRAY);
        button.addActionListener(this);
        button.setFont(new Font("Consolas", Font.PLAIN, 25));


        button2 = new JButton();
        button2.addActionListener(this);
        button2.setText("Open file");
        button2.setFont(new Font("Consolas", Font.PLAIN, 25));


        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Consolas", Font.PLAIN, 35));
        textField.setForeground(new Color(0x00FF00));
        textField.setBackground(Color.black);

        textField.setText("username");

        this.add(button);
        this.add(button2);
        this.add(textField);
        this.pack();
        this.setVisible(true);

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
}


