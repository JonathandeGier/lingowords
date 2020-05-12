package nl.jonathandegier.lingowords.infrastructure.database.postgres;

import nl.jonathandegier.lingowords.infrastructure.target.WordRepository;
import nl.jonathandegier.lingowords.infrastructure.database.dto.WordDTO;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class PostgresWordRepository implements WordRepository {

    private EntityManager entityManager;

    public PostgresWordRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void store(List<WordDTO> words) {
        for (WordDTO word : words) {
            this.entityManager.persist(word);
        }

    }
}
