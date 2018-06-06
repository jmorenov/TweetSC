package com.jmorenov.tweetsccore.spellchecker;

import com.jmorenov.tweetsccore.extra.File;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static String _alphabet = "aábcdeéfghiíjklmnñoópqrstuúvwxyz";
    private static final String _dictionaryFileName = "dic.txt";
    private static final String _nombresPropiosFileName = "nombres_propios.txt";

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
     * Method to get the corrected text for Tweet Norm 2013.
     * @param text String with the text to correct.
     * @return String with the correctec text.
     */
    public String correctTextForTweetNorm(String text) {
        String textCorrected = "";

        if (!text.equals("Not Available")) {
            String normalizedText = getValidText(text);
            ArrayList<String> wordsOfText = getWords(normalizedText);

            for (String word : wordsOfText) {
                if (!isPunctuationSign(word)) {
                    if (!_dictionaryWords.containsKey(word)) {
                        if (_nombresPropiosWords.containsKey(word)) {
                            textCorrected = textCorrected + "\n\t" + word + " 1 -";
                        } else {
                            String wordCorrect = correctWord(word);

                            if (wordCorrect.equals(word)) {
                                textCorrected = textCorrected + "\n\t" + word + " 2 -";
                            } else {
                                textCorrected = textCorrected + "\n\t" + word + " 0 " + wordCorrect;
                            }
                        }
                    }
                }
            }
        }

        return textCorrected;
    }

    /**
     * Method to get the corrected text.
     * @param text String with the text to correct.
     * @return String with the correctec text.
     */
    public String correctText(String text) {
        String textCorrected = "";
        String normalizedText = getValidText(text);
        ArrayList<String> wordsOfText = getWords(normalizedText);


        for (String word : wordsOfText) {
            if (!isPunctuationSign(word)) {
                if (!_dictionaryWords.containsKey(word)) {
                    if (_nombresPropiosWords.containsKey(word)) {
                        textCorrected = textCorrected + word;
                    } else {
                        String wordCorrect = correctWord(word);

                        if (wordCorrect.equals(word)) {
                            textCorrected = textCorrected + word;
                        } else {
                            textCorrected = textCorrected + wordCorrect;
                        }
                    }
                } else {
                    textCorrected = textCorrected + word;
                }

                textCorrected = textCorrected + " ";
            }
        }

        return textCorrected;
    }

    /**
     * Method to read the dictionaries from the files.
     * @throws IOException when the file not found.
     */
    private void readDictionaries() throws IOException {
        _dictionaryWords = readDictionary(_dictionaryFileName);
        _nombresPropiosWords = readDictionary(_nombresPropiosFileName);
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

        Stream.of(new String(bytesOfDictionaryFile).toLowerCase().split("\n")).forEach( (word) ->{
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
     * Method to get all the words from a text.
     * @param text String with the text.
     * @return ArrayList with the words from the text.
     */
    private ArrayList<String> getWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        Pattern p = Pattern.compile("[\\w']+");
        Matcher m = p.matcher(text);

        while (m.find()) {
            words.add(text.substring(m.start(), m.end()));
        }

        return words;
    }

    /**
     * Method to remove the emojis from a text.
     * @param text String with the text to remove the emojis.
     * @return String with the text without the emojis.
     */
    private String removeEmojiFromText(String text) {
        return EmojiParser.removeAllEmojis(text);
    }

    /**
     * Method to check if a word is a Url.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    private Boolean isUrl(String word) {
        return word.matches("http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\(\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+");
    }

    /**
     * Method to check if a word is a username of Twitter.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    private Boolean isUsername(String word) {
        return word.matches("@[a-zA-Z0-9_]*$");
    }

    /**
     * Method to check if a word is a hashtag of Twitter.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    private Boolean isHashtag(String word) {
        return word.matches("#[a-zA-Z0-9_]*$");
    }

    /**
     * Method to check if a word is a valid word.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    private Boolean validWord(String word) {
        return !isUsername(word) && !isHashtag(word) && !isUrl(word);
    }

    /**
     * Method to check if a word is a punctuation sign.
     * @param word String with the word to check.
     * @return Boolean control parameter.
     */
    private Boolean isPunctuationSign(String word) {
        return !word.matches("^[a-zA-Z0-9_]*$");
    }

    /**
     * Method to get the valid text from a text.
     * @param text String with the text.
     * @return String with the valid text.
     */
    private String getValidText(String text) {
        String textWithoutEmojis = removeEmojiFromText(text.toLowerCase());
        String validText = "";

        ArrayList<String> wordsOfText = getWords(textWithoutEmojis);

        for (String word : wordsOfText) {
            if (validWord(word)) {
                validText = validText + word + " ";
            }
        }

        return validText;
    }
}
