package com.jmorenov.tweetsccore.tokenizer;

import edu.stanford.nlp.international.spanish.process.SpanishTokenizer;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.TokenizerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * StanfordNLPTokenizer class to tokenize a text.
 * https://stanfordnlp.github.io/CoreNLP/
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class StanfordNLPTokenizer extends Tokenizer {
    private TokenizerFactory<CoreLabel> tokenizerFactory;

    /**
     * Constructor of the class
     */
    public StanfordNLPTokenizer() throws IOException {
        Properties props = new Properties();
        TokenizerAnnotator.TokenizerType type = TokenizerAnnotator.TokenizerType.Spanish;

        props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));

        String options = props.getProperty("tokenize.options", null);

        if (options == null) {
            options = type.getDefaultOptions();
        }

        tokenizerFactory = SpanishTokenizer.factory(new CoreLabelTokenFactory(), options);
    }

    /**
     * Method to get the tokens from the text.
     * @param text String with the text
     * @return List of String with the tokens
     */
    @Override
    public List<String> getTokens(String text) {
        Reader reader = new StringReader(text);

        edu.stanford.nlp.process.Tokenizer<CoreLabel> tokens = tokenizerFactory.getTokenizer(reader);
        List<String> tokenList = new ArrayList<>();

        for(CoreLabel token : tokens.tokenize()) {
            tokenList.add(token.toString());
        }

        return tokenList;
    }
}