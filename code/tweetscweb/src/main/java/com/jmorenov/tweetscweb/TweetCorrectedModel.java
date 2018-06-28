package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.TweetCorrected;

public class TweetCorrectedModel extends TweetModel {
    public String correctedText = null;

    public TweetCorrectedModel() {}

    public TweetCorrectedModel(TweetCorrected tweetCorrected) {
        this.id = tweetCorrected.getId();
        this.username = tweetCorrected.getUsername();
        this.hash = tweetCorrected.getHash();
        this.text = tweetCorrected.getText();
        this.correctedText = tweetCorrected.getCorrectedText();
        this.date = tweetCorrected.getDate();
    }
}