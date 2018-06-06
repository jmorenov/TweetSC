package com.jmorenov.tweetsccore.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConfiguration {
    private static TwitterConfiguration _twitterConfiguration = null;
    private Twitter _twitterAccess;

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

    private synchronized static void createInstance() {
        if (_twitterConfiguration == null) {
            _twitterConfiguration = new TwitterConfiguration();
        }
    }

    public static TwitterConfiguration getInstance() {
        if (_twitterConfiguration == null) {
            createInstance();
        }

        return _twitterConfiguration;
    }

    public Twitter getTwitterAccess() {
        return this._twitterAccess;
    }
}
