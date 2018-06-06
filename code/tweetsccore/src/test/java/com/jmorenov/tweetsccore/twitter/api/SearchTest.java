package com.jmorenov.tweetsccore.twitter.api;

import org.junit.Test;
import twitter4j.Status;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    @Test
    public void getAllTweetsOfAnUserShouldReturnAtLeastOneTweet() {
        String username = "marca";

        Search search = new Search();
        List<Status> tweets = search.getAllTweetsOfUser(username);

        assertTrue("Error getting all tweets from an user", tweets.size() > 0);
    }

    @Test
    public void getTweetsOfAnUserByATextShouldReturnAtLeastOneTweet() {
        String username = "marca";

        Search search = new Search();
        List<Status> tweets = search.getTweetsByTextOfUser(username, "Pochettino");

        assertTrue("Error getting tweets by a text from an user", tweets.size() > 0);
    }
}