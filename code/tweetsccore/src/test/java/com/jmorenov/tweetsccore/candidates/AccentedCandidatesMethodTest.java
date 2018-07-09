package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AccentedCandidatesMethodTest {
    @Test
    public void accentedCandidatesMethodShouldGenerateTheCorrectCandidatesFromCamion () {
        OOV oov = new OOV("camion", 0, 5);
        AccentedCandidatesMethod method = new AccentedCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 1, candidates.size());
        assertEquals("Error generating candidates", "camión", candidates.get(0).getCandidate());
    }

    @Test
    public void accentedCandidatesMethodShouldGenerateTheCorrectCandidatesFromEspana () {
        OOV oov = new OOV("espana", 0, 5);
        AccentedCandidatesMethod method = new AccentedCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 1, candidates.size());
        assertEquals("Error generating candidates", "España", candidates.get(0).getCandidate());
    }
}
