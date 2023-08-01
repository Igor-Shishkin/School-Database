package GUI;

import database.WriteReadDataToFile;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


public class MainWindow extends JFrame implements ActionListener {

//    static ArrayList<Pupil> listOfPupils;

    static List<Color> actualSetColor;
    Properties properties;
    JTextField currentStatusField;
    Border border;
    JPanel centerPanel;
    JPanel gradesPanel;
    JPanel pupilsPanel;
    JPanel informationPanel;
    JPanel downPanel;
    WriteReadDataToFile writeReadDataToFile;
    MyMenuBar myMenuBar;
    ColorsSets colorsSets = new ColorsSets();

    MainWindow() throws IOException {
        writeReadDataToFile = new WriteReadDataToFile();
        myMenuBar = new MyMenuBar();
        actualSetColor = colorsSets.setOfColorsSoftRose;


        border = BorderFactory.createSoftBevelBorder(SoftBevelBorder.RAISED, actualSetColor.get(0), actualSetColor.get(4));

//        JPanel topPanel = new JPanel();
//        topPanel.setPreferredSize(new Dimension(10, 5));
//        topPanel.setBorder(border);
        downPanel = new JPanel(new BorderLayout());
        setCurrentStatusPanel();

        centerPanel = new CentralPanel();

        this.setLayout(new BorderLayout());
        this.setJMenuBar(myMenuBar);
//        this.add(currentStatusPanel);
//        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(downPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }

//    public void setFonts (Component component, Font font) {
//        component.setFont(font);
//        if (component instanceof Container) {
//            for (Component child : ((Container) component).getComponents()) {
//                setFonts(child, font);
//            }
//        }
//    }

    public void setCurrentStatusPanel () {
        currentStatusField = new JTextField("Logged in");
        currentStatusField.setPreferredSize(new Dimension(982, 25));
        currentStatusField.setBackground(actualSetColor.get(2));
        currentStatusField.setForeground(Color.DARK_GRAY);
        currentStatusField.setEditable(false);
        downPanel.setPreferredSize(new Dimension(10, 35));
        downPanel.setBorder(border);
        downPanel.add(currentStatusField, BorderLayout.WEST);
    }




    public void setStatusPanel() throws IOException, FontFormatException {
        currentStatusField = new JTextField("Logged in");
        currentStatusField.setBounds(2, 609, 977, 25);
        currentStatusField.setBackground(actualSetColor.get(2));
        currentStatusField.setForeground(actualSetColor.get(4));
        currentStatusField.setEditable(false);

        Path path = Paths.get("src", "main", "resources");
        File fontFile = new File(path.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        currentStatusField.setFont(remRegular);
    }

}


