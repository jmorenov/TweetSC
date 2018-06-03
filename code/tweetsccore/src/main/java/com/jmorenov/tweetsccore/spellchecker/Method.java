package com.jmorenov.tweetsccore.spellchecker;

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
     * Abstract method to get the corrected text.
     * @param text String with the text to correct.
     * @return String with the correctec text.
     */
    public abstract String correctText(String text);

    /**
     * Abstract method to get the corrected text for Tweet Norm 2013.
     * @param text String with the text to correct.
     * @return String with the correctec text.
     */
    public abstract String correctTextForTweetNorm(String text);
}
