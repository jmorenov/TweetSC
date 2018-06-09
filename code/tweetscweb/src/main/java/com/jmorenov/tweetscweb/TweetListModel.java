package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;

import java.util.List;

/**
 * TweetListModel class with the model of the list of tweets.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetListModel {
    private List<TweetCorrected> tweets;

    /**
     * Method to set the list of corrected tweets.
     * @param tweets List of TweetCorrected
     */
    public void setTweets(List<TweetCorrected> tweets) {
        this.tweets = tweets;
    }

    /**
     * Method to get the list of corrected tweets.
     * @return List of TweetCorrected
     */
    public List<TweetCorrected> getTweets() {
        return this.tweets;
    }
}