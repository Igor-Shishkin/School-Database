package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    HashMap<String,String> logininfo = new HashMap<String,String>();
    JLabel resultLabel;
    int INDENT_FROM_THE_TOP = -50;

    LoginPage (HashMap<String, String> originalLoginInfo) {
        logininfo = originalLoginInfo;
        resultLabel = new JLabel("\t\tlogin successful");
        resultLabel.setBounds(0,0,450,420);
        resultLabel.setBackground(Color.CYAN);
        resultLabel.setForeground(Color.green);
        resultLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        resultLabel.setVisible(true);

        userIDLabel.setBounds(20, 104+INDENT_FROM_THE_TOP, 130, 25);
        userIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 27));
        userPasswordLabel.setBounds(20, 154+INDENT_FROM_THE_TOP, 150,30);
        userPasswordLabel.setFont(new Font("MV Boli", Font.PLAIN, 27));

        messageLabel.setBounds(125, 250+INDENT_FROM_THE_TOP, 250, 25);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(155,100+INDENT_FROM_THE_TOP,250,35);
        userPasswordField.setBounds(155,150+INDENT_FROM_THE_TOP,250,35);

        Font buttonFont = new Font("MV Boli", Font.BOLD, 18);
        loginButton.setBounds(125, 200+INDENT_FROM_THE_TOP, 100, 25);
        loginButton.setFocusable(true);
        loginButton.setFont(buttonFont);
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 200+INDENT_FROM_THE_TOP, 100, 25);
        resetButton.setFocusable(false);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(this);

        frame.add(resultLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,420);
        frame.setLayout(null);
        frame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }
        if (e.getSource() == loginButton) {
            String id = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(id)) {
                if (logininfo.get(id).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Access confirmed :)");
                    resultLabel.setVisible(true);
                    resultLabel.setText("Access confirmed :)");
                    resultLabel.setBackground(Color.green);
                    try {
                        Main.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }else{
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                    resultLabel.setVisible(true);
                    resultLabel.setText("Wrong password");
                    resultLabel.setBackground(Color.red);
                    try {
                        Main.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    resultLabel.setVisible(false);
                }
            }else {
//                messageLabel.setForeground(Color.DARK_GRAY);
//                messageLabel.setText("Username is not found.");
//                resultLabel.setVisible(true);
                resultLabel.setText("Username is not found.");
                resultLabel.setBackground(Color.red);
                try {
                    Main.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                resultLabel.setVisible(false);
            }
        }
    }

}
