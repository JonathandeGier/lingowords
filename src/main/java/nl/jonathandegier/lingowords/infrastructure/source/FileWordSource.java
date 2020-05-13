package nl.jonathandegier.lingowords.infrastructure.source;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.domain.WordSource;
import nl.jonathandegier.lingowords.infrastructure.serialization.SerializationStrategyProvider;

import java.io.File;
import java.net.URL;
import java.util.List;

public class FileWordSource implements WordSource {

    private String fileSource;

    public FileWordSource(String fileSource) {
        this.fileSource = fileSource;
    }

    public List<Word> importWords() {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(this.fileSource);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        }

        File file = new File(resource.getFile());

        FileWordDeserializer deserializer = SerializationStrategyProvider.getDeserializer(file.getName());

        return deserializer.deserialize(file);
    }
}
