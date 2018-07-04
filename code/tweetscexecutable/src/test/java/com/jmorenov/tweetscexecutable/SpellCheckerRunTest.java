package com.jmorenov.tweetscexecutable;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SpellCheckerRunTest {
    @Test
    public void spellcheckerRunShouldReturnTheCorrectionOfAText() {
        String text = "Hola mi nombre es Javier.";
        String[] args = {"-text", text};

        assertEquals("failure - strings are not equal", "Hola mi nombre es Javier.", SpellCheckerRun.run(args));
    }

    @Test
    public void spellcheckerRunWithTweetSCMethodShouldReturnTheCorrectionOfAText() {
        String text = "Hola mi nombre es Javier.";
        String[] args = {"-text", text, "-method", "TweetSCMethod"};

        assertEquals("failure - strings are not equal", "Hola mi nombre es Javier.", SpellCheckerRun.run(args));
    }
}