package com.jmorenov.tweetsccore.preprocess;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class RulesTest {
    @Test
    public void rulesShouldProcessTheRulesFile () throws IOException {
        String rulesFile = "preprocess/rules.txt";
        Rules rules = new Rules(rulesFile);

        assertEquals("Error processing the rules", 161, rules.getRules().size());
    }
}
