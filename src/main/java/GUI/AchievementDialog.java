package GUI;

import GUI.styleStorage.ConstantsOfStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AchievementDialog extends JDialog implements ActionListener {
        String achievements;
        JButton  closeButton;
        JLabel textArea;

    AchievementDialog(JFrame parentFrame, String achievements) {
            super(parentFrame, "Achievement", true);

            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setLayout(new GridBagLayout());

            String achievementForLabel = "";
            if (achievements.length()>30) {
                for (int i = 25; i < achievements.length(); i++) {
                    if (achievements.charAt(i) == ' ') {
                        achievementForLabel = achievements.substring(0, i).concat("<br>")
                                .concat(achievements.substring(i + 1));
                        achievements = achievementForLabel;
                        i += 25;
                    }
                }
            }
            achievementForLabel = "<html>".concat(achievementForLabel).concat("</html>");

            textArea = new JLabel(achievementForLabel);
            textArea.setFont(ConstantsOfStyle.THE_MAIN_FONT.deriveFont(Font.PLAIN,20));

            closeButton = new JButton("Cancel");
            closeButton.addActionListener(this);

            JScrollPane scrollPane = new JScrollPane(textArea);

            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(7,20,4,20);
            c.fill = GridBagConstraints.HORIZONTAL;

            c.gridx=0;
            c.gridy=0;
            c.gridwidth = 4;
            c.gridheight = 2;
            this.add(scrollPane, c);

            c.insets = new Insets(7,5,7,5);
            c.gridx=0;
            c.gridy=2;
            c.gridwidth = 4;
            c.gridheight = 1;
            this.add(new JSeparator(), c);

            c.insets = new Insets(6,10,6,10);
            c.gridx=1;
            c.gridy=3;
            c.gridwidth = 2;
            this.add(closeButton, c);

            this.setLocationRelativeTo(null);
            this.pack();
        this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setTitle("Pupil's achievement");
//        this.setVisible(true);

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==closeButton){
                dispose();

            }
        }
    }

