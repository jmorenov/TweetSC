package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.api.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TypeOfQuery class enum with the types of query.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
enum TypeOfQuery {
    ID, USER, TEXT, USERTEXT, TEXTUSER, UNKNOWN
}

/**
 * TweetSearchQuery class with the functionality to work over the queries.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetSearchQuery {
    private static String idPattern = "^id=(\\d+)$";
    private static String userPattern = "^user=(\\w+)$";
    private static String userTextPattern = "^user=(\\w+)&text=([\\w\\W]+)$";
    private static String textUserPattern = "^text=([\\w\\W]+)&user=(\\w+)$";
    private static String textPattern = "^text=([\\w\\W]+)$";
    private TweetSearchQueryModel tweetSearchQueryModel;

    /**
     * Constructor of the class.
     * @param tweetSearchQueryModel TweetSearchQueryModel with the data.
     */
    public TweetSearchQuery(TweetSearchQueryModel tweetSearchQueryModel) {
        this.tweetSearchQueryModel = tweetSearchQueryModel;
    }

    /**
     * Method to get the type of query.
     * @return TypeOfQuery
     */
    private TypeOfQuery getTypeOfQuery() {
        TypeOfQuery typeOfQuery;
        String query = this.tweetSearchQueryModel.getQuery();

        if (query.matches(idPattern)) {
            typeOfQuery = TypeOfQuery.ID;
        } else if (query.matches(userPattern)) {
            typeOfQuery = TypeOfQuery.USER;
        } else if (query.matches(userTextPattern)) {
            typeOfQuery = TypeOfQuery.USERTEXT;
        } else if (query.matches(textUserPattern)) {
            typeOfQuery = TypeOfQuery.TEXTUSER;
        } else if (query.matches(textPattern)) {
            typeOfQuery = TypeOfQuery.TEXT;
        } else {
            typeOfQuery = TypeOfQuery.UNKNOWN;
        }

        return typeOfQuery;
    }

    /**
     * Method to get the id from the query.
     * @return String
     */
    private String getId() {
        String id = null;

        if (getTypeOfQuery() == TypeOfQuery.ID) {
            Pattern r = Pattern.compile(idPattern);
            Matcher matcher = r.matcher(this.tweetSearchQueryModel.getQuery());

            if (matcher.find()) {
                id = matcher.group(1);
            }
        }

        return id;
    }

    /**
     * Method to get the user from the query.
     * @return String
     */
    private String getUser() {
        String user = null;
        String pattern;
        int group;
        TypeOfQuery typeOfQuery = getTypeOfQuery();

        if (getTypeOfQuery() == TypeOfQuery.USER) {
            pattern = userPattern;
            group = 1;
        } else if (typeOfQuery == TypeOfQuery.USERTEXT) {
            pattern = userTextPattern;
            group = 1;
        } else {
            pattern = textUserPattern;
            group = 2;
        }

        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(this.tweetSearchQueryModel.getQuery());

        if (matcher.find()) {
            user = matcher.group(group);
        }

        return user;
    }

    /**
     * Method to get the text from the query.
     * @return String
     */
    private String getText() {
        String text = null;
        String pattern;
        int group;
        TypeOfQuery typeOfQuery = getTypeOfQuery();

        if (getTypeOfQuery() == TypeOfQuery.TEXT) {
            pattern = textPattern;
            group = 1;
        } else if (typeOfQuery == TypeOfQuery.USERTEXT) {
            pattern = userTextPattern;
            group = 2;
        } else {
            pattern = textUserPattern;
            group = 1;
        }

        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(this.tweetSearchQueryModel.getQuery());

        if (matcher.find()) {
            text = matcher.group(group);
        }

        return text;
    }

    /**
     * Method to add a tweet to the list if it tweet is not null.
     * @return List of Tweet
     */
    private List<Tweet> add(List<Tweet> tweets, Tweet tweet) {
        if (tweet != null) {
            tweets.add(tweet);
        }

        return tweets;
    }

    /**
     * Method to add a list of tweets to the list if it
     * list of tweets have at least one.
     * @return List of Tweet
     */
    private List<Tweet> addAll(List<Tweet> tweets, List<Tweet> tweetsToInsert) {
        if (tweetsToInsert.size() > 0) {
            tweets.addAll(tweetsToInsert);
        }

        return tweets;
    }

    /**
     * Method to get if the query is valid or not.
     * @return Boolean
     */
    public boolean isValidQuery() {
        return getTypeOfQuery() != TypeOfQuery.UNKNOWN;
    }

    /**
     * Method to load the tweets from the query.
     * @return List of tweet
     */
    public List<Tweet> loadTweets() {
        List<Tweet> tweets = new ArrayList<>();
        String user, id, text;
        Search twitterSearch = new Search();
        TypeOfQuery typeOfQuery = getTypeOfQuery();

        switch (typeOfQuery) {
            case ID:
                id = getId();
                tweets = add(tweets, twitterSearch.getTweetById(id));
                break;
            case USER:
                user = getUser();
                tweets = addAll(tweets, twitterSearch.getAllTweetsOfUser(user));
                break;
            case TEXT:
                text = getText();
                tweets = addAll(tweets, twitterSearch.getTweetsByText(text));
                break;
            case USERTEXT:
            case TEXTUSER:
                user = getUser();
                text = getText();
                tweets = addAll(tweets, twitterSearch.getTweetsByTextOfUser(user, text));
                break;
        }

        return tweets;
    }
}