package com.jmorenov.spellchecker;

import org.junit.Test;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SpellCheckerTest {
    @Test
    public void spellcheckerShouldReturnWordsOfAText() {
        String text = "Hola mi nombre es Javier.";
        SpellChecker spellChecker = new SpellChecker(text);
        ArrayList<String> words = spellChecker.words();

        assertEquals("failure - strings are not equal", "Hola-1", words.get(0));
    }
}