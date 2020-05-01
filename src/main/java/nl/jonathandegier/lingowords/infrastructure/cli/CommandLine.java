package nl.jonathandegier.lingowords.infrastructure.cli;

import nl.jonathandegier.lingowords.application.WordProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLine implements CommandLineRunner {

    private WordProcessor wordApplication;

    public CommandLine(WordProcessor processor) {
        this.wordApplication = processor;
    }

    @Override
    public void run(String... args) throws Exception {
        wordApplication.importWords();
        wordApplication.checkwords();
        wordApplication.storeWords();
    }


}
