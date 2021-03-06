package com.jmorenov.tweetsccore.candidates.method;

import com.jmorenov.tweetsccore.candidates.Candidate;
import com.jmorenov.tweetsccore.extra.Dictionaries;
import com.jmorenov.tweetsccore.extra.OOV;

import java.util.ArrayList;
import java.util.List;

/**
 * L_LCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class L_LCandidatesMethod extends CandidatesMethod {
    /**
     * Constructor of the class.
     */
    public L_LCandidatesMethod() {}

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        List<Candidate> candidates = new ArrayList<>();
        int spacePosition = 1;
        List<String> words = Dictionaries.getInstance().getSpanishDictionary();
        words.addAll(Dictionaries.getInstance().getEntitiesDictionary());

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
