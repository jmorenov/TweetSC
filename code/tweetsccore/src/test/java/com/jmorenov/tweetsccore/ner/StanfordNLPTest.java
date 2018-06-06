package com.jmorenov.tweetsccore.ner;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTest {
    @Test
    public void test1 () {
        String text = "Hole mi nombra es Javier.";
        StanfordNLP stanfordNLP = new StanfordNLP(text);
        ArrayList<String> tokens = stanfordNLP.getTokens();
        ArrayList<String> tokenRegex = stanfordNLP.getTokensRegex();

        assertEquals("Error", "Mensaje esperado 1", "Mensaje esperado 1");
    }
}