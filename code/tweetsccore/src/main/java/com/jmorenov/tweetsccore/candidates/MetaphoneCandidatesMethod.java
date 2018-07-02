package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * MetaphoneCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class MetaphoneCandidatesMethod extends CandidatesMethod {
    /**
     * Constructor of the class.
     */
    public MetaphoneCandidatesMethod() throws IOException {
        //StringUtils.getLevenshteinDistance(s1, s2);
    }

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        return null;
    }

    /**
     * Method to obtain the method description.
     * @return CandidatesMethodType
     */
    @Override
    public CandidatesMethodType getMethod() {
        return CandidatesMethodType.Metaphone;
    }
}
