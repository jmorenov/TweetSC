package com.jmorenov.spellchecker;

import com.jmorenov.postagging.POSTagging;
import com.jmorenov.postagging.StanfordNLP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;

class SpellChecker {
    private final static Logger logger = LoggerFactory.getLogger(SpellChecker.class);
    private POSTagging post;

    SpellChecker(String text) {
        post = new StanfordNLP(text);
    }

    ArrayList<String> words() {
        return post.getTokens();
    }

    public static void main(String[] args) {
        logger.debug("[MAIN] Current Date : {}", new Date());
    }
}