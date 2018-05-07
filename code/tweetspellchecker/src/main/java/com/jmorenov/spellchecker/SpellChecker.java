package com.jmorenov.spellchecker;

import com.jmorenov.postagging.POSTagging;
import com.jmorenov.postagging.StanfordNLP;

import java.util.ArrayList;

class SpellChecker {
    private POSTagging _post;

    SpellChecker(String text) {
        _post = new StanfordNLP(text);
    }

    ArrayList<String> words() {
        return _post.getTokens();
    }
}