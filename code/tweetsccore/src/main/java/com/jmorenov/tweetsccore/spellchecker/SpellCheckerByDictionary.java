package com.jmorenov.tweetsccore.spellchecker;

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

public class SpellCheckerByDictionary {
    private Map<String, Integer> _dictionaryWords;
    private Map<String, Integer> _nombresPropiosWords;
    private static String _alphabet = "aábcdeéfghiíjklmnñoópqrstuúvwxyz";
    private static final String _dictionaryFileName = "dic.txt";
    private static final String _nombresPropiosFileName = "nombres_propios.txt";

    public SpellCheckerByDictionary() throws IOException {
        readDictionaries();
    }

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

        textCorrected = textCorrected + "\n";

        return textCorrected;
    }

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

    private void readDictionaries() throws IOException {
        _dictionaryWords = readDictionary(_dictionaryFileName);
        _nombresPropiosWords = readDictionary(_nombresPropiosFileName);
    }

    private Map<String, Integer> readDictionary(String fileName) throws IOException {
        InputStream streamOfDictionaryFile = getClass().getClassLoader().getResourceAsStream(fileName);
        byte[] bytesOfDictionaryFile = IOUtils.toByteArray(streamOfDictionaryFile);
        Map<String, Integer> dictionaryMap = new HashMap<String, Integer>();

        Stream.of(new String(bytesOfDictionaryFile).toLowerCase().split("\n")).forEach( (word) ->{
            dictionaryMap.compute( word, (k,v) -> v == null ? 1 : v + 1  );
        });

        return dictionaryMap;
    }

    private Stream<String> edits1(final String word) {
        Stream<String> deletes    = IntStream.range(0, word.length())  .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));
        Stream<String> replaces   = IntStream.range(0, word.length())  .mapToObj((i)->i).flatMap( (i) -> _alphabet.chars().mapToObj( (c) ->  word.substring(0,i) + (char)c + word.substring(i+1) )  );
        Stream<String> inserts    = IntStream.range(0, word.length()+1).mapToObj((i)->i).flatMap( (i) -> _alphabet.chars().mapToObj( (c) ->  word.substring(0,i) + (char)c + word.substring(i) )  );
        Stream<String> transposes = IntStream.range(0, word.length()-1).mapToObj((i)-> word.substring(0,i) + word.substring(i+1,i+2) + word.charAt(i) + word.substring(i+2) );

        return Stream.of( deletes,replaces,inserts,transposes ).flatMap((x)->x);
    }

    private Stream<String> known(Stream<String> words) {
        return words.filter( (word) -> _dictionaryWords.containsKey(word) );
    }

    private String correctWord(String word) {
        Optional<String> e1 = known(edits1(word)).max( (a, b) -> _dictionaryWords.get(a) - _dictionaryWords.get(b) );

        if(e1.isPresent())
            return _dictionaryWords.containsKey(word) ? word : e1.get();

        Optional<String> e2 = known(edits1(word).map( (w2)->edits1(w2) ).flatMap((x)->x)).max( (a,b) -> _dictionaryWords.get(a) - _dictionaryWords.get(b) );

        return (e2.isPresent() ? e2.get() : word);
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

    private String removeEmojiFromText(String text) {
        return EmojiParser.removeAllEmojis(text);
    }

    private Boolean isUrl(String word) {
        return word.matches("http[s]?://(?:[a-zA-Z]|[0-9]|[$-_@.&+]|[!*\\(\\),]|(?:%[0-9a-fA-F][0-9a-fA-F]))+");
    }

    private Boolean isUsername(String word) {
        return word.matches("@[a-zA-Z0-9_]*$");
    }

    private Boolean isHashtag(String word) {
        return word.matches("#[a-zA-Z0-9_]*$");
    }

    private Boolean validWord(String word) {
        return !isUsername(word) && !isHashtag(word) && !isUrl(word);
    }

    private Boolean isPunctuationSign(String word) {
        return !word.matches("^[a-zA-Z0-9_]*$");
    }

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
