package nl.jonathandegier.lingowords.infrastructure.source;

import nl.jonathandegier.lingowords.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.FileContent;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test File word source")
public class FileWordSourceTest {

    private static Stream<Arguments> files() {
        return Stream.of(
                Arguments.of("testWords.txt", FileContent.testWordsTxtWordList()),
                Arguments.of("testWords.json", FileContent.testWordsJsontWordList())
        );
    }

    @ParameterizedTest
    @MethodSource("files")
    @DisplayName("Test import files")
    public void test_import_file(String path, List<Word> expected) {
        FileWordSource wordSource = new FileWordSource(path);

        List<Word> returnedWords = wordSource.importWords();

        assertEquals(expected, returnedWords);
    }

    @Test
    @DisplayName("Test file not found")
    public void test_import_file_not_found() {
        FileWordSource wordSource = new FileWordSource("non-existing-file.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            wordSource.importWords();
        });
    }

    private File getFile(String path) {
        return new File(getClass().getClassLoader().getResource(path).getFile());
    }
}
