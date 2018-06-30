package com.jmorenov.tweetsccore.tokenizer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class NGramTokenizerTest {
    @Test
    public void freelingTokenizerShouldReturnTheCorrectResult() {
        String text = "Hol";
        NGramTokenizer nGramTokenizer = new NGramTokenizer();

        assertEquals("failure - the post is incorrect", "Hola", nGramTokenizer.getTokens(text).get(0));
    }
}
