package schoolDatabaseProgram.GUI;

import schoolDatabaseProgram.Main;
import schoolDatabaseProgram.database.Permissions;
import schoolDatabaseProgram.database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ChangeLogin extends JDialog implements ActionListener {
    JButton loginButton = new JButton("Login"), resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    HashMap<String, User> loginInfo;
    JPanel resultPanel;
    JLabel resultLabel;
    int INDENT_FROM_THE_TOP = -60;
    Permissions permissions;
    boolean isSuccess;


    ChangeLogin (JFrame parentFrame, HashMap<String,User> loginInfo) {
        super(parentFrame, "Change permission", true);
        this.loginInfo = loginInfo;


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
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
                    Main.CURRENT_USER = id;
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

//    public String showDialogAndGetInput(){
//        this.setVisible(true);
//        return id;
//    }
}
