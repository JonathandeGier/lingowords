package nl.jonathandegier.lingowords.infrastructure.source;

import nl.jonathandegier.lingowords.domain.Word;

import java.io.File;
import java.util.List;

public interface FileWordDeserializer {

    public List<Word> deserialize(File file);
}
