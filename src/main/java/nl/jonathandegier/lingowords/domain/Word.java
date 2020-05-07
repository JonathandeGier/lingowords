package nl.jonathandegier.lingowords.domain;

public class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word otherWord = (Word) obj;

            return this.word.equals(otherWord.word);
        }

        return false;
    }

    public String toString() {
        return this.word;
    }
}
