package com.jmorenov.tweetsccore.method;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class TweetSCMethodTest {
    @Test
    public void tweetSCMethodShouldReturnTheCorrectText() throws IOException {
        String text = "Hola, mi nombre es Javier.";
        Tweet tweet = new Tweet(text);
        TweetSCMethod tweetSCMethod = new TweetSCMethod();
        TweetCorrected tweetCorrected = tweetSCMethod.correctTweet(tweet);

        assertEquals("failure - the correction is incorrect", "Hola, mi nombre es Javier.", tweetCorrected.getCorrectedText());
    }
}