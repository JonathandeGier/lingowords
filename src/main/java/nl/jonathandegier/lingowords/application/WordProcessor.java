package nl.jonathandegier.lingowords.application;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.domain.WordSource;
import nl.jonathandegier.lingowords.domain.WordTarget;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordProcessor {

    private WordSource wordSource;
    private WordTarget wordTarget;

    private List<Word> words;

    public WordProcessor(WordSource wordSource, WordTarget wordTarget) {
        this.wordSource = wordSource;
        this.wordTarget = wordTarget;
    }

    public void importWords() {
        this.words = wordSource.importWords();
    }

    public void checkwords() {
        List<Word> checkedWords = new ArrayList<Word>();

        for (Word word : this.words) {
            if (word.getWord().matches("^[a-z]{5,7}$")) {
                checkedWords.add(word);
            }
        }

        this.words = checkedWords;
    }

    public void storeWords() {
        wordTarget.storeWords(this.words);
    }

    public List<Word> getWords() {
        return words;
    }
}
