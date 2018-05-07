package com.jmorenov.spellchecker;

import com.jmorenov.postagging.StanfordNLP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellCheckerByDictionary {
    private Map<String, Integer> _dictionaryWords;
    private Map<String, Integer> _ownNames;
    private String alphabet;

    SpellCheckerByDictionary(String text) {
        //_dictionaryWords = train(getWords())
    }

    private ArrayList<String> getWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(text);

        while (m.find()) {
            words.add(text.substring(m.start(), m.end()));
        }

        return words;
    }

    private Map<String, Integer> train(ArrayList<String> words) {
        Map<String, Integer> dictionary = new HashMap<String, Integer>();

        for (String word : words) {
            int wordPcValue = dictionary.get(word) + 1;

            dictionary.put(word, wordPcValue);
        }

        return dictionary;
    }

    private void edits1(String word) {

    }

    private void known_edits2(String word) {

    }

    private void known(String word) {

    }

    private void removeEmojiFromText(String text) {

    }

    private void correctWord(String word) {

    }

    private Boolean isUrl(String word) {
        return true;
    }

    private Boolean isUsername(String word) {
        return true;
    }

    private Boolean isisHashtag(String word) {
        return true;
    }

    private Boolean validWord(String word) {
        return true;
    }

    private Boolean isPunctuationSign(String word) {
        return true;
    }

    private String getValidText(String text) {
        return "";
    }
}
