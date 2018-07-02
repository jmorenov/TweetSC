package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;

import java.util.List;

/**
 * CandidatesMethod abstract class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class CandidatesMethod {
    /**
     * Abstract method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    public abstract List<Candidate> generateCandidates(OOV oov);

    /**
     *  Abstract method to obtain the method description.
     * @return CandidatesMethodType
     */
    public abstract CandidatesMethodType getMethod();
}