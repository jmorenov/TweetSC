package com.jmorenov.tweetsccore.analyzer;

import java.util.List;

/**
 * Analyzer abstract class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class Analyzer {
    /**
     * Method to get a list with the Analysis Elements.
     * @return List with the Analysis Elements.
     */
    public abstract List<AnalysisElement> analyzeText(String text);
}
