package com.jmorenov.tweetsccore.twitter;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;

import java.util.List;

/**
 * Tweet corrected class with the structure of a corrected tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetCorrected extends Tweet {
    private String correctedText = null;
    private List<OOV> OOVWords;

    /**
     * Default constuctor of the class.
     */
    public TweetCorrected() {}

    /**
     * Constructor of the class.
     * @param text of the tweet
     */
    public TweetCorrected(String text) {
        super(text);
    }

    /**
     * Constructor of the class.
     * @param id of the tweet
     * @param username
     * @param hash of the tweet
     * @param text of the tweet
     */
    public TweetCorrected(String id, String username, String hash, String text) {
        super(id, username, hash, text);
    }

    /**
     * Constructor from tweet.
     * @param tweet Tweet
     */
    public TweetCorrected(Tweet tweet) {
        super(tweet);
    }

    /**
     * Method to get the corrected tweet.
     * @return String the corrected text
     */
    public String getCorrectedText() {
        return this.correctedText;
    }

    /**
     * Method to set the corrected tweet.
     * @param correctedText the corrected text
     */
    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }

    /**
     * Method to set the Out-Of-Vocabulary words of the tweet.
     * @param OOVWords the list of OOV
     */
    public void setOOVWords(List<OOV> OOVWords) {
        this.OOVWords = OOVWords;
    }

    /**
     * Method to get the Out-Of-Vocabulary words of the tweet.
     * @return List of OOV
     */
    public List<OOV> getOOVWords() {
        return this.OOVWords;
    }

    /**
     * Method to get the string of the Tweet Corrected.
     * @return String with the String of the class
     */
    @Override
    public String toString() {
        return super.toString() + " --> " +  getCorrectedText();
    }

    /**
     * Method to get the corrected text for Tweet Norm 2013.
     * @return String with the correctec text.
     */
    public String toTweetNormString() {
        String tweetNormString = getId();

        if (getOOVWords() != null && getOOVWords().size() > 0) {
            for (OOV oov : getOOVWords()) {
                tweetNormString += "\n\t" + oov.getToken();

                if (oov.getAnnotation() == Annotation.Variation) {
                    tweetNormString += " 0 " + oov.getCorrection();
                } else {
                    tweetNormString += " " + oov.getAnnotation().value + " -";
                }
            }
        }

        return tweetNormString;
    }

    /**
     * Method to set the corrected text from the OOV words.
     */
    public void computeCorrectedText() {
        if (getOOVWords() != null && getOOVWords().size() > 0) {
            correctedText = getText();
            StringBuilder correctedTextBuffer = new StringBuilder(correctedText);

            for (OOV oov : getOOVWords()) {
                if (oov.getAnnotation() == Annotation.Variation) {
                    correctedTextBuffer.replace(oov.getStartPosition(), oov.getEndPosition(), oov.getCorrection());

                    correctedText = correctedTextBuffer.toString();
                }   
            }
        } else {
            correctedText = getText();
        }
    }
}