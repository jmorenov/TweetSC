package com.jmorenov.tweetsccore.spellchecker;

import com.jmorenov.tweetsccore.method.DictionaryMethod;
import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class SpellCheckerTest {
    @Test
    public void spellCheckerWithDictionaryMethodAndCorrectTextMethodShouldReturnTheCorrectText() throws IOException {
        String text = "Hola mi nombre es Javier.";
        SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());
        String textCorrected = spellChecker.correctText(text);

        assertEquals("failure - the correction is incorrect", "Hola mi nombre es Javier.", textCorrected);
    }

    @Test
    public void spellCheckerWithDictionaryMethodAndCorrectTweetMethodShouldReturnTheCorrectTweetWithoutVariationAsTweetNormForm() throws IOException {
        String id = "318676223985336320";
        String username = "YoqueseYatusaeh";
        String hash = "49bc1393f28d8eb119e02d410fa9a2d8";
        String text = "Hoy llevo la camiseta de Batman jdjejdkahflwkdjwpvqh.";

        Tweet tweet = new Tweet(id, username, hash, text);
        SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());
        TweetCorrected tweetCorrected = spellChecker.correctTweet(tweet);

        assertEquals("failure - the correction is incorrect", "318676223985336320\n" +
                "\tBatman 1 -\n" +
                "\tjdjejdkahflwkdjwpvqh 2 -", tweetCorrected.toTweetNormString());
    }

    @Test
    public void spellCheckerWithDictionaryMethodAndCorrectTweetMethodShouldReturnTheCorrectTweetAsTweetNormForm() throws IOException {
        String id = "318819189580263425";
        String username = "Irene_kyoto";
        String hash = "0034c035b4328bfbdb2b90be52c2d598";
        String text = "Esperando walking dead. A ver como acaba...";

        Tweet tweet = new Tweet(id, username, hash, text);
        SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());
        TweetCorrected tweetCorrected = spellChecker.correctTweet(tweet);

        assertEquals("failure - the correction is incorrect", "318819189580263425\n" +
                "\twalking 0 Walking\n" +
                "\tdead 0 Dead", tweetCorrected.toTweetNormString());
    }

    @Test
    public void spellCheckerWithDictionaryMethodAndCorrectTweetMethodShouldReturnTheCorrectTweetAsTweetNormForm2() throws IOException {
        String id = "318596947432841216";
        String username = "MariaaNogales";
        String hash = "76529c7a59fc697d73bfdf323bf60d50";
        String text = "El siestón que me voy a echar hoy va a ser digno...!";

        Tweet tweet = new Tweet(id, username, hash, text);
        SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());
        TweetCorrected tweetCorrected = spellChecker.correctTweet(tweet);

        assertEquals("failure - the correction is incorrect", "318596947432841216",
                tweetCorrected.toTweetNormString());
    }
}