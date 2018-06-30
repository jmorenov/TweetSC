package com.jmorenov.tweetsccore.tokenizer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FreelingTokenizerTest {
    @Test
    public void freelingTokenizerShouldReturnTheCorrectResult() {
        String text = "Hola mi nombre es Javier xD.";
        FreelingTokenizer freelingTokenizer = new FreelingTokenizer();

        assertEquals("failure - the post is incorrect", "Hola", freelingTokenizer.getTokens(text).get(0));
    }
}