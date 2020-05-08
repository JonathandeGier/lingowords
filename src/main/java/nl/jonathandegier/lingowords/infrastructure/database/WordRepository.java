package nl.jonathandegier.lingowords.infrastructure.database;

import nl.jonathandegier.lingowords.infrastructure.database.dto.WordDTO;

import java.util.List;

public interface WordRepository {

    void store(List<WordDTO> words);
}
