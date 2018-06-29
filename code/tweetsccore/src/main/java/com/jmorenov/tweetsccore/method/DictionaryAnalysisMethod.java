package com.jmorenov.tweetsccore.method;

import java.io.IOException;

/**
 * DictionaryAnalysisMethod class with the method of spell checker with dictionaries with analysis.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class DictionaryAnalysisMethod extends DictionaryMethod {
    /**
     * Default constructor of the class.
     * @throws IOException when the file not found.
     */
    public DictionaryAnalysisMethod() throws IOException {
        super();
    }

    /**
     * Method to get the String of the method.
     * @return String with the String of the method.
     */
    @Override
    public String toString() {
        return "DictionaryAnalysisMethod";
    }

}