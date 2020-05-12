package nl.jonathandegier.lingowords.infrastructure.target.dto;

import nl.jonathandegier.lingowords.domain.Word;

import javax.persistence.*;

@Entity
@Table(name = "words", indexes = {@Index(name = "length_index", columnList = "length")})
public class WordDTO {

    @Id
    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "length", nullable = false)
    private int length;

    public WordDTO(String word, int length) {
        this.word = word;
        this.length = length;
    }

    public WordDTO(Word wordObject) {
        this.word = wordObject.getWord();
        this.length = wordObject.getWord().length();
    }

    public String getWord() {
        return word;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordDTO) {
            WordDTO other = (WordDTO) obj;

            return this.word.equals(other.word) && this.length == other.length;
        }

        return false;
    }
}
