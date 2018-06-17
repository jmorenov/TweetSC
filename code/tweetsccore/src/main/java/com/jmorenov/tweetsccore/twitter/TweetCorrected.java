package com.jmorenov.tweetsccore.twitter;

/**
 * Tweet corrected class with the structure of a corrected tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetCorrected extends Tweet {
    private String correctedText = null;

    /**
     * Default constuctor of the class.
     */
    public TweetCorrected() {}

    /**
     * Constructor of the class.
     * @param text of the tweet
     */
    public TweetCorrected(String text) {
        super(text);
    }

    /**
     * Constructor of the class.
     * @param id of the tweet
     * @param username
     * @param hash of the tweet
     * @param text of the tweet
     */
    public TweetCorrected(String id, String username, String hash, String text) {
        super(id, username, hash, text);
    }

    /**
     * Constructor from tweet.
     * @param tweet Tweet
     */
    public TweetCorrected(Tweet tweet) {
        super(tweet);
    }

    /**
     * Method to get the corrected tweet.
     * @return
     */
    public String getCorrectedText() {
        return this.correctedText;
    }

    /**
     * Method to set the corrected tweet.
     * @param correctedText the corrected text
     */
    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }

    /**
     * Method to get the string of the Tweet Corrected.
     * @return String with the String of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " --> " +  getCorrectedText();
    }
}
