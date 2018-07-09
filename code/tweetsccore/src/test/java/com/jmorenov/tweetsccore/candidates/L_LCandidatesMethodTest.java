package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class L_LCandidatesMethodTest {
    @Test
    public void L_LCandidatesMethodShouldGenerateTheCorrectCandidates () {
        OOV oov = new OOV("poreso", 0, 5);
        L_LCandidatesMethod method = new L_LCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 1, candidates.size());
        assertEquals("Error generating candidates", "por eso", candidates.get(0).getCandidate());
    }
}
