package com.jmorenov.tweetsccore.extra;

/**
 * Anotation enum with the different anotations posibilities of a tweet.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public enum Annotation {
    Variation(0), Correct(1), NoEs(2);

    public int value;
    private Annotation(int value) {
        this.value = value;
    }
}