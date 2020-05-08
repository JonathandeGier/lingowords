package nl.jonathandegier.lingowords;

import nl.jonathandegier.lingowords.domain.WordSource;
import nl.jonathandegier.lingowords.domain.WordTarget;
import nl.jonathandegier.lingowords.infrastructure.database.WordRepository;
import nl.jonathandegier.lingowords.infrastructure.database.postgres.PostgresWordRepository;
import nl.jonathandegier.lingowords.infrastructure.serialization.TxtFileWordSerialization;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordSource;
import nl.jonathandegier.lingowords.infrastructure.target.DatabaseWordTarget;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class AppConfig {

    @Bean
    public WordSource wordSource(FileWordDeserializer fileDeserializer) {
        return new FileWordSource(fileDeserializer);
    }

    @Bean
    public FileWordDeserializer fileDeserializer() {
        return new TxtFileWordSerialization();
    }

    @Bean
    public WordTarget wordTarget(WordRepository wordRepository) {
        return new DatabaseWordTarget(wordRepository);
    }

    @Bean
    public WordRepository getWordRepository(EntityManager entityManager) {
        return new PostgresWordRepository(entityManager);
    }
}
