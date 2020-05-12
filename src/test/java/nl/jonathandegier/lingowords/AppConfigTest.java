package nl.jonathandegier.lingowords;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    private AppConfig appConfig = new AppConfig();

    @Test
    void test_get_word_source() {
        assertNotNull(appConfig.getwordSource(null));
    }

    @Test
    void test_get_file_deserializer() {
        assertNotNull(appConfig.getfileDeserializer());
    }

    @Test
    void test_get_word_target() {
        assertNotNull(appConfig.getwordTarget(null));
    }

    @Test
    void test_get_word_repository() {
        assertNotNull(appConfig.getWordRepository(null));
    }
}
