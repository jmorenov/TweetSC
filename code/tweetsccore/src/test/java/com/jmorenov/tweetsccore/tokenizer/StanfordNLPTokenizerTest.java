package com.jmorenov.tweetsccore.tokenizer;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTokenizerTest {
    @Test
    public void test() throws IOException {
        String text = "Holo mi nombrees Javier xD y trabajo para Facebook y Google.";
        StanfordNLPTokenizer openNLPTokenizer = new StanfordNLPTokenizer();

        assertEquals("failure - the post is incorrect", "holo", openNLPTokenizer.getTokens(text).get(0));
    }
}