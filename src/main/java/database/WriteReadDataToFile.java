package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;



public class WriteReadDataToFile {
    Properties properties;


    public WriteReadDataToFile() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/jdbc.properties"));
    }

    public void writeListLoFile(DataToFile data, File file) throws JsonProcessingException {
        String serializedList = serializeToJSON(data);
        String encryptedData = encryptData(serializedList,
                properties.getProperty("jdbc.encryptPassword"));
        writeToFile(encryptedData, file.toPath());
    }
    public void writeDataToFile(DataToFile data, File file) throws JsonProcessingException {
        String serializedList = serializeToJSON(data);
        String encryptedData = encryptData(serializedList,
                properties.getProperty("jdbc.encryptPassword"));
        writeToFile(encryptedData, file.toPath());
    }
    public ArrayList<Pupil> readListFromFile (File file) throws IOException {
        String textFromFile = readFromFile(file);
        String decryptedData = decryptData(textFromFile, properties.getProperty("jdbc.encryptPassword"));
        return deserializeFromJSON(decryptedData);
    }
    public DataToFile readDataFromFile (File file) throws IOException {
        String textFromFile = readFromFile(file);
        String decryptedData = decryptData(textFromFile, properties.getProperty("jdbc.encryptPassword"));
        return deserializeDataFromJSON(decryptedData);
    }
    private static String serializeToJSON(ArrayList<Pupil> pupilsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(pupilsList);
    }
    private String serializeToJSON(DataToFile data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(data);
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

    private String readFromFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }
    private String decryptData(String encryptedData, String password) {
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

    private ArrayList<Pupil> deserializeFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(jsonData, new TypeReference<ArrayList<Pupil>>() {});
    }
    private DataToFile deserializeDataFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(jsonData, new TypeReference<DataToFile>() {
        });
    }

    private static Pupil deserializePupilFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<Pupil>() {
        });
    }


}
