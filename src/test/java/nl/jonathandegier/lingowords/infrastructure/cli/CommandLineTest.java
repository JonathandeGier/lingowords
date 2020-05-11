package nl.jonathandegier.lingowords.infrastructure.cli;

import nl.jonathandegier.lingowords.application.WordProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@DisplayName("Test Command line")
public class CommandLineTest {

    @Test
    public void test_run() throws Exception {
        var processorMock = mock(WordProcessor.class);

        CommandLine commandLine = new CommandLine(processorMock);

        commandLine.run();

        verify(processorMock, times(1)).importWords();
        verify(processorMock, times(1)).checkwords();
        verify(processorMock, times(1)).storeWords();
    }
}
