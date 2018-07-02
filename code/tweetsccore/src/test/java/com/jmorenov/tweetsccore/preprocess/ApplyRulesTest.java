package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ApplyRulesTest {
    @Test
    public void applyShouldApplyTheRules () throws IOException {
        String text = "Y esto to xk yo lo digo";
        ApplyRules applyRules = new ApplyRules();
        List<OOV> oovs = applyRules.apply(text);

        assertEquals("Error applying the rules", 2, oovs.size());
        assertEquals("Error applying the rules", "to", oovs.get(0).getToken());
        assertEquals("Error applying the rules", "todo", oovs.get(0).getCorrection());
        assertEquals("Error applying the rules", Annotation.Variation, oovs.get(0).getAnnotation());
        assertEquals("Error applying the rules", 7, oovs.get(0).getStartPosition());
        assertEquals("Error applying the rules", 9, oovs.get(0).getEndPosition());
        assertEquals("Error applying the rules", "xk", oovs.get(1).getToken());
        assertEquals("Error applying the rules", "porque", oovs.get(1).getCorrection());
        assertEquals("Error applying the rules", Annotation.Variation, oovs.get(1).getAnnotation());
        assertEquals("Error applying the rules", 10, oovs.get(1).getStartPosition());
        assertEquals("Error applying the rules", 12, oovs.get(1).getEndPosition());
    }
}