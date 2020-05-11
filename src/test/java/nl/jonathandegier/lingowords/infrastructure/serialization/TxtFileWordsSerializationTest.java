package nl.jonathandegier.lingowords.infrastructure.serialization;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Txt words deserialization test")
public class TxtFileWordsSerializationTest {

    public static List<Word> testWordsTxtWordList() {
        return new ArrayList<Word>(List.of(
                new Word("14-jarig"),
                new Word("broedvogeltelling"),
                new Word("broedzorg"),
                new Word("broei"),
                new Word("broeibak"),
                new Word("broeibed"),
                new Word("broeien"),
                new Word("Jordanier"),
                new Word("jota"),
                new Word("jou"),
                new Word("jouen")
        ));
    }

    @Test
    public void test_deserialize() {
        FileWordDeserializer deserializer = new TxtFileWordSerialization();

        List<Word> words = deserializer.deserialize(getFile("testWords.txt"));

        assertEquals(testWordsTxtWordList(), words);
    }


    private File getFile(String path) {
        return new File(getClass().getClassLoader().getResource(path).getFile());
    }
}
