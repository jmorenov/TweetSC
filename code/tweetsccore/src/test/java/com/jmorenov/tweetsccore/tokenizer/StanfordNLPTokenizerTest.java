package com.jmorenov.tweetsccore.tokenizer;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTokenizerTest {
    @Test
    public void stanfordNLPTokenizerShouldReturnTheCorrectResult() throws IOException {
        String text = "Hola mi nombre es Javier xD.";
        StanfordNLPTokenizer stanfordNLPTokenizer = new StanfordNLPTokenizer();

        assertEquals("failure - the tokenizer result is incorrect", "Hola", stanfordNLPTokenizer.getTokens(text).get(0));
    }
}