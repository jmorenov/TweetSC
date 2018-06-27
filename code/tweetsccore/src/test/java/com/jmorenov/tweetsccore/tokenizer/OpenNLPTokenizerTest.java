package com.jmorenov.tweetsccore.tokenizer;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class OpenNLPTokenizerTest {
    @Test
    public void test() throws IOException {
        String text = "Holo mi nombre es Javier xD.";
        OpenNLPTokenizer openNLPTokenizer = new OpenNLPTokenizer();

        assertEquals("failure - the post is incorrect", "holo", openNLPTokenizer.getTokens(text).get(0));
    }
}
