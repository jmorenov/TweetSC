package com.jmorenov.tweetscweb;

import java.util.ArrayList;
import java.util.List;

/**
 * TweetListModel class with the model of the list of tweets.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetListModel {
    public List<TweetModel> tweets;

    public TweetListModel() {
        tweets = new ArrayList<TweetModel>();
    }
}