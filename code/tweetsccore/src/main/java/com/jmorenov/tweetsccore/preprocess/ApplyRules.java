package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ApplyRules class to apply preprocess rules.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class ApplyRules {
    private static String rulesFileName = Paths.get("src","main","resources", "preprocess", "rules.txt").toAbsolutePath() + "/";
    private Rules rules;

    /**
     * Constructor of the class.
     * @throws IOException
     */
    public ApplyRules() throws IOException {
        rules = new Rules(rulesFileName);
    }

    /**
     * Method to apply the rules to a text.
     * @param text String with the text
     * @return List of OOVs
     */
    public List<OOV> apply(String text) {
        List<OOV> oovs = new ArrayList<>();

        for (Rule rule : rules.getRules()) {
            Pattern p = Pattern.compile(rule.getRegex());
            Matcher m = p.matcher(text);

            while (m.find()) {
                String word = text.substring(m.start(1), m.end(1));
                OOV oov = new OOV(word, m.start(1), m.end(1));

                oov.setCorrection(rule.getResult());
                oov.setAnnotation(Annotation.Variation);
                oovs.add(oov);
            }
        }

        return oovs;
    }
}