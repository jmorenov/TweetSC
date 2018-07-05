package com.jmorenov.tweetsccore.twitter;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;
import com.jmorenov.tweetsccore.method.DictionaryMethod;
import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TweetCorrectedTest {
    @Test
    public void tweetCorrectedShouldComputeTheCorrecText() {
        TweetCorrected tweetCorrected = new TweetCorrected("ola mai nomve s javier y soiii batman.");
        List<OOV> oovs = new ArrayList<>();

        OOV oov = new OOV("ola", 0, 3);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("Hola");
        oovs.add(oov);

        oov = new OOV("mai", 4, 7);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("mi");
        oovs.add(oov);

        oov = new OOV("nomve", 8, 13);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("nombre");
        oovs.add(oov);

        oov = new OOV("s", 14, 15);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("es");
        oovs.add(oov);

        oov = new OOV("javier", 16, 22);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("Javier");
        oovs.add(oov);

        oov = new OOV("soiii", 25, 30);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("soy");
        oovs.add(oov);

        oov = new OOV("batman", 31, 37);
        oov.setAnnotation(Annotation.Variation);
        oov.setCorrection("Batman");
        oovs.add(oov);

        tweetCorrected.setOOVWords(oovs);

        assertEquals("failure - the correction is incorrect", "Hola mi nombre es Javier y soy Batman.", tweetCorrected.getCorrectedText());
    }
}