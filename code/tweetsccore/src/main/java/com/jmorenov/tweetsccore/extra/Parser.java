package com.jmorenov.tweetsccore.extra;

import com.vdurmont.emoji.EmojiParser;

public class Parser {
    /**
     * Method to remove the emojis from a text.
     * @param text String with the text to remove the emojis.
     * @return String with the text without the emojis.
     */
    public static String removeEmojiFromText(String text) {
        return EmojiParser.removeAllEmojis(text);
    }

    public static String getURLRegex() {
        return "([a-zA-Z0-9]+:\\/\\/)*[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&=]*)(\\/[^\\s]+)*";
    }

    public static String getUsernameRegex() {
        return "@[a-zA-Z0-9_]*";
    }

    public static String getHashtagRegex() {
        return "#[a-zA-Z0-9_]*";
    }

    /**
     * Method to check if a word is a Url.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    public static Boolean isUrl(String word) {
        //return word.matches("http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\(\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+");
        return word.matches(getURLRegex());
    }

    /**
     * Method to check if a word is a username of Twitter.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    public static Boolean isUsername(String word) {
        return word.matches(getUsernameRegex());
    }

    /**
     * Method to check if a word is a hashtag of Twitter.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    public static Boolean isHashtag(String word) {
        return word.matches(getHashtagRegex());
    }

    /**
     * Method to check if a word is a valid word.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    public static Boolean isValidWord(String word) {
        return !isUsername(word) && !isHashtag(word) && !isUrl(word) && !isPunctuationSign(word);
    }

    /**
     * Method to check if a word is a punctuation sign.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    public static Boolean isPunctuationSign(String word) {
        return !word.matches("^[a-zA-Z0-9_]*");
    }
}
