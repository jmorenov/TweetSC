package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FastTextCandidatesMethodTest {
    @Ignore
    @Test
    public void fastTextCandidatesMethodShouldGenerateTheCorrectCandidates () throws Exception {
        OOV oov = new OOV("poreso", 0, 5);
        FastTextCandidatesMethod method = new FastTextCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 1, candidates.size());
        assertEquals("Error generating candidates", "por eso", candidates.get(0).getCandidate());
    }
}