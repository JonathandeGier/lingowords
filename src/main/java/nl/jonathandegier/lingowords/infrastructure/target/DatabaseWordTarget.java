package nl.jonathandegier.lingowords.infrastructure.target;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.domain.WordTarget;
import nl.jonathandegier.lingowords.infrastructure.database.dto.WordDTO;

import java.util.ArrayList;
import java.util.List;

public class DatabaseWordTarget implements WordTarget {

    private WordRepository repository;

    public DatabaseWordTarget(WordRepository repository) {
        this.repository = repository;
    }

    public void storeWords(List<Word> words) {
        List<WordDTO> wordsDtoList = new ArrayList<WordDTO>();

        for (Word word : words) {
            wordsDtoList.add(new WordDTO(word));
        }

        repository.store(wordsDtoList);
    }
}
