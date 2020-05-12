package integration;

import nl.jonathandegier.lingowords.application.WordProcessor;
import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.domain.WordSource;
import nl.jonathandegier.lingowords.domain.WordTarget;
import nl.jonathandegier.lingowords.infrastructure.database.postgres.PostgresWordRepository;
import nl.jonathandegier.lingowords.infrastructure.serialization.TxtFileWordSerialization;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordSource;
import nl.jonathandegier.lingowords.infrastructure.target.DatabaseWordTarget;
import nl.jonathandegier.lingowords.infrastructure.target.WordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.FileContent;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Integration Tests")
public class IntegrationTests {

    private static Stream<Arguments> files() {
        return Stream.of(
                // file path, file content, amount of good words
                Arguments.of("testWords.txt", FileContent.testWordsTxtWordList(), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("files")
    @DisplayName("Test Import")
    void test_import(String filePath, List<Word> expectedWordList) {
        var targetMock = mock(WordTarget.class);

        FileWordDeserializer deserializer = new TxtFileWordSerialization();
        WordSource source = new FileWordSource(deserializer, filePath);
        WordProcessor processor = new WordProcessor(source, targetMock);

        processor.importWords();

        assertEquals(expectedWordList, processor.getWords());
    }

    @ParameterizedTest
    @MethodSource("files")
    @DisplayName("Test Export")
    void test_export(String filePath, List<Word> wordList) {
        var sourceMock = mock(WordSource.class);
        when(sourceMock.importWords()).thenReturn(wordList);

        var entityManagerMock = mock(EntityManager.class);

        WordRepository repository = new PostgresWordRepository(entityManagerMock);
        WordTarget target = new DatabaseWordTarget(repository);
        WordProcessor processor = new WordProcessor(sourceMock, target);

        processor.importWords();
        processor.storeWords();

        verify(entityManagerMock, times(wordList.size())).persist(any());
    }

    @ParameterizedTest
    @MethodSource("files")
    @DisplayName("Test System")
    void test_system(String filePath, List<Word> wordList, int goodWords) {
        var entityManagerMock = mock(EntityManager.class);

        WordRepository repository = new PostgresWordRepository(entityManagerMock);
        WordTarget target = new DatabaseWordTarget(repository);

        FileWordDeserializer deserializer = new TxtFileWordSerialization();
        WordSource source = new FileWordSource(deserializer, filePath);
        WordProcessor processor = new WordProcessor(source, target);

        processor.importWords();
        processor.checkwords();
        processor.storeWords();

        verify(entityManagerMock, times(goodWords)).persist(any());
    }
}
