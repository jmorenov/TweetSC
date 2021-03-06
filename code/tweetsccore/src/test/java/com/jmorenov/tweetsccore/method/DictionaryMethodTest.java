package com.jmorenov.tweetsccore.method;

import com.jmorenov.tweetsccore.twitter.Tweet;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class DictionaryMethodTest {
    @Test
    public void correctTextForTweetNormShouldReturnTheCorrectResult() throws IOException {
        String text = "Hola mi nombre es Javier.";
        Tweet tweet = new Tweet(text);
        DictionaryMethod dictionaryMethod = new DictionaryMethod();

        assertEquals("failure - the correction is incorrect", "Hola mi nombre es Javier.", dictionaryMethod.correctTweet(tweet).getCorrectedText());
    }
}