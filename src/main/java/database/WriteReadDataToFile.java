package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;



public class WriteReadDataToFile {
    Properties properties;
//    public static void main(String[] args) throws IOException {
//        GeneratePupilData generate = new GeneratePupilData();
//        ArrayList<Pupil> listOfPupils = new ArrayList<>(5);

//        String serializedList = serializeToJSON(listOfPupils);
//        String serializedPupil = serializePupilToJSON(pupil1);
//        String password = "0000";
//        Path filePath = Paths.get("ProtectedData.txt");
//
//        String encryptedData = encryptData(serializedList, password);
//        WriteToFile(encryptedData, filePath);
//
//        String stringFromFile = readFromFile(filePath);
//        String decryptedString = decryptData(stringFromFile, password);
//        System.out.println(decryptedString);
//        List<Pupil> listFromString = deserializeFromJSON(decryptedString);
//        listFromString.forEach(System.out::println);

//        Pupil pupilFromFile = deserializePupilFromJSON(decryptedString);
//        System.out.println(pupilFromFile);
//    }

    public WriteReadDataToFile() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/jdbc.properties"));
    }

    public void writeListLoFile(ArrayList<Pupil> listOfPupils, File file) throws JsonProcessingException {
        String serializedList = serializeToJSON(listOfPupils);
        String encryptedData = encryptData(serializedList,
                properties.getProperty("jdbc.encryptPassword"));
        writeToFile(encryptedData, file.toPath());
    }
    public ArrayList<Pupil> readListFromFile (File file) throws IOException {
        String textFromFile = readFromFile(file);
        String decryptedData = decryptData(textFromFile, properties.getProperty("jdbc.encryptPassword"));
        return deserializeFromJSON(decryptedData);
    }
    private static String serializeToJSON(ArrayList<Pupil> pupilsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
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
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(jsonData, new TypeReference<ArrayList<Pupil>>() {
        });
    }

    private static Pupil deserializePupilFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<Pupil>() {
        });
    }


}
