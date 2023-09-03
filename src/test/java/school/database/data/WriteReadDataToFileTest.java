package school.database.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import school.database.data.objects.Pupil;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriteReadDataToFileTest {

    @Test
    void serializeToJSON() throws IOException {
        DataToFile data = new DataToFile(/* Initialize your DataToFile object here */);
        WriteReadDataToFile writeReadDataToFile = new WriteReadDataToFile();
        String json = writeReadDataToFile.serializeToJSON(data);
        assertThat(json).isNotNull();
        assertThat(isValidJson(json)).isTrue();
    }

    private boolean isValidJson(String json) {
        try {
            new ObjectMapper().readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    @Test
    void encryptData() throws IOException {
        WriteReadDataToFile writeReadDataToFile = new WriteReadDataToFile();

        String text = "Some information";
        String password = "MySecretPassword";

        String encryptedText = writeReadDataToFile.encryptData(text, password);

        assertThat(encryptedText)
                .isNotNull()
                .isNotEqualTo(text);
    }

    @Test
    void writeToFileandRead() throws IOException {
        WriteReadDataToFile writeReadDataToFile = new WriteReadDataToFile();

        DataToFile data = new DataToFile();
        Pupil pupil = new Pupil();
        pupil.setName("Jan");
        pupil.setSurname("Podolecki");
        pupil.setPesel("12345678901");
        pupil.setId(13);
        pupil.setGrade(4);
        data.setListOfPupils(new ArrayList<>(List.of(pupil)));

        String json = writeReadDataToFile.serializeToJSON(data);
        String password = "MySecretPassword";
        String encryptedText = writeReadDataToFile.encryptData(json, password);

        writeReadDataToFile.writeToFile(encryptedText, Path.of("Text.txt"));

        File file = new File("Text.txt");
        String fromFile = writeReadDataToFile.readFromFile(file);

        String decryptedText = writeReadDataToFile.decryptData(fromFile, password);

        DataToFile dataFromFile = writeReadDataToFile.deserializeDataFromJSON(decryptedText);

        assertThat(file).isNotEmpty();
        assertThat(dataFromFile)
                .isNotNull()
                        .isEqualTo(data);


        file.delete();
    }


    @Test
    void decryptData() throws IOException {
        WriteReadDataToFile writeReadDataToFile = new WriteReadDataToFile();

        String text = "Some information";
        String password = "MySecretPassword";

        String encryptedText = writeReadDataToFile.encryptData(text, password);

        String decryptedText = writeReadDataToFile.decryptData(encryptedText, password);

        assertThat(decryptedText)
                .isNotNull()
                .isNotEqualTo(encryptedText);
    }
}