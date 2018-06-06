package com.jmorenov.tweetsccore.ner;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.IOException;
import java.util.*;

/**
 * StanfordNLP class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class StanfordNLP extends NER {
    private Properties props;
    private StanfordCoreNLP pipeline;
    private CoreDocument document;

    public StanfordNLP(String text) {
        props = new Properties();

        try {
            props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        props.setProperty("coref.algorithm", "neural");

        pipeline = new StanfordCoreNLP(props);
        document = new CoreDocument(text);

        pipeline.annotate(document);
    }

    public ArrayList<String> getTokens() {
        ArrayList<String> tokens = new ArrayList<>();

        for (CoreLabel token : document.tokens()) {
            tokens.add(token.toString());
        }

        return tokens;
    }

    public ArrayList<String> getTokensRegex() {
        String serializedClassifier = "edu/stanford/nlp/models/ner/spanish.kbp.ancora.distsim.s512.crf.ser.gz";
        ArrayList<String> tokenRegex = new ArrayList<>();

        try {
            AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

            List<List<CoreLabel>> out = classifier.classify(document.text());
            for (List<CoreLabel> sentence : out) {
                for (CoreLabel word : sentence) {
                    tokenRegex.add(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
                }
            }
        } catch (Exception ex) {}

        return tokenRegex;
    }
}