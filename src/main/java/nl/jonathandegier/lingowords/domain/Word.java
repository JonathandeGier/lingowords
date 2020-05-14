package nl.jonathandegier.lingowords.domain;

public class Word {
    private static final int hashBase = 1652387;

    private String lingoWord;

    public Word(String word) {
        this.lingoWord = word;
    }

    public String getWord() {
        return this.lingoWord;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word) {
            Word otherWord = (Word) obj;

            return this.lingoWord.equals(otherWord.lingoWord);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return hashBase + this.lingoWord.hashCode();
    }

    public String toString() {
        return this.lingoWord;
    }
}
