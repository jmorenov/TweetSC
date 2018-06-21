package com.jmorenov.tweetsccore.method;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;
import com.jmorenov.tweetsccore.extra.Parser;
import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * DictionaryMethod class with the method of spell checker with dictionaries.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class DictionaryMethod extends Method {
    private Map<String, Integer> _dictionaryWords;
    private Map<String, Integer> _nombresPropiosWords;
    private Map<String, Integer> _entitiesWords;
    private Map<String, Integer> _englishWords;
    private static String _alphabet = "aábcdeéfghiíjklmnñoópqrstuúvwxyz";
    private static final String _dictionaryFileName = "dic.txt";
    private static final String _nombresPropiosFileName = "nombres_propios.txt";
    private static final String _entitiesFileName = "entities.txt";
    private static final String _englishFileName = "english.txt";

    /**
     * Default constructor of the class.
     * @throws IOException when the file not found.
     */
    public DictionaryMethod() throws IOException {
        readDictionaries();
    }

    /**
     * Method to get the String of the method.
     * @return String with the String of the method.
     */
    @Override
    public String toString() {
        return "DictionaryMethod";
    }

    /**
     * Method to get the corrected tweet.
     * @param tweet Tweet with the tweet to correct.
     * @return TweetCorrected with the corrected tweet.
     */
    public TweetCorrected correctTweet(Tweet tweet) {
        TweetCorrected tweetCorrected = new TweetCorrected(tweet);

        tweetCorrected.setOOVWords(getOOVs(tweet.getText()));

        for (OOV oov : tweetCorrected.getOOVWords()) {
            OOV correctedOOV = correctOOV(oov);

            oov.setAnnotation(correctedOOV.getAnnotation());
            oov.setCorrection(correctedOOV.getCorrection());
        }

        tweetCorrected.computeCorrectedText();

        return tweetCorrected;
    }

    /**
     * Method to correct an Out-Of-Vocabulary word.
     * @param oov OOV
     * @return OOV the corrected OOV.
     */
    private OOV correctOOV(OOV oov) {
        if (isACorrectOOV(oov.getToken())) {
            oov.setAnnotation(Annotation.Correct);
        } else
        {
            String correctedWord = correctWord(oov.getToken());
            String correctedEntity = correctEntity(oov.getToken());

            if (!correctedEntity.equals(oov.getToken())) {
                oov.setAnnotation(Annotation.Variation);
                oov.setCorrection(correctedEntity);
            } else if (isAEnglishOOV(oov.getToken())) {
                oov.setAnnotation(Annotation.NoEs);
            } else if (!correctedWord.equals(oov.getToken())) {
                oov.setAnnotation(Annotation.Variation);
                oov.setCorrection(correctedWord);
            } else {
                oov.setAnnotation(Annotation.NoEs);
            }
        }

        return oov;
    }

    private boolean isACorrectOOV(String oov) {
        return _entitiesWords.containsKey(oov) || _nombresPropiosWords.containsKey(oov);
    }

    private boolean isAEnglishOOV(String oov) {
        return _englishWords.containsKey(oov);
    }

    private String correctEntity(String oov) {
        String capitalizedWord = StringUtils.capitalize(oov);

        if (_entitiesWords.containsKey(capitalizedWord) || _nombresPropiosWords.containsKey(capitalizedWord)) {
            return capitalizedWord;
        }
        return oov;
    }

    /**
     * Method to read the dictionaries from the files.
     * @throws IOException when the file not found.
     */
    private void readDictionaries() throws IOException {
        _dictionaryWords = readDictionary(_dictionaryFileName);
        _nombresPropiosWords = readDictionary(_nombresPropiosFileName);
        _entitiesWords = readDictionary(_entitiesFileName);
        _englishWords = readDictionary(_englishFileName);
    }

    /**
     * Method to read a dictionary.
     * @param fileName String with the name of the file of the dictionary.
     * @return Map with the word and probability from the dictionary.
     * @throws IOException when the file not found.
     */
    private Map<String, Integer> readDictionary(String fileName) throws IOException {
        byte[] bytesOfDictionaryFile = File.readToByte(fileName);
        Map<String, Integer> dictionaryMap = new HashMap<String, Integer>();

        Stream.of(new String(bytesOfDictionaryFile).split("\n")).forEach( (word) ->{
            dictionaryMap.compute( word, (k,v) -> v == null ? 1 : v + 1  );
        });

        return dictionaryMap;
    }

    /**
     * Method to generate all edits that are one edit away from word.
     * @param word String with the word.
     * @return Stream with all the edits that are one edit away from word.
     */
    private Stream<String> edits1(final String word) {
        Stream<String> deletes    = IntStream.range(0, word.length())  .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));
        Stream<String> replaces   = IntStream.range(0, word.length())  .mapToObj((i)->i).flatMap( (i) -> _alphabet.chars().mapToObj( (c) ->  word.substring(0,i) + (char)c + word.substring(i+1) )  );
        Stream<String> inserts    = IntStream.range(0, word.length()+1).mapToObj((i)->i).flatMap( (i) -> _alphabet.chars().mapToObj( (c) ->  word.substring(0,i) + (char)c + word.substring(i) )  );
        Stream<String> transposes = IntStream.range(0, word.length()-1).mapToObj((i)-> word.substring(0,i) + word.substring(i+1,i+2) + word.charAt(i) + word.substring(i+2) );

        return Stream.of( deletes,replaces,inserts,transposes ).flatMap((x)->x);
    }

    /**
     * Method to get the subset of words that appear in the dictionary from words.
     * @param words Stream with the words to compare.
     * @return Stream with the subset of words that appear in the dictionary from words
     */
    private Stream<String> known(Stream<String> words) {
        return words.filter( (word) -> _dictionaryWords.containsKey(word) );
    }

    /**
     * Method to get the most probable spelling correction for word
     * @param word String with the word to correct.
     * @return String with the word corrected.
     */
    private String correctWord(String word) {
        Optional<String> e1 = known(edits1(word)).max( (a, b) -> _dictionaryWords.get(a) - _dictionaryWords.get(b) );

        if(e1.isPresent())
            return _dictionaryWords.containsKey(word) ? word : e1.get();

        Optional<String> e2 = known(edits1(word).map( (w2)->edits1(w2) ).flatMap((x)->x)).max( (a,b) -> _dictionaryWords.get(a) - _dictionaryWords.get(b) );

        return (e2.isPresent() ? e2.get() : word);
    }

    /**
     * Method to check if a word is an OOV
     * @param word String the word to check
     * @return boolean
     */
    private boolean isOOV(String word) {
        return !_dictionaryWords.containsKey(word.toLowerCase())
                && Parser.isValidWord(word);
    }

    /**
     * Method to get all the words from a text.
     * @param text String with the text.
     * @return ArrayList with the words from the text.
     */
    private ArrayList<OOV> getOOVs(String text) {
        ArrayList<OOV> oovWords = new ArrayList<>();
        //Pattern p = Pattern.compile("[\\wÀÈÌÒÙÂÊÎÔÛÁÉÍÓÚÄËÏÖÜàèìòùâêîôûáéíóúäëïöüçñÑ\\-_'´]+");
        Pattern p = Pattern.compile("([^\\s]*)[\\s$]*");
        Matcher m = p.matcher(text);

        while (m.find()) {
            String word = text.substring(m.start(1), m.end(1));
            OOV oov = new OOV(word, m.start(), m.end());

            if (isOOV(word)) {
                Pattern p1 = Pattern.compile("[\\wÀÈÌÒÙÂÊÎÔÛÁÉÍÓÚÄËÏÖÜàèìòùâêîôûáéíóúäëïöüçñÑ\\-_'´]+");
                Matcher m1 = p1.matcher(text);

                if (m1.find()) {
                    oovWords.add(oov);
                }
            }
        }

        return oovWords;
    }
}