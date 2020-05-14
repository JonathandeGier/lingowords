package nl.jonathandegier.lingowords.application;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordSource;
import nl.jonathandegier.lingowords.infrastructure.target.DatabaseWordTarget;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Test Word Processor")
public class WordProcessorTest {

    static final List<Word> rawWords = new ArrayList<>(
            List.of(new Word("kast"),           // te kort
                    new Word("rozen"),          // goed
                    new Word("puzzel"),         // goed
                    new Word("opleven"),        // goed
                    new Word("opleveren"),      // te lang
                    new Word("Rozen"),          // begint met hoofdletter
                    new Word("Puzzel"),         // begint met hoofdletter
                    new Word("Opleven"),        // begint met hoofdletter
                    new Word("e-mail"),         // koppelteken
                    new Word("6jarige"),        // cijfer
                    new Word("aan doe"),        // spatie
                    new Word("aan doen"),       // spatie te lang
                    new Word("barré"),          // é
                    new Word("ampère"),         // è
                    new Word("skiën")           // ë
            )
    );

    static final List<Word> goodWords = new ArrayList<>(
            List.of(new Word("rozen"),
                    new Word("puzzel"),
                    new Word("opleven")
            )
    );

    @Test
    @DisplayName("Import words")
    void test_import() {
        var wordSourceMock = mock(FileWordSource.class);
        var wordTargetMock = mock(DatabaseWordTarget.class);

        when(wordSourceMock.importWords()).thenReturn(rawWords);

        var processor = new WordProcessor(wordSourceMock, wordTargetMock);

        processor.importWords();

        assertEquals(rawWords, processor.getWords());
        verify(wordSourceMock, times(1)).importWords();
    }

    @Test
    @DisplayName("Check words")
    void test_check_words() {
        var wordSourceMock = mock(FileWordSource.class);
        var wordTargetMock = mock(DatabaseWordTarget.class);

        when(wordSourceMock.importWords()).thenReturn(rawWords);

        var processor = new WordProcessor(wordSourceMock, wordTargetMock);
        processor.importWords();

        processor.checkwords();

        assertEquals(goodWords, processor.getWords());
    }

    @Test
    @DisplayName("Store/Export words")
    void test_export() {
        var wordSourceMock = mock(FileWordSource.class);
        var wordTargetMock = mock(DatabaseWordTarget.class);

        when(wordSourceMock.importWords()).thenReturn(goodWords);

        var processor = new WordProcessor(wordSourceMock, wordTargetMock);

        processor.importWords();
        processor.storeWords();

        verify(wordTargetMock, times(1)).storeWords(goodWords);
    }
}
