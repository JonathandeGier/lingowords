package nl.jonathandegier.lingowords.infrastructure.serialization;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.FileContent;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test txt words deserialization")
public class TxtFileWordsSerializationTest {

    @Test
    @DisplayName("Test Deserialize")
    public void test_deserialize() {
        FileWordDeserializer deserializer = new TxtFileWordSerialization();

        List<Word> words = deserializer.deserialize(getFile("testWords.txt"));

        assertEquals(FileContent.testWordsTxtWordList(), words);
    }

    @Test
    @DisplayName("Test IO Exception")
    public void test_io_exception() {
        FileWordDeserializer deserializer = new TxtFileWordSerialization();

        assertThrows(RuntimeException.class, () -> {
            deserializer.deserialize(new File("non-existing-file.txt"));
        });
    }

    private File getFile(String path) {
        return new File(getClass().getClassLoader().getResource(path).getFile());
    }
}
