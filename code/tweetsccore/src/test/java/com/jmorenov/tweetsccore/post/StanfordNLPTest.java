package com.jmorenov.tweetsccore.post;

import com.jmorenov.tweetsccore.post.StanfordNLP;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class StanfordNLPTest {
    @Test
    public void test1 () {
        String text = "hole mi nombra es javier.";
        StanfordNLP stanfordNLP = new StanfordNLP(text);
        String tags = stanfordNLP.getTags();

        assertEquals("Error", "Mensaje esperado 1", "Mensaje esperado 1");
    }
}
