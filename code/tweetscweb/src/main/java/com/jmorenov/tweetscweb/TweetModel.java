package com.jmorenov.tweetscweb;

/**
 * TweetModel class with the model.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetModel {
    private long id;
    private String content;
    private String correctedContent = null;

    /**
     * Method to get the id of the tweet.
     * @return long with the id of the twee.
     */
    public long getId() {
        return id;
    }

    /**
     * Method to set the id of the tweet.
     * @param id long with the id of the tweet.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method to get the content of the tweet.
     * @return String with the content of the tweet.
     */
    public String getContent() {
        return content;
    }

    /**
     * Method to set the content of the tweet.
     * @param content String with the content of the tweet.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Method to get the corrected content of the tweet.
     * @return String with the corrected content of the tweet.
     */
    public String getCorrectedContent() {
        return correctedContent;
    }

    /**
     * Method to set the corrected content of the tweet.
     * @param correctedContent String with the corrected content of the tweet.
     */
    public void setCorrectedContent(String correctedContent) {
        this.correctedContent = correctedContent;
    }

    /**
     * Method to check if the content is corrected.
     * @return Boolean with the value if the content is corrected.
     */
    public boolean isCorrected() {
        return this.correctedContent != null;
    }
}
