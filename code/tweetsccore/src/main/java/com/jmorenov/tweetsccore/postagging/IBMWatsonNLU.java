package com.jmorenov.tweetsccore.postagging;

import java.util.ArrayList;

/**
 * IBMWatsonNLU class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class IBMWatsonNLU extends POSTagging {
    /**
     * Default constructor.
     * @param text String with the text.
     */
    public IBMWatsonNLU(String text) {}

    /**
     * Method to get the tokens from a text.
     * @return ArrayList with the tokens.
     */
    public ArrayList<String> getTokens() {
        ArrayList<String> tokens = new ArrayList<String>();

        tokens.add("TEST");

        return tokens;
    }
}
