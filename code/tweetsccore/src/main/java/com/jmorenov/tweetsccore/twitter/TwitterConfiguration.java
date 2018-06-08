package com.jmorenov.tweetsccore.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * TwitterConfiguration class with the configuration of the connection with the API of twitter.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TwitterConfiguration {
    private static TwitterConfiguration _twitterConfiguration = null;
    private Twitter _twitterAccess;

    /**
     * Private constructor of the class.
     */
    private TwitterConfiguration() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true);
        configurationBuilder.setOAuthConsumerKey("GpBTrgjEZc8dMNhH74N1RDwe4");
        configurationBuilder.setOAuthConsumerSecret("hNahic5RSY9P6LdB8hkbEyNDDSm2Cm3kgYt6tIacesgLfkoQx4");
        configurationBuilder.setOAuthAccessToken("300124028-SYZzjOSEV73RzX80pyXl9ivZFR7AlVh9Nz4okHCO");
        configurationBuilder.setOAuthAccessTokenSecret("4xyeRFCCcMDlkjzZ7PVc3OI7RBRy5fPrETK3ynXVFkoVd");

        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        this._twitterAccess = tf.getInstance();
    }

    /**
     * Method to create the instance of the class.
     */
    private synchronized static void createInstance() {
        if (_twitterConfiguration == null) {
            _twitterConfiguration = new TwitterConfiguration();
        }
    }

    /**
     * Method to get the instance of the class.
     * @return TwitterConfiguration the instance of the class
     */
    public static TwitterConfiguration getInstance() {
        if (_twitterConfiguration == null) {
            createInstance();
        }

        return _twitterConfiguration;
    }

    /**
     * Method to get the access to the API.
     * @return Twitter
     */
    public Twitter getTwitterAccess() {
        return this._twitterAccess;
    }
}
