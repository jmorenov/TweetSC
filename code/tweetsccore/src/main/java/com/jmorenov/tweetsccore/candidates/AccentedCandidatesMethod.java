package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AccentedCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class AccentedCandidatesMethod extends CandidatesMethod {
    private static final String[] files = {"aspellNormalized.dict", "nombres_propios.txt", "entities.txt"};
    private Map<Character, Character> accentMap;
    private List<String> words;

    /**
     * Constructor of the class.
     */
    public AccentedCandidatesMethod() {
        try {
            this.accentMap = new HashMap<>();

            this.accentMap.put('a', 'á');
            this.accentMap.put('e', 'é');
            this.accentMap.put('i', 'í');
            this.accentMap.put('o', 'ó');
            this.accentMap.put('u', 'ú');
            this.accentMap.put('n', 'ñ');

            words = new ArrayList<>();

            for (String file : files) {
                words.addAll(File.readDictionaryFromResources(file));
            }
        } catch (IOException ex) {
            words = null;
        }
    }

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        List<Candidate> candidates = new ArrayList<>();

        if (words != null && !isAccentedWord(oov.getToken())) {
            String word = oov.getToken().toLowerCase();
            StringBuilder wordStringBuilder = new StringBuilder(word);

            for (int i = 0; i < oov.getToken().length(); i++) {
                Character character = oov.getToken().charAt(i);

                if (this.accentMap.containsKey(character)) {
                    wordStringBuilder.setCharAt(i, this.accentMap.get(character));

                    if (words.contains(wordStringBuilder.toString())) {
                        candidates.add(new Candidate(wordStringBuilder.toString(), CandidatesMethodType.Accented));
                    } else if (words.contains(StringUtils.capitalize(wordStringBuilder.toString()))) {
                        candidates.add(new Candidate(StringUtils.capitalize(wordStringBuilder.toString()), CandidatesMethodType.Accented));
                    }

                    wordStringBuilder.setCharAt(i, character);
                }
            }
        }

        return candidates;
    }

    private boolean isAccentedWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'á'
                    || word.charAt(i) == 'é'
                    || word.charAt(i) == 'í'
                    || word.charAt(i) == 'ó'
                    || word.charAt(i) == 'ú') {
                return true;
            }
        }

        return false;
    }

    /**
     * Method to obtain the method description.
     * @return CandidatesMethodType
     */
    @Override
    public CandidatesMethodType getMethod() {
        return CandidatesMethodType.Accented;
    }
}
