package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CandidatesRankingTest {
    @Test
    public void freelingTokenizerShouldReturnTheCorrectResult() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate("camionera", CandidatesMethodType.LevenshteinFST));
        candidates.add(new Candidate("camionero", CandidatesMethodType.LevenshteinFST));
        candidates.add(new Candidate("camionería", CandidatesMethodType.LevenshteinFST));
        candidates.add(new Candidate("camión", CandidatesMethodType.LevenshteinFST));
        candidates.add(new Candidate("camiones", CandidatesMethodType.LevenshteinFST));

        OOV oov = new OOV("camion", 13, 18);

        oov.setCandidates(candidates);

        CandidatesRanking candidatesRanking = new CandidatesRanking();

        List<Candidate> candidatesRanked = candidatesRanking.rank("Yo tengo un camion", oov);

        assertEquals("failure - the ranking of candidates is incorrect", "camiones", candidatesRanked.get(0).getCandidate());
    }
}
