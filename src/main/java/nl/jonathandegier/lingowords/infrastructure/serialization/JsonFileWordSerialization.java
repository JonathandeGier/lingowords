package nl.jonathandegier.lingowords.infrastructure.serialization;

import com.google.gson.Gson;
import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonFileWordSerialization implements FileWordDeserializer {

    private Gson gson = new Gson();

    @Override
    public List<Word> deserialize(File file) {
        List<Word> words = new ArrayList<>();

        try {
            String[] stringWords = gson.fromJson(new FileReader(file), String[].class);

            for (String word : stringWords) {
                words.add(new Word(word));
            }

        } catch (Exception e) {
            throw new SerializationException("Could not deserialize json file");
        }

        return words;
    }
}
