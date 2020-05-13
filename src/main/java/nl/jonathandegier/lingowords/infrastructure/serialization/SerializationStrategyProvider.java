package nl.jonathandegier.lingowords.infrastructure.serialization;

import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;
import org.apache.commons.io.FilenameUtils;

import java.util.HashMap;
import java.util.Map;

public class SerializationStrategyProvider {

    private static final Map<String, FileWordDeserializer> strategies = new HashMap<String, FileWordDeserializer>() {{
        put("txt", new TxtFileWordSerialization());
        put("json", new JsonFileWordSerialization());
    }};

    public static FileWordDeserializer getDeserializer(String filename) {
        String extension = FilenameUtils.getExtension(filename);

        FileWordDeserializer strategy = strategies.get(extension.toLowerCase());

        if (strategy == null) {
            throw new IllegalArgumentException("Unknown file extension");
        }

        return strategy;
    }
}
