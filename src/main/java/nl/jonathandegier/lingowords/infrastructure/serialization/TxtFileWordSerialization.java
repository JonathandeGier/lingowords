package nl.jonathandegier.lingowords.infrastructure.serialization;

import nl.jonathandegier.lingowords.domain.Word;
import nl.jonathandegier.lingowords.infrastructure.source.FileWordDeserializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileWordSerialization implements FileWordDeserializer {

    @Override
    public List<Word> deserialize(File file) {
        List<Word> words = new ArrayList<Word>();

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                words.add(new Word(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
