package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class L_LCandidatesMethod extends CandidatesMethod {
    private static final String[] files = {"aspellNormalized.dict", "nombres_propios.txt", "entities.txt"};
    private List<String> words;
    /**
     * Constructor of the class.
     */
    public L_LCandidatesMethod() {
        try {
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
        int spacePosition = 1;

        while (words != null && spacePosition < oov.getToken().length()) {
            String posibleCandidatePreSpace = oov.getToken().substring(0, spacePosition);
            String posibleCandidatePostSpace = oov.getToken().substring(spacePosition, oov.getToken().length());

            if (words.contains(posibleCandidatePreSpace) && words.contains(posibleCandidatePostSpace)) {
                candidates.add(new Candidate(posibleCandidatePreSpace + " " + posibleCandidatePostSpace, CandidatesMethodType.L_L));
            }

            spacePosition++;
        }

        return candidates;
    }

    /**
     * Method to obtain the method description.
     * @return CandidatesMethodType
     */
    @Override
    public CandidatesMethodType getMethod() {
        return CandidatesMethodType.L_L;
    }
}
