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
public class StanfordNLPNER extends NER {
    private Properties props;
    private AbstractSequenceClassifier<CoreLabel> classifier;

    public StanfordNLPNER(String text) {
        props = new Properties();

        try {
            props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        props.setProperty("coref.algorithm", "neural");

        String serializedClassifier = "edu/stanford/nlp/models/ner/spanish.kbp.ancora.distsim.s512.crf.ser.gz";

        try {
            classifier = CRFClassifier.getClassifier(serializedClassifier);
        } catch (Exception ex) {}

    }

    /**
     * Method to get a list with the NER Elements detected.
     * @return List with the NER Elements.
     */
    public List<NERELement> getNERElements(String text) {
        ArrayList<NERELement> nerElements = new ArrayList<>();

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(text);

        pipeline.annotate(document);

        List<List<CoreLabel>> out = classifier.classify(document.text());
        for (List<CoreLabel> sentence : out) {
            for (CoreLabel word : sentence) {
                NERELement nereLement = new NERELement(word.word(), word.get(CoreAnnotations.AnswerAnnotation.class));
                nerElements.add(nereLement);
            }
        }

        return nerElements;
    }
}