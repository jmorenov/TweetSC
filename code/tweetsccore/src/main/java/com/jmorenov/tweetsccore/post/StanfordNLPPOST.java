package com.jmorenov.tweetsccore.post;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.CoreMap;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class StanfordNLPPOST extends POST {
    private String text;

    public StanfordNLPPOST(String text) {
        this.text = text;
    }

    public String getTags() {
        String tagged = "";
        Properties props = new Properties();

        try {
            props.load(IOUtils.readerFromString("StanfordCoreNLP-spanish.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        props.setProperty("annotators","tokenize, ssplit, pos");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(this.text);
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                tagged += word + "/" + pos + " ";
            }
        }

        return tagged;
    }
}