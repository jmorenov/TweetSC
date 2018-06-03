package com.jmorenov.tweetsccore.postagging;

/*import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.tokensregex.TokenSequenceMatcher;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;*/

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * StanfordNLP class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class StanfordNLP extends POSTagging {
    /**
     * Method to get the tokens from a text.
     * @return ArrayList with the tokens.
     */
    public ArrayList<String> getTokens() {
        ArrayList<String> tokens = new ArrayList<String>();

        return tokens;
    }

    /*private Properties props;
    private StanfordCoreNLP pipeline;
    private CoreDocument document;

    public StanfordNLP(String text) {
        props = new Properties();

        try {
            props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        props.setProperty("coref.algorithm", "neural");

        pipeline = new StanfordCoreNLP(props);
        document = new CoreDocument(text);

        pipeline.annotate(document);
    }

    public ArrayList<String> getTokens() {
        ArrayList<String> tokens = new ArrayList<String>();

        for (CoreLabel token : document.tokens()) {
            tokens.add(token.toString());
        }

        return tokens;
    }

    public ArrayList<String> getTokenRegex() {
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
    }*/
}