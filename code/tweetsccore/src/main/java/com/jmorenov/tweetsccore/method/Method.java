package com.jmorenov.tweetsccore.method;

import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;

/**
 * Method abstract class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class Method {
    /**
     * Default constructor of the class.
     */
    public Method() {}

    /**
     * Abstract method to get the String of the method.
     * @return String with the String of the method.
     */
    @Override
    public abstract String toString();

    /**
     * Abstract method to get the corrected tweet.
     * @param tweet Tweet with the tweet to correct.
     * @return TweetCorrected with the corrected tweet.
     */
    public abstract TweetCorrected correctTweet(Tweet tweet);
}