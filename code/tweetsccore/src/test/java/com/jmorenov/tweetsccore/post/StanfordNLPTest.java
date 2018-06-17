package com.jmorenov.tweetsccore.post;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTest {
    @Test
    public void test1 () {
        String text = "hole mi nombra es javier.";
        StanfordNLPPOST stanfordNLP = new StanfordNLPPOST(text);
        String tags = stanfordNLP.getTags();

        assertEquals("Error", "Mensaje esperado 1", "Mensaje esperado 1");
    }
}
