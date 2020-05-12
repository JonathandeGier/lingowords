package nl.jonathandegier.lingowords.infrastructure.target;

import nl.jonathandegier.lingowords.infrastructure.target.dto.WordDTO;

import java.util.List;

public interface WordRepository {

    void store(List<WordDTO> words);
}
