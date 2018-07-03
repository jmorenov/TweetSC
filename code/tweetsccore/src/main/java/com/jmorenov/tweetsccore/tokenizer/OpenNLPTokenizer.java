package com.jmorenov.tweetsccore.tokenizer;

import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.Token;
import opennlp.tools.languagemodel.NGramLanguageModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        try (InputStream modelIn = File.getStreamFromResources("opennlp/SpanishPOS.bin")) {
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
    public List<Token> getTokens(String text) {
        TokenizerME tokenizerME = new TokenizerME(openNLPTokenizerModel);
        Span[] tokensPos = tokenizerME.tokenizePos(text);
        String[] tokens = tokenizerME.tokenize(text);
        List<Token> tokenList = new ArrayList<>();

        for (int i = 0; i <  tokens.length; i++) {
            tokenList.add(new Token(tokens[i], tokensPos[i].getStart(), tokensPos[i].getEnd()));
        }

        return tokenList;
    }
}