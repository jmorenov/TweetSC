package com.jmorenov.tweetsccore.tokenizer;

import com.jmorenov.tweetsccore.tokenizer.FreelingTokenizer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FreelingTokenizerTest {
    @Test
    public void freelingTokenizerShouldReturnTheCorrectResult() {
        String text = "Hola mi nombre es Javier vivo en España, y creo que www.google.com http://t.com/jsfkjs Soy_javi. javier_hola@gmail.com @holahola #hashtagtwitter.";
        FreelingTokenizer freelingTokenizer = new FreelingTokenizer();

        assertEquals("failure - the tokenizer result is incorrect", "Hola", freelingTokenizer.getTokens(text).get(0));
    }
}