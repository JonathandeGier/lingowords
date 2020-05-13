package nl.jonathandegier.lingowords.infrastructure.serialization;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.FileContent;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test JSON words deserialization")
public class JsonFileWordSerializationTest {

    @Test
    @DisplayName("Test Deserialize")
    public void test_deserialize() {
        FileWordDeserializer deserializer = new JsonFileWordSerialization();

        List<Word> words = deserializer.deserialize(getFile("testWords.json"));

        assertEquals(FileContent.testWordsJsontWordList(), words);
    }

    @Test
    @DisplayName("Test IO Exception")
    public void test_io_exception() {
        FileWordDeserializer deserializer = new JsonFileWordSerialization();

        assertThrows(RuntimeException.class, () -> {
            deserializer.deserialize(new File("non-existing-file.json"));
        });
    }

    private File getFile(String path) {
        return new File(getClass().getClassLoader().getResource(path).getFile());
    }
}
