package com.jmorenov.tweetscexecutable;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SpellCheckerRunTest {
    @Test
    public void spellcheckerRunShouldReturnTheCorrectionOfAText() {
        String text = "Hola mi nombre es Javier.";
        String[] args = {"-text", text};

        try {
            SpellCheckerRunResult result = SpellCheckerRun.run(args);

            assertEquals("Hola mi nombre es Javier.", result.result, "failure - strings are not equal");
        } catch (ParseException ex) {
            fail("failure - error parsing the arguments");
        }
    }

    @Test
    public void spellcheckerRunWithTweetSCMethodShouldReturnTheCorrectionOfAText() {
        String text = "Hola mi nombre es Javier.";
        String[] args = {"-text", text, "-method", "TweetSCMethod"};

        try {
            SpellCheckerRunResult result = SpellCheckerRun.run(args);

            assertEquals("Hola mi nombre es Javier.", result.result, "failure - strings are not equal");
        } catch (ParseException ex) {
            fail("failure - error parsing the arguments");
        }
    }

    @Test
    public void spellcheckerRunWithWrongArgumentsShouldThrowAnException() {
        String text = "Hola mi nombre es Javier.";
        String[] args = {"-method", "TweetSCMethod"};
        Throwable exception = Assertions.assertThrows(ParseException.class,() -> SpellCheckerRun.run(args));

        assertEquals("Missing required option: text", exception.getMessage());
    }
}