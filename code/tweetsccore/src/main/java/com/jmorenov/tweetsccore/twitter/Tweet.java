package com.jmorenov.tweetsccore.twitter;

public class Tweet {
    private String _id;
    private String _username;
    private String _hash;
    private String _text;

    public Tweet(String text) {
        this._text = text;
    }

    public Tweet(String id, String username, String hash, String text) {
        this._id = id;
        this._username = username;
        this._hash = hash;
        this._text = text;
    }

    public String getId() {
        return _id;
    }

    public String getUsername() {
        return _username;
    }

    public String getHash() {
        return _hash;
    }

    public String getText() {
        return _text;
    }

    @Override
    public String toString() {
        return getId() + " " + getUsername() + " " + getHash() + " " + getText();
    }
}
