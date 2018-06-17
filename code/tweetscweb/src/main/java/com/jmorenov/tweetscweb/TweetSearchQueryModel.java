package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;
import java.util.List;

/**
 * TweetSearchQueryModel class with the model of the queries.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetSearchQueryModel {
    private String query;
    private List<TweetModel> tweets;

    /**
     * Method to get the query.
     * @return String
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * Method to set the query.
     * @param query String
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Method to get the tweets.
     * @return List of tweet
     */
    public List<TweetModel> getTweets() {
        return this.tweets;
    }

    /**
     * Method to set the tweets.
     * @param tweets List of tweet
     */
    public void setTweets(List<TweetModel> tweets) {
        this.tweets = tweets;
    }
}