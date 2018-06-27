package com.jmorenov.tweetsccore.tokenizer;

import java.util.List;

/**
 * Tokenizer abstract class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class Tokenizer {
    /**
     * Method to get the tokens from a text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    public abstract List<String> getTokens(String text);
}
