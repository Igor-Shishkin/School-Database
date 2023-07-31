package GUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Pupil;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MainWindow extends JFrame implements ActionListener {

    List<Color> setOfColorsSoftRose = new ArrayList<>(10);
    List<Color> setOfColorsOcean = new ArrayList<>(10);
    List<Color> setOfColorsContrast = new ArrayList<>(10);
    List<Color> setOfColorsAggressive = new ArrayList<>(10);
    List<Color> actualSetColor;
    ArrayList<Pupil> listOfPupils;
    Properties properties;
    JMenu fileMenu;
    JMenu styleMenu;
    JMenu informationMenu;
    JMenuItem openFileItem;
    JMenuItem newDatabaseItem;
    JMenuItem saveFileItem;
    JMenuItem closeItem;
    JMenu userMenu;
    JMenuItem logOutItem;
    JMenuItem ocean;
    JMenuItem changeAdmissionItem;
    JMenuItem informationItem;
    JMenuItem softRose;
    JMenuItem aggressive;
    JMenuItem contrast;

    JMenuBar menuBar;
    JTextField currentStatusPanel;
    Border border;
    JPanel centerPanel;
    JPanel gradesPanel;
    JPanel pupilsPanel;
    JPanel informationPanel;
    JPanel downPanel;

    MainWindow() throws IOException {
        fillColorsSets();
        setCurrentStatusPanel();
        actualSetColor = setOfColorsSoftRose;
        border = BorderFactory.createLineBorder(actualSetColor.get(4));

        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/jdbc.properties"));

        border = BorderFactory.createSoftBevelBorder(0, Color.BLUE, Color.MAGENTA);

//        JPanel topPanel = new JPanel();
//        topPanel.setPreferredSize(new Dimension(10, 5));
//        topPanel.setBorder(border);
        downPanel = new JPanel(new BorderLayout());
        downPanel.setPreferredSize(new Dimension(10, 35));
        downPanel.setBorder(border);
        downPanel.add(currentStatusPanel, BorderLayout.WEST);
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        fillCenterPanel();
        centerPanel.setBackground(Color.gray);


        menuBar = new JMenuBar();
        setJMenuBar();

        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
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
        if (e.getSource() == closeItem) {
            System.exit(0);
        }
        if (e.getSource() == openFileItem) {
            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(null);
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    String textFromFile = readFromFile(file);
                    String decryptedData = decryptData(textFromFile, properties.getProperty("jdbc.encryptPassword"));
                    listOfPupils = deserializeFromJSON(decryptedData);
                    listOfPupils.forEach(System.out::println);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "\t\tI can't read this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                            JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }


            }
        }

        if (e.getSource() == saveFileItem) {

            if (listOfPupils.size() != 0) {
                JFileChooser fileChooser = new JFileChooser();
//            fileChooser.showOpenDialog(null);
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        String serializedList = serializeToJSON(listOfPupils);
                        String encryptedData = encryptData(serializedList,
                                properties.getProperty("jdbc.encryptPassword"));
                        writeToFile(encryptedData, file.toPath());

                    } catch (JsonProcessingException ex) {
                        JOptionPane.showMessageDialog(null,
                                "\t\tI can't do this file!\nCALL TECH SUPPORT OR ELSE!", "title",
                                JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your database is empty", "title",
                        JOptionPane.INFORMATION_MESSAGE);
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

    public void setCurrentStatusPanel () {
        currentStatusPanel = new JTextField("Logged in");
        currentStatusPanel.setPreferredSize(new Dimension(982, 25));
        currentStatusPanel.setBackground(setOfColorsSoftRose.get(2));
        currentStatusPanel.setForeground(setOfColorsSoftRose.get(4));
        currentStatusPanel.setEditable(false);
    }
    public void setJMenuBar() {

        Path iconsPath = Paths.get("src", "main", "resources", "icons");
        URL imageURL = MainWindow.class.getResource("/src/main/resources/icons/goals.png");
        assert imageURL != null;
        ImageIcon fileIcon = new ImageIcon(iconsPath.resolve("file.png").toString());
        ImageIcon infoIcon = new ImageIcon(iconsPath.resolve("info.png").toString());
        ImageIcon userIcon = new ImageIcon(iconsPath.resolve("user.png").toString());
        ImageIcon styleIcon = new ImageIcon(iconsPath.resolve("style.png").toString());

        fileMenu = new JMenu("File");
        styleMenu = new JMenu("Style");
        informationMenu = new JMenu("Information");
        userMenu = new JMenu("User");

        fileMenu.setIcon(fileIcon);
        informationMenu.setIcon(infoIcon);
        styleMenu.setIcon(styleIcon);
        userMenu.setIcon(userIcon);

        newDatabaseItem = new JMenuItem("New");
        openFileItem = new JMenuItem("Open");
        saveFileItem = new JMenuItem("Save");
        closeItem = new JMenuItem("Close");
        logOutItem = new JMenuItem("Log out");
        changeAdmissionItem = new JMenuItem("Change admission");
        informationItem = new JMenuItem("Information");
        softRose = new JMenuItem("Soft rose");
        ocean = new JMenuItem("Ocean");
        contrast = new JMenuItem("Contrast");
        aggressive = new JMenuItem("Aggressive");

        fileMenu.add(newDatabaseItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(closeItem);

        styleMenu.add(ocean);
        styleMenu.add(softRose);
        styleMenu.add(contrast);
        styleMenu.add(aggressive);
        informationMenu.add(informationItem);
        userMenu.add(changeAdmissionItem);
        userMenu.add(logOutItem);

        menuBar.add(fileMenu);
        menuBar.add(userMenu);
        menuBar.add(styleMenu);
        menuBar.add(informationMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        styleMenu.setMnemonic(KeyEvent.VK_S);
        userMenu.setMnemonic(KeyEvent.VK_U);
        informationMenu.setMnemonic(KeyEvent.VK_H);


        openFileItem.addActionListener(this);
        newDatabaseItem.addActionListener(this);
        saveFileItem.addActionListener(this);
        changeAdmissionItem.addActionListener(this);
        informationItem.addActionListener(this);
        logOutItem.addActionListener(this);
        changeAdmissionItem.addActionListener(this);
        closeItem.addActionListener(this);

    }

    public void setMenuBarColors(Color foreground, Color backgraund) {
        fileMenu.setForeground(foreground);
        styleMenu.setForeground(foreground);
        informationMenu.setForeground(foreground);
        userMenu.setForeground(foreground);

        newDatabaseItem.setForeground(foreground);
        openFileItem.setForeground(foreground);
        saveFileItem.setForeground(foreground);
        closeItem.setForeground(foreground);
        logOutItem.setForeground(foreground);
        ocean.setForeground(foreground);
        changeAdmissionItem.setForeground(foreground);
        informationItem.setForeground(foreground);

        fileMenu.setBackground(backgraund);
        styleMenu.setBackground(backgraund);
        informationMenu.setBackground(backgraund);
        userMenu.setBackground(backgraund);

        newDatabaseItem.setBackground(backgraund);
        openFileItem.setBackground(backgraund);
        saveFileItem.setBackground(backgraund);
        closeItem.setBackground(backgraund);
        logOutItem.setBackground(backgraund);
        ocean.setBackground(backgraund);
        changeAdmissionItem.setBackground(backgraund);
        informationItem.setBackground(backgraund);
    }

    private static String serializeToJSON(ArrayList<Pupil> pupilsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pupilsList);
    }
    private String encryptData(String text, String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        return encryptor.encrypt(text);
    }

    private static String serializePupilToJSON(Pupil pupil) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pupil);
    }
    private void writeToFile(String data, Path path) {

        try {
            Files.write(path, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFromFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    private static String decryptData(String encryptedData, String password) {
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(password);
            encryptor.setAlgorithm("PBEWithMD5AndDES");
            return encryptor.decrypt(encryptedData);
        } catch (EncryptionOperationNotPossibleException e) {
            // Handle the decryption failure gracefully
            e.printStackTrace();
            // Return an appropriate error message or take necessary action
            return "Decryption failed: " + e.getMessage();
        }
    }

    private static ArrayList<Pupil> deserializeFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<ArrayList<Pupil>>() {
        });
    }

    private static Pupil deserializePupilFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<Pupil>() {
        });
    }

    public void fillColorsSets() {
        setOfColorsSoftRose.add(new Color(0xFFFFFF));
        setOfColorsSoftRose.add(new Color(0xE9FAE3));
        setOfColorsSoftRose.add(new Color(0xdee8d5));
        setOfColorsSoftRose.add(new Color(0xd5c7bc));
        setOfColorsSoftRose.add(new Color(0xac92a6));

        setOfColorsOcean.add(new Color(0xD9F7F6));
        setOfColorsOcean.add(new Color(0x8EF5F3));
        setOfColorsOcean.add(new Color(0x70C2C0));
        setOfColorsOcean.add(new Color(0x447574));
        setOfColorsOcean.add(new Color(0x677575));

        setOfColorsContrast.add(new Color(0x5FCDD9));
        setOfColorsContrast.add(new Color(0x04BFAD));
        setOfColorsContrast.add(new Color(0x04BF9D));
        setOfColorsContrast.add(new Color(0x027373));
        setOfColorsContrast.add(new Color(0x172026));

        setOfColorsAggressive.add(new Color((0xF5EF49)));
        setOfColorsAggressive.add(new Color((0x18F54C)));
        setOfColorsAggressive.add(new Color((0xF53D4C)));
        setOfColorsAggressive.add(new Color((0xEC31F5)));
        setOfColorsAggressive.add(new Color((0x4A1796)));
    }

    public void setStatusPanel() throws IOException, FontFormatException {
        currentStatusPanel = new JTextField("Logged in");
        currentStatusPanel.setBounds(2, 609, 982, 25);
        currentStatusPanel.setBackground(setOfColorsSoftRose.get(2));
        currentStatusPanel.setForeground(setOfColorsSoftRose.get(4));
        currentStatusPanel.setEditable(false);

        Path path = Paths.get("src", "main", "resources");
        File fontFile = new File(path.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        currentStatusPanel.setFont(remRegular);
    }

    public void fillCenterPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        gradesPanel = new JPanel();
        gradesPanel.setBackground(Color.CYAN);
        gradesPanel.setBorder(border);
        pupilsPanel = new JPanel();
        pupilsPanel.setBackground(Color.GRAY);
        pupilsPanel.setBorder(border);
        informationPanel = new JPanel();
//        informationPanel.setPreferredSize(new Dimension(200,200));
        informationPanel.setBackground(Color.GREEN);
        informationPanel.setBorder(border);
        centerPanel.setPreferredSize(new Dimension(1000, 640));



        constraints.fill = GridBagConstraints.BOTH;

        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.insets = new Insets(5,3,5,2);

        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(gradesPanel, constraints);

        constraints.weightx = 0.65;
        constraints.gridx = 1;
        constraints.gridy = 0;
        centerPanel.add(pupilsPanel, constraints);

        constraints.weightx = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        centerPanel.add(informationPanel, constraints);
    }


}


