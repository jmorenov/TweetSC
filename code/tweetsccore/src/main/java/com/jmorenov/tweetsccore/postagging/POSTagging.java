package com.jmorenov.tweetsccore.postagging;

import java.util.ArrayList;

/**
 * POSTagging abstract class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class POSTagging {
    /**
     * Method to get the tokens from a text.
     * @return ArrayList with the tokens.
     */
    public abstract ArrayList<String> getTokens();
}
