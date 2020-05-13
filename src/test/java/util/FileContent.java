package util;

import nl.jonathandegier.lingowords.domain.Word;

import java.util.ArrayList;
import java.util.List;

public class FileContent {
    public static List<Word> testWordsTxtWordList() {
        return new ArrayList<Word>(List.of(
                new Word("14-jarig"),
                new Word("broedvogeltelling"),
                new Word("broedzorg"),
                new Word("broei"),
                new Word("broeibak"),
                new Word("broeibed"),
                new Word("broeien"),
                new Word("Jordanier"),
                new Word("jota"),
                new Word("jou"),
                new Word("Jouen")
        ));
    }

    public static List<Word> testWordsJsontWordList() {
        return new ArrayList<Word>(List.of(
                new Word("14-jarig"),
                new Word("broedvogeltelling"),
                new Word("broedzorg"),
                new Word("broei"),
                new Word("broeibak"),
                new Word("broeibed"),
                new Word("broeien"),
                new Word("Jordanier"),
                new Word("jota"),
                new Word("jou"),
                new Word("Jouen")
        ));
    }
}
