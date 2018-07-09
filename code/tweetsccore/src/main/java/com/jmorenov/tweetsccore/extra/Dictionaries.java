package com.jmorenov.tweetsccore.extra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dictionaries class to access all the dictionaries.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Dictionaries {
    private static Dictionaries instance = null;
    private static String spanishDictionaryFile = "aspellNormalized.dict";
    private static String entitiesDictionaryFile = "entities.dict";
    private static String englishDictionaryFile = "english.txt";
    private static String spanishPhoneticDictionaryFile = "aspellNormalizedPhonetic.dict";
    private List<String> spanishDictionary = new ArrayList<>();
    private List<String> englishDictionary = new ArrayList<>();
    private List<String> entitiesDictionary = new ArrayList<>();
    private Map<String, String> spanishPhoneticDictionary = new HashMap<>();

    /**
     * Constructor of the class.
     */
    protected Dictionaries() {
        try {
            this.spanishDictionary.addAll(File.readDictionaryFromResources(spanishDictionaryFile));
            this.englishDictionary.addAll(File.readDictionaryFromResources(englishDictionaryFile));
            this.entitiesDictionary.addAll(File.readDictionaryFromResources(entitiesDictionaryFile));

            List<String> phoneticWordsDictionary = File.readDictionaryFromResources(spanishPhoneticDictionaryFile);

            for (String phoneticWordsDictionaryLine : phoneticWordsDictionary) {
                String wordsLine[] = phoneticWordsDictionaryLine.split(" : ");

                this.spanishPhoneticDictionary.put(wordsLine[0], wordsLine[1]);
            }
        } catch (IOException ex) {
            this.spanishDictionary = this.englishDictionary =
                    this.entitiesDictionary = null;
            this.spanishPhoneticDictionary = null;
        }
    }

    /**
     * Method to get the instance of the class.
     * @return Dictionaries
     */
    public static Dictionaries getInstance() {
        if(instance == null) {
            instance = new Dictionaries();
        }
        return instance;
    }

    /**
     * Method to get all the dictionaries.
     * @return List String
     */
    public List<String> getAllDictionaries() {
        List<String> dictionaries = new ArrayList<>();

        dictionaries.addAll(this.spanishDictionary);
        dictionaries.addAll(this.englishDictionary);
        dictionaries.addAll(this.entitiesDictionary);

        return dictionaries;
    }

    /**
     * Method to get the spanish dictionary
     * @return List String
     */
    public List<String> getSpanishDictionary() {
        return this.spanishDictionary;
    }

    /**
     * Method to get the english dictionary
     * @return List String
     */
    public List<String> getEnglishDictionary() {
        return englishDictionary;
    }

    /**
     * Method to get the entities dictionary
     * @return List String
     */
    public List<String> getEntitiesDictionary() {
        return entitiesDictionary;
    }

    /**
     * Method to get the spanish phonetic dictionary
     * @return Map String String
     */
    public Map<String, String> getSpanishPhoneticDictionary() {
        return spanishPhoneticDictionary;
    }
}
