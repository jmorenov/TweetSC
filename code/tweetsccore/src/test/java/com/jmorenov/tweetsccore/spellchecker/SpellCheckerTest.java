package com.jmorenov.tweetsccore.spellchecker;

import org.junit.Test;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SpellCheckerTest {
    @Test
    public void spellcheckerShouldReturnWordsOfAText() {
        String text = "Hola mi nombre es Javier.";
        SpellChecker spellChecker = new SpellChecker(text);
        ArrayList<String> words = spellChecker.words();

        assertEquals("failure - dimensions are not equal", 6, words.size());
        assertEquals("failure - strings are not equal", "Hola-1", words.get(0));
        assertEquals("failure - strings are not equal", "mi-2", words.get(1));
        assertEquals("failure - strings are not equal", "nombre-3", words.get(2));
        assertEquals("failure - strings are not equal", "es-4", words.get(3));
        assertEquals("failure - strings are not equal", "Javier-5", words.get(4));
        assertEquals("failure - strings are not equal", ".-6", words.get(5));
    }
}