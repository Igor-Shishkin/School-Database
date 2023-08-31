package school.database.gui;

import school.database.data.objects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ChangeLogin extends JDialog implements ActionListener {
    private final JButton loginButton = new JButton("Login");
    private final JButton resetButton = new JButton("Reset");
    private final JTextField userIDField = new JTextField();
    private final JPasswordField userPasswordField = new JPasswordField();
    private final JLabel messageLabel = new JLabel();
    private final HashMap<String, User> loginInfo;
    private boolean isSuccess;
    private final transient ActualElements actualElements;


    ChangeLogin (JFrame parentFrame, HashMap<String,User> loginInfo, ActualElements actualElements) {
        super(parentFrame, "Change permission", true);
        this.loginInfo = loginInfo;
        this.actualElements = actualElements;

        String fontName = "MV Boli";

        JPanel resultPanel = new JPanel();
        resultPanel.setBounds(0,0,450,420);
        JLabel resultLabel = new JLabel("   login successful");
        resultLabel.setFont(new Font(fontName, Font.BOLD, 40));
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.setVisible(false);


        JLabel userIDLabel = new JLabel("userID:");
        userIDLabel.setBounds(20, 44, 130, 25);
        userIDLabel.setFont(new Font(fontName, Font.PLAIN, 27));
        JLabel userPasswordLabel = new JLabel("password:");
        userPasswordLabel.setBounds(20, 94, 150,30);
        userPasswordLabel.setFont(new Font(fontName, Font.PLAIN, 27));

        messageLabel.setBounds(100, 190, 300, 25);
        messageLabel.setFont(new Font(null, Font.ITALIC, 20));

        userIDField.setBounds(155,40,250,35);
        userPasswordField.setBounds(155,90,250,35);

        Font buttonFont = new Font(fontName, Font.BOLD, 18);
        loginButton.setBounds(125, 140, 100, 25);
        loginButton.setFocusable(true);
        loginButton.setFont(buttonFont);
        loginButton.addActionListener(this);

        resetButton.setBounds(225, 140, 100, 25);
        resetButton.setFocusable(false);
        resetButton.setFont(buttonFont);
        resetButton.addActionListener(this);

        setWindowCloseListener();

        this.setLocationRelativeTo(null);
        this.add(resultPanel);
        this.add(userIDField);
        this.add(userPasswordField);
        this.add(userIDLabel);
        this.add(userPasswordLabel);
        this.add(messageLabel);
        this.add(loginButton);
        this.add(resetButton);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(450,280);
        this.setLayout(null);
        this.setVisible(false);


    }

    private void setWindowCloseListener() {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Cancel? In this case you will not\nbe able to work with the database",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    isSuccess = false;
                    dispose();
                }
            }
        };
        this.addWindowListener(windowListener);
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
                    actualElements.setUserName(id);
                    actualElements.setActualPermissions(loginInfo.get(id).getPermissions());
                    isSuccess = true;
                    this.dispose();
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
    public boolean showAndReturnIsSuccess() {
        this.setVisible(true);
        return isSuccess;
    }

}
