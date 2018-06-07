package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetSearchQueryModel {
    private String query;
    private List<Tweet> tweets;

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Tweet> getTweets() {
        return this.tweets;
    }

    public boolean isValidQuery() {
        return true;
    }

    public void loadTweets() {
        this.tweets = new ArrayList<>();

        Tweet tweet1 = new Tweet("id12", "jmorenov", "1234hash", "prueba de texto");
        Tweet tweet2 = new Tweet("id123", "angy", "1234hash", "prueba de texto 23");

        this.tweets.add(tweet1);
        this.tweets.add(tweet2);
    }
}