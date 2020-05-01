package nl.jonathandegier.lingowords.domain;

import java.util.List;

public interface WordSource {

    public List<Word> importWords();
}
