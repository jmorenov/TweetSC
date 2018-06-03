package com.jmorenov.tweetsccore.twitter;

/**
 * Tweet class with the structure of a tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Tweet {
    private String _id;
    private String _username;
    private String _hash;
    private String _text;

    /**
     * Constructor of the class.
     * @param text String with the text of the tweet.
     */
    public Tweet(String text) {
        this._text = text;
    }

    /**
     * Constructor of the class.
     * @param id String with the id of the tweet.
     * @param username String with the username of the tweet.
     * @param hash String with the hash of the tweet.
     * @param text String with the text of the tweet.
     */
    public Tweet(String id, String username, String hash, String text) {
        this._id = id;
        this._username = username;
        this._hash = hash;
        this._text = text;
    }

    /**
     * Method to get the id of the tweet.
     * @return String with the id of the tweet.
     */
    public String getId() {
        return _id;
    }

    /**
     * Method to get the username of the tweet.
     * @return String with the username of the tweet.
     */
    public String getUsername() {
        return _username;
    }

    /**
     * Method to get the hash of the tweet.
     * @return String with the hash of the tweet.
     */
    public String getHash() {
        return _hash;
    }

    /**
     * Method to get the text of the tweet.
     * @return String with the text of the tweet.
     */
    public String getText() {
        return _text;
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
