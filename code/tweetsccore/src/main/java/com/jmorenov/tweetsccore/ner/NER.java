package com.jmorenov.tweetsccore.ner;

import java.util.ArrayList;

/**
 * POSTagging abstract class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class NER {
    /**
     * Method to get the tokens from a text.
     * @return ArrayList with the tokens.
     */
    public abstract ArrayList<String> getTokens();

    /**
     * Method to get the tokens from a text.
     * @return ArrayList with the tokens and the class.
     */
    public abstract ArrayList<String> getTokensRegex();
}