package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class GenerateListOfPupils {
    public static void main(String[] args) throws IOException {
        GeneratePupilData generatePupilData = new GeneratePupilData();
        ArrayList<Pupil> listOfPupils = new ArrayList<>(200);
        for (int i = 0; i < 100; i++) {
            listOfPupils.add(generatePupilData.generatePupil());
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/jdbc.properties"));

        GenerateListOfPupils generate = new GenerateListOfPupils();

        String serializedList = generate.serializeToJSON(listOfPupils);
        String encryptedData = generate.encryptData(serializedList, properties.getProperty("jdbc.encryptPassword"));
        generate.WriteToFile(encryptedData, Paths.get("ProtectedData.txt"));


    }

    private String encryptData(String text, String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        return encryptor.encrypt(text);
    }

    private void WriteToFile(String data, Path path) {

        try {
            Files.write(path, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String serializeToJSON(ArrayList<Pupil> pupilsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pupilsList);
    }

    private String serializePupilToJSON(Pupil pupil) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pupil);
    }

    private String readFromFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
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

    private List<Pupil> deserializeFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<ArrayList<Pupil>>() {
        });
    }

    private Pupil deserializePupilFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<Pupil>() {
        });
    }

}
