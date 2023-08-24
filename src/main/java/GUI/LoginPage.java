package GUI;

import database.PupilsDataList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    HashMap<String,User> loginInfo;
    JPanel resultPanel;
    JLabel resultLabel;
    int INDENT_FROM_THE_TOP = -60;
    Permissions permissions;
    private final PupilsDataList dataList;


    LoginPage (HashMap<String, User> originalLoginInfo, Permissions permissions, PupilsDataList dataList) {
        loginInfo = originalLoginInfo;
        this.permissions = permissions;
        this.dataList = dataList;

        resultPanel = new JPanel();
        resultPanel.setBounds(0,0,450,420);
        resultLabel = new JLabel("   login successful");
        resultLabel.setFont(new Font("MV Boli", Font.BOLD, 40));
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.setVisible(false);


        userIDLabel.setBounds(20, 104+INDENT_FROM_THE_TOP, 130, 25);
        userIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 27));
        userPasswordLabel.setBounds(20, 154+INDENT_FROM_THE_TOP, 150,30);
        userPasswordLabel.setFont(new Font("MV Boli", Font.PLAIN, 27));

        messageLabel.setBounds(100, 250+INDENT_FROM_THE_TOP, 300, 25);
        messageLabel.setFont(new Font(null, Font.ITALIC, 20));

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

        frame.setLocationRelativeTo(null);
        frame.add(resultPanel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,280);
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

            if (loginInfo.containsKey(id)) {
                if (loginInfo.get(id).getPassword().equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("   Access confirmed :)");
//                    Main.PERMISSIONS = loginInfo.get(id).getPermissions();
                    try {
                        new MainWindow(dataList);
                    } catch (IOException | FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                }else{
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }
            }else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username is not found.");
            }
        }
    }


}
