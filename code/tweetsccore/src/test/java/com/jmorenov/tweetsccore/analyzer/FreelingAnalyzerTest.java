package com.jmorenov.tweetsccore.analyzer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FreelingAnalyzerTest {
    @Test
    public void freelingAnalyzerShouldReturnTheCorrectResult() {
        String text = "Hola mi nombre es Javier xD.";
        FreelingAnalyzer freelingAnalyzer = new FreelingAnalyzer();

        assertEquals("failure - the analysis is incorrect", "Hola : hola : I : No senses : No multiword", freelingAnalyzer.analyzeText(text).get(0).toString());
    }
}