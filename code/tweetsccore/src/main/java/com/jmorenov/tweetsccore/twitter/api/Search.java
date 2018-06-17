package com.jmorenov.tweetsccore.twitter.api;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TwitterConfiguration;
import twitter4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Search class to do call on the Twitter API about tweets.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Search {
    private Twitter _twitterAccess;

    private List<Tweet> listOfStatusToListOfTweet (List<Status> statuses) {
        List<Tweet> tweets = new ArrayList<>();

        for (Status status : statuses) {
            tweets.add(new Tweet(status));
        }

        return tweets;
    }

    /**
     * Method to search tweets of an user.
     * @param username the user
     * @param text the text to search
     * @return List of Status with the tweets.
     */
    private List<Tweet> searchTweetsOfUsers(String username, String text) {
        List<Tweet> tweets = new ArrayList<>();
        try {
            Query query = new Query(text + " lang:es AND from:" + username + " AND -filter:retweets");
            query.setCount(100);
            QueryResult result;
            result = this._twitterAccess.search(query);
            tweets = listOfStatusToListOfTweet(result.getTweets());
        } catch (TwitterException twitterException) {
            twitterException.printStackTrace();
        }
        return tweets;
    }

    /**
     * Method to search tweets.
     * @param text the text to search
     * @return List of Status with the tweets.
     */
    private List<Tweet> searchTweets(String text) {
        List<Tweet> tweets = new ArrayList<>();

        try {
            Query query = new Query(text + " lang:es AND -filter:retweets");
            query.setCount(100);
            QueryResult result;
            result = this._twitterAccess.search(query);
            tweets = listOfStatusToListOfTweet(result.getTweets());
        } catch (TwitterException twitterException) {
            twitterException.printStackTrace();
        }

        return tweets;
    }

    /**
     * Method to search a tweet by id.
     * @param id the id of the tweet
     * @return Status with the tweet.
     */
    private Tweet searchTweetById(String id) {
        Tweet tweet = null;

        try {
            long longId = Long.parseLong(id);
            tweet = new Tweet(this._twitterAccess.showStatus(longId));
        } catch (TwitterException twitterException) {
            twitterException.printStackTrace();
        }

        return tweet;
    }

    /*
    private String downloadFullTextOfTweet(long id) {
        String text;
        String link;

        try {
            Document doc = Jsoup.connect("https://twitter.com/statuses/" + id).get();
            Element tweetContainer = doc.getElementsByAttributeValue("data-tweet-id", "" + id).first();
            Element tweet = tweetContainer.selectFirst("p.tweet-text");

            try {
                Element linkElement = tweet.selectFirst("a");
                link = linkElement.text();
            } catch (NullPointerException linkException) {
                link = "";
            }
            text = tweet.text();

            if (!link.equals("")) {
                text = text.replace(link, " " + link);
                text = text.substring(0, text.length()-2);
            }

        } catch (IOException urlException) {
            text = "";
        }

        return text;
    }*/

    /**
     * Default constructor of the class.
     */
    public Search() {
        this._twitterAccess = TwitterConfiguration.getInstance().getTwitterAccess();
    }

    /**
     * Method to get all the tweets of an user.
     * @param username the user
     * @return List of Status with the tweets
     */
    public List<Tweet> getAllTweetsOfUser(String username) {
        return this.searchTweetsOfUsers(username, "");
    }

    /**
     * Method to search tweets of an user.
     * @param username the user
     * @param text the text to search
     * @return List of Status with the tweets
     */
    public List<Tweet> getTweetsByTextOfUser(String username, String text) {
        return this.searchTweetsOfUsers(username, text);
    }

    /**
     * Method to search tweets.
     * @param text the text to search
     * @return List of Status with the tweets
     */
    public List<Tweet> getTweetsByText(String text) {
        return this.searchTweets(text);
    }

    /**
     * Method to search tweets by it id.
     * @param id the id of the tweet
     * @return Status The tweet
     */
    public Tweet getTweetById(String id) {
        return this.searchTweetById(id);
    }
}