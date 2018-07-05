package com.jmorenov.tweetsccore.twitter;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;

import java.util.Collections;
import java.util.List;

/**
 * Tweet corrected class with the structure of a corrected tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetCorrected extends Tweet {
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
     * @param date of the tweet
     */
    public TweetCorrected(String id, String username, String hash, String text, String date) {
        super(id, username, hash, text, date);
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
        return this.computeCorrectedText();
    }

    /**
     * Method to set the Out-Of-Vocabulary words of the tweet.
     * @param OOVWords the list of OOV
     */
    public void setOOVWords(List<OOV> OOVWords) {
        this.OOVWords = OOVWords;
        Collections.sort(this.OOVWords);
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
     * Method to compute the corrected text from the OOV words.
     * @return String with the corrected text
     */
    private String computeCorrectedText() {
        String correctedText = getText();

        if (getOOVWords() != null && getOOVWords().size() > 0) {
            int diff = 0;

            for (OOV oov : getOOVWords()) {
                if (oov.getAnnotation() == Annotation.Variation) {
                    if (oov.getStartPosition() == 0) {
                        correctedText = oov.getCorrection() + correctedText.substring(oov.getEndPosition() + diff, correctedText.length());
                    }else {
                        correctedText = correctedText.substring(0, oov.getStartPosition() + diff)
                                + oov.getCorrection()
                                + correctedText.substring(oov.getEndPosition() + diff, correctedText.length());
                    }

                    diff += oov.getCorrection().length() - oov.getToken().length();
                }   
            }
        }

        return correctedText;
    }
}