package nl.jonathandegier.lingowords.infrastructure.target;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.domain.WordTarget;
import nl.jonathandegier.lingowords.infrastructure.target.dto.WordDTO;
import nl.jonathandegier.lingowords.infrastructure.database.postgres.PostgresWordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@DisplayName("Test Database target")
public class DatabaseWordTargetTest {

    private static List<Word> words = new ArrayList<>(
            asList(
                    new Word("word"),
                    new Word("balls"),
                    new Word("tenten"),
                    new Word("telescoop"),
                    new Word("cube")
            )
    );

    private static List<WordDTO> wordDtos = new ArrayList<>(
            asList(
                    new WordDTO("word", 4),
                    new WordDTO("balls", 5),
                    new WordDTO("tenten", 6),
                    new WordDTO("telescoop", 9),
                    new WordDTO("cube", 4)
            )
    );

    @Test
    @DisplayName("Store words")
    void test_store_words() {
        var repositoryMock = mock(PostgresWordRepository.class);

        WordTarget target = new DatabaseWordTarget(repositoryMock);

        target.storeWords(words);

        verify(repositoryMock, times(1)).store(wordDtos);
    }
}
