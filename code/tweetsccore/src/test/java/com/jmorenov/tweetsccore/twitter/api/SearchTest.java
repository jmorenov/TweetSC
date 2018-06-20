package com.jmorenov.tweetsccore.twitter.api;

import com.jmorenov.tweetsccore.twitter.Tweet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchTest {
    @Test
    public void getAllTweetsOfAnUserShouldReturnAtLeastOneTweet() {
        String username = "marca";

        Search search = new Search();
        List<Tweet> tweets = search.getAllTweetsOfUser(username);

        assertTrue("Error getting all tweets from an user", tweets.size() > 0);
    }

    @Test
    public void getTweetsOfAnUserByATextShouldReturnAtLeastOneTweet() {
        String username = "marca";
        String text = "fútbol";

        Search search = new Search();
        List<Tweet> tweets = search.getTweetsByTextOfUser(username, text);

        assertTrue("Error getting tweets by a text from an user", tweets.size() > 0);
    }

    @Test
    public void getTweetsByATextShouldReturnAtLeastOneTweet() {
        String text = "real madrid";

        Search search = new Search();
        List<Tweet> tweets = search.getTweetsByText(text);

        assertTrue("Error getting tweets by a text", tweets.size() > 0);
    }

    @Test
    public void getTweetByIdShouldReturnOneTweet() {
        String id = "983806224515895297";

        Search search = new Search();
        Tweet tweet = search.getTweetById(id);

        assertTrue("Error getting a tweet by id", !tweet.getText().equals(""));
    }
}