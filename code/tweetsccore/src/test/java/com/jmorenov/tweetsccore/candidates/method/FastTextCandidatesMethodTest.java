package com.jmorenov.tweetsccore.candidates.method;

import com.jmorenov.tweetsccore.candidates.Candidate;
import com.jmorenov.tweetsccore.candidates.method.FastTextCandidatesMethod;
import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class FastTextCandidatesMethodTest {
    @Test
    public void fastTextCandidatesMethodShouldGenerateTheCorrectCandidates () throws Exception {
        OOV oov = new OOV("poreso", 0, 5);
        FastTextCandidatesMethod method = new FastTextCandidatesMethod();
        List<Candidate> candidates = method.generateCandidates(oov);

        assertEquals("Error generating candidates", 28, candidates.size());
        assertEquals("Error generating candidates", "aballestaron", candidates.get(0).getCandidate());
    }
}