package com.jmorenov.tweetscweb;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.api.Search;
import org.apache.xerces.impl.xpath.regex.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum TypeOfQuery {
    ID, USER, TEXT, USERTEXT, UNKNOWN
}

public class TweetSearchQueryModel {
    private String query;
    private List<Tweet> tweets;

    private Matcher getElementsOfQuery() {
        //String pattern = "^id=(\\w+)$|^user=(\\w+)$|^user=(\\w+)&text=([\\w\\W]+)$|^text=([\\w\\W]+)&user=(\\w+)$|^text=([\\w\\W]+)$";
        String idPattern = "^id=(\\w+)$";
        String userPattern = "^user=(\\w+)$";
        String userTextPattern = "^user=(\\w+)&text=([\\w\\W]+)$";
        String textUserPattern = "^text=([\\w\\W]+)&user=(\\w+)$";
        String textPattern = "^text=([\\w\\W]+)$";
        //Pattern r = Pattern.compile(pattern);



        return r.matcher(query);
    }

    private TypeOfQuery getTypeOfQuery() {
        TypeOfQuery typeOfQuery;

        switch (query) {
            case query.matches(idPattern)
        }
    }

    private String getId(Matcher matcher) {
        String id = null;

        try {
            if (matcher.find()) {
                id = matcher.group(4);
            }
        } catch(IllegalStateException ex) {}

        return id;
    }

    private String getUser(Matcher matcher) {
        String user;

        try {
            user = matcher.group(1);
        } catch(IllegalStateException ex) {
            try {
                user = matcher.group(2);
            } catch(IllegalStateException ex2) {
                try {
                    user = matcher.group(5);
                } catch(IllegalStateException ex3) {
                    user = null;
                }
            }
        }

        return user;
    }

    private String getText(Matcher matcher) {
        String text;

        try {
            text = matcher.group(3);
        } catch(IllegalStateException ex) {
            try {
                text = matcher.group(4);
            } catch(IllegalStateException ex2) {
                try {
                    text = matcher.group(6);
                } catch(IllegalStateException ex3) {
                    text = null;
                }
            }
        }

        return text;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Tweet> getTweets() {
        return this.tweets;
    }

    public boolean isValidQuery() {
        return getTypeOfQuery() != TypeOfQuery.UNKNOWN;
    }

    public void loadTweets() {
        this.tweets = new ArrayList<>();

        Search twitterSearch = new Search();
        TypeOfQuery typeOfQuery = getTypeOfQuery();

        switch (typeOfQuery) {
            case ID:
                String id = getId();
                this.tweets.add(twitterSearch.getTweetById(id));
                break;
            case USER:
                break;
            case TEXT:
                break;
            case USERTEXT:
                break;
            case UNKNOWN:
            default:
                break;
        }
    }
}