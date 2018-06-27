package com.jmorenov.tweetsccore.tokenizer;

import com.jmorenov.tweetsccore.extra.File;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * OpenNLPTokenizer class to tokenize a text.
 * https://opennlp.apache.org/docs/1.8.4/manual/opennlp.html
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class OpenNLPTokenizer extends Tokenizer {
    private opennlp.tools.tokenize.Tokenizer openNLPTokenizer;
    private opennlp.tools.tokenize.TokenizerModel openNLPTokenizerModel;

    /**
     * Constructor of the class
     * @throws IOException
     */
    public OpenNLPTokenizer() throws IOException {
        try (InputStream modelIn = File.getStreamFromResources("opennlp/SpanishTokChunk.bin")) {
            openNLPTokenizerModel = new opennlp.tools.tokenize.TokenizerModel(modelIn);
            openNLPTokenizer = new TokenizerME(openNLPTokenizerModel);
        }
    }

    /**
     * Method to get the tokens from the text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    @Override
    public List<String> getTokens(String text) {
        Span tokenSpans[] = openNLPTokenizer.tokenizePos(text);
        String tokens[] = openNLPTokenizer.tokenize(text);

        TokenizerME tokenizerME = new TokenizerME(openNLPTokenizerModel);
        tokens = tokenizerME.tokenize(text);
        double tokenProbs[] = tokenizerME.getTokenProbabilities();

        return Arrays.asList(tokens);
    }
}