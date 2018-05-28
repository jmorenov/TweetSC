package com.jmorenov.tweetsccore.spellchecker;

import com.jmorenov.tweetsccore.postagging.POSTagging;
import com.jmorenov.tweetsccore.postagging.StanfordNLP;

import java.util.ArrayList;

public class SpellChecker {
    private POSTagging _post;

    public SpellChecker(String text) {
        //_post = new StanfordNLP(text);
    }

    public ArrayList<String> words() {
        return _post.getTokens();
    }

    public String correct() {
        return "corregido...";
    }
}