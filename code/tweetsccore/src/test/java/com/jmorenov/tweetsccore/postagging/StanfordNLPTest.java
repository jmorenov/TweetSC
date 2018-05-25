package com.jmorenov.tweetsccore.postagging;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTest {
    @Test
    public void test1 () {
        String text = "Hole mi nombra es Javier.";
        StanfordNLP stanfordNLP = new StanfordNLP(text);
        ArrayList<String> tokenRegex = stanfordNLP.getTokenRegex();

        assertEquals("Error", "Mensaje esperado 1", tokenRegex.get(0));
    }
}