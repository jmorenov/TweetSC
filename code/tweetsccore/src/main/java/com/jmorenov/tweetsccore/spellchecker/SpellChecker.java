package com.jmorenov.tweetsccore.spellchecker;

import com.jmorenov.tweetsccore.method.Method;
import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;

/**
 * SpellChecker class to correct a text.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class SpellChecker {
    private Method _method;

    /**
     * Constructor of the class.
     * @param method {@link Method} parameter with the method to use.
     * @see Method
     */
    public SpellChecker(Method method) {
        setMethod(method);
    }

    /**
     * Method to define the spell checker method.
     * @param method {@link Method} parameter with the method to use.
     */
    public void setMethod(Method method) {
        this._method = method;
    }

    /**
     * Method to get spell checker method description.
     * @return String with the description of the method.
     */
    public String getMethodDescription() {
        return this._method.toString();
    }

    /**
     * Method to correct the text.
     * @param text String with the text to correct.
     * @return String with the corrected text.
     */
    public String correctText(String text) {
        return this._method.correctText(text);
    }

    /**
     * Method to correct the text to Tweet Norm 2013.
     * @param text String with the text to correct.
     * @return String with the corrected Text.
     */
    public String correctTextForTweetNorm(String text) {
        return this._method.correctTextForTweetNorm(text);
    }

    /**
     * Method to correct a tweet.
     * @param tweet Tweet with the tweet.
     * @return TweetCorrected with the corrected tweet.
     */
    public TweetCorrected correctTweet(Tweet tweet) {
        TweetCorrected tweetCorrected = new TweetCorrected(tweet);

        tweetCorrected.setCorrectedText(this._method.correctText(tweet.getText()));

        return tweetCorrected;
    }
}