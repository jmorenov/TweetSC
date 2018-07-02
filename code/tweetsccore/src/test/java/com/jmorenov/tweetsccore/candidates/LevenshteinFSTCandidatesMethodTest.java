package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class LevenshteinFSTCandidatesMethodTest {
    @Test
    public void levenshteinDistanceCandidatesMethodShouldGenerateTheCorrectCandidates () throws Exception {
        OOV oov = new OOV("camion", 0, 5);
        LevenshteinFSTCandidatesMethod method = new LevenshteinFSTCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 46, candidates.size());
    }
}