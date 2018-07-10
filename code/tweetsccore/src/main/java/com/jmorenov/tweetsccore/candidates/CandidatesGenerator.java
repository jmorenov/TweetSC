package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.candidates.method.CandidatesMethod;
import com.jmorenov.tweetsccore.extra.OOV;

import java.util.List;

/**
 * CandidatesGenerator class that define the generator of candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class CandidatesGenerator {
    private List<CandidatesMethod> methods;

    /**
     * Constructor of the class.
     * @param methods List of CandidatesMethod
     */
    public CandidatesGenerator(List<CandidatesMethod> methods) {
        this.methods = methods;
    }

    /**
     * Method to generate the candidates of an OOV.
     * @param oovs List of OOV
     * @return List of OOV
     */
    public List<OOV> generateCandidates(List<OOV> oovs) {
        for (CandidatesMethod method : methods) {
            for (OOV oov : oovs) {
                List<Candidate> candidates = method.generateCandidates(oov);

                oov.addAllCandidates(candidates);
            }
        }

        return oovs;
    }
}
