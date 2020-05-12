package nl.jonathandegier.lingowords.infrastructure.database.postgres;

import nl.jonathandegier.lingowords.infrastructure.target.WordRepository;
import nl.jonathandegier.lingowords.infrastructure.database.dto.WordDTO;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class PostgresWordRepositoryTest {

    static List<WordDTO> wordDtos = new ArrayList<WordDTO>(
            asList(
                    new WordDTO("word", 4),
                    new WordDTO("balls", 5),
                    new WordDTO("tenten", 6),
                    new WordDTO("telescoop", 9),
                    new WordDTO("cube", 4)
            )
    );

    @Test
    void test_store() {

        var entityManagerMock = mock(EntityManager.class);

        WordRepository repository = new PostgresWordRepository(entityManagerMock);

        repository.store(wordDtos);

        verify(entityManagerMock, times(5)).persist(any());
    }
}
