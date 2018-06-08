package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;

import java.util.List;

public class TweetListModel {
    private List<TweetCorrected> tweets;

    public void setTweets(List<TweetCorrected> tweets) {
        this.tweets = tweets;
    }

    public List<TweetCorrected> getTweets() {
        return this.tweets;
    }
}