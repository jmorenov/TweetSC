package com.jmorenov.tweetsccore.extra;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OOVDetectorTest {
    @Test
    public void freelingTokenizerShouldReturnTheCorrectResult() {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token("illo", 0, 3));
        tokens.add(new Token("ke", 5, 6));
        tokens.add(new Token("pacha", 8, 12));
        tokens.add(new Token("nada", 14, 17));

        OOVDetector oovDetector = new OOVDetector();

        List<OOV> oovs = oovDetector.detectOOV(tokens);

        assertEquals("failure - the detection of OOV is incorrect", 3, oovs.size());
    }
}
