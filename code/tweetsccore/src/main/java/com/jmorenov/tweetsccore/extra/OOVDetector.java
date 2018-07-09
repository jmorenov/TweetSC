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
    /**
     * Constructor of the class.
     */
    public OOVDetector() { }

    /**
     * Method to detect the OOV.
     * @param tokens List of Token
     * @return List of OOV
     */
    public List<OOV> detectOOV(List<Token> tokens) {
        List<OOV> oovs = new ArrayList<>();

        if (Dictionaries.getInstance().getSpanishDictionary() != null) {
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
        return Dictionaries.getInstance().getEntitiesDictionary().contains(oov);
    }

    /**
     * Method to get if an OOV is english.
     * @param oov String
     * @return boolean
     */
    private boolean isAEnglishOOV(String oov) {
        return Dictionaries.getInstance().getEnglishDictionary().contains(oov);
    }

    /**
     * Method to check if a word is an OOV
     * @param word String the word to check
     * @return boolean
     */
    private boolean isOOV(String word) {
        return !Dictionaries.getInstance().getSpanishDictionary().contains(word.toLowerCase())
                && Parser.isValidWord(word);
    }
}
