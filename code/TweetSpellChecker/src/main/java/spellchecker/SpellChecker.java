package spellchecker;

import postagging.POSTagging;
import postagging.StanfordNLP;

import java.util.ArrayList;

class SpellChecker {
    private POSTagging post;

    SpellChecker(String text) {
        post = new StanfordNLP(text);
    }

    ArrayList<String> words() {
        return post.getTokens();
    }
}