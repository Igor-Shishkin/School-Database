package schoolDatabaseProgram.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;



public class WriteReadDataToFile {
    Properties properties;


    public WriteReadDataToFile() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/jdbc.properties"));
    }

    public void writeDataToFile(DataToFile data, File file) throws JsonProcessingException {
        String serializedList = serializeToJSON(data);
        String encryptedData = encryptData(serializedList,
                properties.getProperty("jdbc.encryptPassword"));
        writeToFile(encryptedData, file.toPath());
    }
    public DataToFile readDataFromFile (File file) throws IOException {
        String textFromFile = readFromFile(file);
        String decryptedData = decryptData(textFromFile, properties.getProperty("jdbc.encryptPassword"));
        return deserializeDataFromJSON(decryptedData);
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
            e.printStackTrace();
            return "Decryption failed: " + e.getMessage();
        }
    }
    private DataToFile deserializeDataFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(jsonData, new TypeReference<DataToFile>() {
        });
    }
}
