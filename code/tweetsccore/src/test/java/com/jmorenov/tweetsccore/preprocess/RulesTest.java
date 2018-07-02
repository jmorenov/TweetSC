package com.jmorenov.tweetsccore.preprocess;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class RulesTest {
    @Test
    public void rulesShouldProcessTheRulesFile () throws IOException {
        String rulesFile = Paths.get("src","main","resources", "preprocess", "rules.txt").toAbsolutePath() + "/";
        Rules rules = new Rules(rulesFile);

        assertEquals("Error processing the rules", 6, rules.getRules().size());
    }
}
