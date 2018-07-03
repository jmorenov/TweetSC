package com.jmorenov.tweetsccore.extra;

/**
 * Token class with the structure of a token from a text.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Token {
    private String text;
    private int startPosition;
    private int endPosition;

    /**
     * Constructor of the class.
     * @param text String the text of the token
     * @param startPosition int the start position of the token in the text
     * @param endPosition int the end position of the token in the text
     */
    public Token(String text, int startPosition, int endPosition) {
        this.text = text;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    /**
     * Method to get the text of the token.
     * @return String
     */
    public String getText() {
        return text;
    }

    /**
     * Method to get the start position.
     * @return int
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Method to get the end position
     * @return int
     */
    public int getEndPosition() {
        return endPosition;
    }
}
