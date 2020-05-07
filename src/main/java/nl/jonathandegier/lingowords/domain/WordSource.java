package nl.jonathandegier.lingowords.domain;

import java.util.List;

public interface WordSource {

    List<Word> importWords();
}
