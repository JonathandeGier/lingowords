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

        for (Word word : this.words) {
            System.out.println(word);
        }
        System.out.println(this.words.size());
    }

    public void storeWords() {
        // TODO: 1-5-2020
    }

    public String getString() {
        return "Hello from Processor";
    }
}
