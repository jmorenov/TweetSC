package com.jmorenov.tweetsccore.ner;

import com.jmorenov.tweetsccore.post.StanfordNLPPOST;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTest {
    @Test
    public void test1 () {
        String text = "Hole mi nombra es Javier.";
        StanfordNLPPOST stanfordNLP = new StanfordNLPPOST(text);
        String tags = stanfordNLP.getTags();

        assertEquals("Error", "Mensaje esperado 1", "Mensaje esperado 1");
    }
}