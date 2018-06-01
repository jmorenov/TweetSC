package com.jmorenov.tweetsccore.spellchecker;

import com.jmorenov.tweetsccore.postagging.POSTagging;
import com.jmorenov.tweetsccore.postagging.StanfordNLP;

import java.util.ArrayList;

public class SpellChecker {
    private Method _method;

    public SpellChecker(Method method) {
        setMethod(method);
    }

    public void setMethod(Method method) {
        this._method = method;
    }

    public String getMethodDescription() {
        return this._method.toString();
    }

    public String correctText(String text) {
        return this._method.correctText(text);
    }

    public String correctTextForTweetNorm(String text) {
        return this._method.correctTextForTweetNorm(text);
    }
}