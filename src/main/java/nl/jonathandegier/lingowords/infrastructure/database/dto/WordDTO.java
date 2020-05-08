package nl.jonathandegier.lingowords.infrastructure.database.dto;

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

    public WordDTO() {}
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
}
