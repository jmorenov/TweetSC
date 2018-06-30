package com.jmorenov.tweetsccore.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * NGramTokenizer class to tokenize a text.
 * https://opennlp.apache.org/docs/1.8.4/manual/opennlp.html
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class NGramTokenizer extends Tokenizer {
    private weka.core.tokenizers.NGramTokenizer nGramTokenizer;

    /**
     * Constructor of the class
     */
    public NGramTokenizer() {
        nGramTokenizer = new weka.core.tokenizers.NGramTokenizer();

        nGramTokenizer.setNGramMaxSize(6);
        nGramTokenizer.setNGramMinSize(1);
    }

    /**
     * Method to get the tokens from the text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    @Override
    public List<String> getTokens(String text) {
        List<String> tokens = new ArrayList<>();

        nGramTokenizer.tokenize(text);

        while (nGramTokenizer.hasMoreElements()) {
            tokens.add(nGramTokenizer.nextElement());
        }

        return tokens;
    }
}
