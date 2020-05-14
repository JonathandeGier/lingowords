package nl.jonathandegier.lingowords.infrastructure.target.dto;

import nl.jonathandegier.lingowords.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test WordDTO")
public class WordDTOTest {

    private static Stream<Arguments> testEqualWords() {
        return Stream.of(
                Arguments.of(new WordDTO("word", 4), new WordDTO("word", 4), true),   // words  are the same
                Arguments.of(new WordDTO("word", 4), new WordDTO("test", 4), false),  // words are different
                Arguments.of(new WordDTO("word", 4), new WordDTO("word", 5), false),  // lengths are different
                Arguments.of(new WordDTO("word", 4), new Word("word"), false)                      // different object
        );
    }

    @DisplayName("Test Equals")
    @ParameterizedTest
    @MethodSource("testEqualWords")
    void test_equals(WordDTO word, Object other, boolean expected) {
        assertEquals(expected, word.equals(other));
    }

    @DisplayName("Test Hash Code")
    @ParameterizedTest
    @MethodSource("testEqualWords")
    void test_hash_code(WordDTO word, Object other, boolean expected) {
        assertEquals(expected,  word.hashCode() == other.hashCode());
    }

    @Test
    @DisplayName("Test get word")
    void test_get_word() {
        WordDTO word = new WordDTO("word", 4);

        assertEquals("word", word.getWord());
    }

    @Test
    @DisplayName("Test get length")
    void test_get_length() {
        WordDTO word = new WordDTO("word", 4);

        assertEquals(4, word.getLength());
    }
}
