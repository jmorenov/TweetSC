package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.candidates.CandidatesGenerator;
import com.jmorenov.tweetsccore.candidates.CandidatesMethod;
import com.jmorenov.tweetsccore.candidates.LevenshteinFSTCandidatesMethod;
import com.jmorenov.tweetsccore.candidates.MetaphoneCandidatesMethod;
import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CandidatesGeneratorTest {
    @Test
    public void candidatesGeneratorShouldReturnTheCorrectResult() {
        List<OOV> oovs = new ArrayList<>();
        oovs.add(new OOV("illo", 0, 3));
        oovs.add(new OOV("ke", 5, 6));
        oovs.add(new OOV("pacha", 8, 12));

        List<CandidatesMethod> methods = new ArrayList<>();

        methods.add(new LevenshteinFSTCandidatesMethod());
        methods.add(new MetaphoneCandidatesMethod());

        CandidatesGenerator candidatesGenerator = new CandidatesGenerator(methods);

        List<OOV> oovsWithCandidates = candidatesGenerator.generateCandidates(oovs);

        assertEquals("failure - the generation of candidates is incorrect", 162, oovsWithCandidates.get(0).getCandidates().size());
        assertEquals("failure - the generation of candidates is incorrect", 201, oovsWithCandidates.get(1).getCandidates().size());
        assertEquals("failure - the generation of candidates is incorrect", 260, oovsWithCandidates.get(2).getCandidates().size());
    }
}
