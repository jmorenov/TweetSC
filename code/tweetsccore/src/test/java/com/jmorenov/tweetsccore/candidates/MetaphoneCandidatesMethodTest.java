package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class MetaphoneCandidatesMethodTest {
    @Test
    public void metaphoneCandidatesMethodShouldGenerateTheCorrectCandidates () throws Exception {
        OOV oov = new OOV("camion", 0, 5);
        MetaphoneCandidatesMethod method = new MetaphoneCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 234, candidates.size());
    }
}