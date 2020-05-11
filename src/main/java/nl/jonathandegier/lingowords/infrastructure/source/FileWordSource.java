package nl.jonathandegier.lingowords.infrastructure.source;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.domain.WordSource;

import java.io.File;
import java.net.URL;
import java.util.List;

public class FileWordSource implements WordSource {

    private FileWordDeserializer deserializer;
    private String fileSource;

    public FileWordSource(FileWordDeserializer deserializer, String fileSource) {
        this.deserializer = deserializer;
        this.fileSource = fileSource;
    }

    public List<Word> importWords() {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(this.fileSource);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        }

        File file = new File(resource.getFile());

        return deserializer.deserialize(file);
    }
}
