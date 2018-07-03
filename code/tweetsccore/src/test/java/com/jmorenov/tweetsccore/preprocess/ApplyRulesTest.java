package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;
import com.jmorenov.tweetsccore.extra.Token;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ApplyRulesTest {
    @Test
    public void applyShouldApplyTheRules () throws IOException {
        String text = "Y to xk batman";
        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token("Y", 0, 0));
        tokens.add(new Token("to", 2, 3));
        tokens.add(new Token("xk", 5, 6));
        tokens.add(new Token("batman", 8, 13));

        ApplyRules applyRules = new ApplyRules();
        ApplyRulesResult applyRulesResult = applyRules.apply(tokens);

        List<OOV> oovs = applyRulesResult.getOOVList();

        assertEquals("Error applying the rules", 3, oovs.size());
        assertEquals("Error applying the rules", "to", oovs.get(0).getToken());
        assertEquals("Error applying the rules", "todo", oovs.get(0).getCorrection());
        assertEquals("Error applying the rules", Annotation.Variation, oovs.get(0).getAnnotation());
        assertEquals("Error applying the rules", 2, oovs.get(0).getStartPosition());
        assertEquals("Error applying the rules", 3, oovs.get(0).getEndPosition());
        assertEquals("Error applying the rules", "xk", oovs.get(1).getToken());
        assertEquals("Error applying the rules", "porque", oovs.get(1).getCorrection());
        assertEquals("Error applying the rules", Annotation.Variation, oovs.get(1).getAnnotation());
        assertEquals("Error applying the rules", 5, oovs.get(1).getStartPosition());
        assertEquals("Error applying the rules", 6, oovs.get(1).getEndPosition());
        assertEquals("Error applying the rules", "batman", oovs.get(2).getToken());
        assertEquals("Error applying the rules", "Batman", oovs.get(2).getCorrection());
        assertEquals("Error applying the rules", Annotation.Variation, oovs.get(2).getAnnotation());
        assertEquals("Error applying the rules", 8, oovs.get(2).getStartPosition());
        assertEquals("Error applying the rules", 13, oovs.get(2).getEndPosition());
    }
}