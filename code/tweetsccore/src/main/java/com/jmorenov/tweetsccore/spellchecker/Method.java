package com.jmorenov.tweetsccore.spellchecker;

public abstract class Method {
    public Method() {

    }
    @Override
    public abstract String toString();
    public abstract String correctText(String text);
    public abstract String correctTextForTweetNorm(String text);
}
