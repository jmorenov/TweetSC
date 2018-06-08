package com.jmorenov.tweetsccore.twitter;

import twitter4j.Status;

/**
 * Tweet class with the structure of a tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Tweet {
    private String id;
    private String username;
    private String hash;
    private String text;

    /**
     * Default constructor of the class.
     */
    public Tweet() {}

    public Tweet(Status tweetStatus) {
        id = Long.toString(tweetStatus.getId());
        username = tweetStatus.getUser().toString();
        hash = "";
        text = tweetStatus.getText();
    }

    /**
     * Constructor of the class.
     * @param text String with the text of the tweet.
     */
    public Tweet(String text) {
        this.text = text;
    }

    /**
     * Constructor of the class.
     * @param id String with the id of the tweet.
     * @param username String with the username of the tweet.
     * @param hash String with the hash of the tweet.
     * @param text String with the text of the tweet.
     */
    public Tweet(String id, String username, String hash, String text) {
        this.id = id;
        this.username = username;
        this.hash = hash;
        this.text = text;
    }

    /**
     * Method to get the id of the tweet.
     * @return String with the id of the tweet.
     */
    public String getId() {
        return id;
    }

    /**
     * Method to get the username of the tweet.
     * @return String with the username of the tweet.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to get the hash of the tweet.
     * @return String with the hash of the tweet.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Method to get the text of the tweet.
     * @return String with the text of the tweet.
     */
    public String getText() {
        return text;
    }

    /**
     * Method to get the string of the method.
     * @return String with the String of the method.
     */
    @Override
    public String toString() {
        return getId() + " " + getUsername() + " " + getHash() + " " + getText();
    }
}
