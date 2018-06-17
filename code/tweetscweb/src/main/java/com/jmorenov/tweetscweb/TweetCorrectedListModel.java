package com.jmorenov.tweetscweb;

import java.util.ArrayList;
import java.util.List;

/**
 * TweetCorrectedListModel class with the model of the list of tweets corrected.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetCorrectedListModel {
    public List<TweetCorrectedModel> tweets;

    public TweetCorrectedListModel() {
        tweets = new ArrayList<TweetCorrectedModel>();
    }
}

