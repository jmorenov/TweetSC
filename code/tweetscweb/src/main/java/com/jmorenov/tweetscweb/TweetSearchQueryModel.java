package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;
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

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}