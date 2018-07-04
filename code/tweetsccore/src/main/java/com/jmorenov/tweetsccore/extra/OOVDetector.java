package com.jmorenov.tweetsccore.extra;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OOVDetector class that define a method to detect OOV.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class OOVDetector {
    private static final String dictionaryFileName = "aspellNormalized.dict";
    private static final String nombresPropiosFileName = "nombres_propios.txt";
    private static final String entitiesFileName = "entities.txt";
    private static final String englishFileName = "english.txt";
    private static List<String> dictionaryWords;
    private static List<String> nombresPropiosWord;
    private static List<String > entitiesWords;
    private static List<String> englishWords;

    /**
     * Constructor of the class.
     */
    public OOVDetector() {
        try {
            dictionaryWords = File.readDictionaryFromResources(dictionaryFileName);
            nombresPropiosWord = File.readDictionaryFromResources(nombresPropiosFileName);
            entitiesWords = File.readDictionaryFromResources(entitiesFileName);
            englishWords = File.readDictionaryFromResources(englishFileName);
        } catch (Exception ex) {
            dictionaryWords = nombresPropiosWord = entitiesWords = englishWords = null;
        }
    }

    /**
     * Method to detect the OOV.
     * @param tokens List of Token
     * @return List of OOV
     */
    public List<OOV> detectOOV(List<Token> tokens) {
        List<OOV> oovs = new ArrayList<>();

        if (dictionaryWords != null) {
            for (Token token : tokens) {
                if (isOOV(token.getText())) {
                    OOV oov = new OOV(token);

                    if (isACorrectOOV(oov.getToken())) {
                        oov.setAnnotation(Annotation.Correct);
                    } else if (isAEnglishOOV(oov.getToken())){
                        oov.setAnnotation(Annotation.NoEs);
                    }

                    oovs.add(oov);
                }
            }
        }

        return oovs;
    }

    /**
     * Method to get if an OOV is correct.
     * @param oov String
     * @return boolean
     */
    private boolean isACorrectOOV(String oov) {
        return entitiesWords.contains(oov) || nombresPropiosWord.contains(oov);
    }

    /**
     * Method to get if an OOV is english.
     * @param oov String
     * @return boolean
     */
    private boolean isAEnglishOOV(String oov) {
        return englishWords.contains(oov);
    }

    /**
     * Method to check if a word is an OOV
     * @param word String the word to check
     * @return boolean
     */
    private boolean isOOV(String word) {
        return !dictionaryWords.contains(word.toLowerCase())
                && Parser.isValidWord(word);
    }
}
