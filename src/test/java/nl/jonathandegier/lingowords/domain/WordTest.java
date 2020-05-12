package nl.jonathandegier.lingowords.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Word (domain object)")
public class WordTest {

    private static Stream<Arguments> testEqualWords() {
        return Stream.of(
                Arguments.of(new Word("word"), new Word("word"), true),     // words  are the same
                Arguments.of(new Word("word"), new Word("test"), false),    // words are different
                Arguments.of(new Word("word"), "word", false)               // different object
        );
    }

    @DisplayName("Test Equals")
    @ParameterizedTest
    @MethodSource("testEqualWords")
    void test_equals(Word word, Object other, boolean expected) {
        assertEquals(expected, word.equals(other));
    }

    @Test
    @DisplayName("Test get word")
    void test_get_word() {
        Word word = new Word("word");

        assertEquals("word", word.getWord());
    }

    @Test
    @DisplayName("Test to string")
    void test_to_string() {
        Word word = new Word("word");

        assertEquals("word", word.toString());
    }
}
