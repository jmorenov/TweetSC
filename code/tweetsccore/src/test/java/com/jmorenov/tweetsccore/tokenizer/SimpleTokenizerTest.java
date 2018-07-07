package com.jmorenov.tweetsccore.tokenizer;

import com.jmorenov.tweetsccore.extra.Token;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SimpleTokenizerTest {
    @Test
    public void simpleTokenizerShouldReturnTheCorrectResult() {
        //String text = "Hola mi nombre es Javier vivo en España, y creo que www.google.com http://t.com/jsfkjs Soy_javi. javier_hola@gmail.com @holahola #hashtagtwitter.";
        String text = "Hola, me llamo Javier... pero mi nick es javier.merea.";
        SimpleTokenizer simpleTokenizer = new SimpleTokenizer();

        List<Token> tokens = simpleTokenizer.getTokens(text);

        assertEquals("failure - the tokenizer result is incorrect", 12, tokens.size());
        assertEquals("failure - the tokenizer result is incorrect", "Hola", tokens.get(0).getText());
        assertEquals("failure - the tokenizer result is incorrect", 0, tokens.get(0).getStartPosition());
        assertEquals("failure - the tokenizer result is incorrect", 4, tokens.get(0).getEndPosition());
        assertEquals("failure - the tokenizer result is incorrect", ",", tokens.get(1).getText());
        assertEquals("failure - the tokenizer result is incorrect", 4, tokens.get(1).getStartPosition());
        assertEquals("failure - the tokenizer result is incorrect", 5, tokens.get(1).getEndPosition());
    }
}
