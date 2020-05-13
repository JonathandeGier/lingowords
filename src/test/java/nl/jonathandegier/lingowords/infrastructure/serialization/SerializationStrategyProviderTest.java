package nl.jonathandegier.lingowords.infrastructure.serialization;

import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test serialization strategy provider")
public class SerializationStrategyProviderTest {

    @Test
    void test_get_txt_deserializer() {
        FileWordDeserializer deserializer = SerializationStrategyProvider.getDeserializer("testWords.txt");
        assertTrue(deserializer instanceof TxtFileWordSerialization);
    }

    @Test
    void test_get_json_deserializer() {
        FileWordDeserializer deserializer = SerializationStrategyProvider.getDeserializer("testWords.json");
        assertTrue(deserializer instanceof JsonFileWordSerialization);
    }

    @Test
    void test_get_unknown_extension() {
        assertThrows(IllegalArgumentException.class, () -> {
            SerializationStrategyProvider.getDeserializer("testWords.custom");
        });
    }
}
