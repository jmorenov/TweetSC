package com.jmorenov.tweetsccore.twitter;

/**
 * Tweet corrected class with the structure of a corrected tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetCorrected extends Tweet {
    private String correctedContent = null;

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
     * Method to get the corrected tweet.
     * @return
     */
    public String getCorrectedContent() {
        return this.correctedContent;
    }

    /**
     * Method to set the corrected tweet.
     * @param correctedContent the corrected text
     */
    public void setCorrectedContent(String correctedContent) {
        this.correctedContent = correctedContent;
    }
}
