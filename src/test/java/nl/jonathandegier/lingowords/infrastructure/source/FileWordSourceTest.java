package nl.jonathandegier.lingowords.infrastructure.source;

import nl.jonathandegier.lingowords.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Test File word source")
public class FileWordSourceTest {

    private static Stream<Arguments> files() {
        return Stream.of(
                Arguments.of("testWords.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("files")
    @DisplayName("Test import files")
    public void test_import_file(String path) {
        var deserializer = mock(FileWordDeserializer.class);
        var returnList = new ArrayList<Word>( asList(new Word("testWord")) );
        when(deserializer.deserialize(any(File.class))).thenReturn(returnList);

        FileWordSource wordSource = new FileWordSource(deserializer, path);

        List<Word> returnedWords = wordSource.importWords();

        verify(deserializer).deserialize(getFile(path));
        assertEquals(returnList, returnedWords);
    }

    @Test
    @DisplayName("Test file not found")
    public void test_import_file_not_found() {
        var deserializer = mock(FileWordDeserializer.class);
        when(deserializer.deserialize(any(File.class))).thenReturn(new ArrayList<Word>());

        FileWordSource wordSource = new FileWordSource(deserializer, "non-existing-file.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            wordSource.importWords();
        });
    }

    private File getFile(String path) {
        return new File(getClass().getClassLoader().getResource(path).getFile());
    }
}
