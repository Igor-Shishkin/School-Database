package database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

public class WriteDataToFile {
    public static void main(String[] args) throws IOException {
        GeneratePupilData generate = new GeneratePupilData();
        Pupil pupil1 = generate.generatePupil();
        Pupil pupil2 = generate.generatePupil();
        Pupil pupil3 = generate.generatePupil();
        List<Pupil> listOfPupils = new ArrayList<>(5);
        listOfPupils.add(pupil1);
        listOfPupils.add(pupil2);
        listOfPupils.add(pupil3);
        String serializedList = serializeToJSON(listOfPupils);
        String password = "0000";
        Path filePath = Paths.get("ProtectedData.txt");

        String encryptedData = encryptData(serializedList, password);
        WriteToFile(encryptedData, filePath);

        String stringFromFile = readFromFile(filePath);
        String decryptedString = decryptData(stringFromFile, password);
        List<Pupil> listFromString = deserializeFromJSON(decryptedString);

        listFromString.forEach(System.out::println);

    }
    private static String encryptData (String text, String password) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        return encryptor.encrypt(text);
    }
    private static void WriteToFile(String data, Path path) {

        try {
            Files.write(path, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String serializeToJSON (List<Pupil> pupilsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(pupilsList);
    }

    public static String readFromFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }
    public static String decryptData (String encryptedData, String password) {
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
    private static List<Pupil> deserializeFromJSON(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<List<Pupil>>() {});
    }


}
