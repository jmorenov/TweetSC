package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;

public class TweetModel {
    public String id;
    public String username;
    public String hash;
    public String text;

    public TweetModel() {}

    public TweetModel(Tweet tweet) {
        this.id = tweet.getId();
        this.username = tweet.getUsername();
        this.hash = tweet.getHash();
        this.text = tweet.getText();
    }

    public Tweet toTweet() {
        return new Tweet(this.id, this.username, this.hash, this.text);
    }
}
