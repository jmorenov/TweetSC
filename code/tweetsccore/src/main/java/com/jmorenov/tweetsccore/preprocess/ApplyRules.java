package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;
import com.jmorenov.tweetsccore.extra.Token;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static String[] entityFiles = {"entities.txt", "english.txt"};
    private List<String> entitiesWords;

    /**
     * Constructor of the class.
     */
    public ApplyRules() {
        try {
            rules = new Rules(rulesFileName);

            readEntitiesWords();
        } catch (IOException ex) {
            rules = null;
        }
    }

    /**
     * Method to apply the rules to a text.
     * @param text String with the text
     * @return List of OOVs
     */
    public List<OOV> apply(String text) {
        List<OOV> oovs = new ArrayList<>();

        if (rules != null) {
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
        }

        return oovs;
    }

    /**
     * Method to apply the rules to a List of Token.
     * @param tokens List of tokens
     * @return ApplyRulesResult
     */
    public ApplyRulesResult apply(List<Token> tokens) {
        ApplyRulesResult applyRulesResult = new ApplyRulesResult();

        if (rules != null) {
            for (Token token : tokens) {
                boolean oovAdded = false;

                for (Rule rule : rules.getRules()) {
                    if (token.getText().matches(rule.getRegex())) {
                        OOV oov = new OOV(token);

                        oov.setCorrection(rule.getResult());
                        oov.setAnnotation(Annotation.Variation);
                        applyRulesResult.addOOV(oov);

                        oovAdded = true;
                    }
                }

                if (!oovAdded) {
                    String entityVariation = getEntityVariation(token.getText());

                    if (!entityVariation.equals("")) {
                        OOV oov = new OOV(token);

                        oov.setCorrection(entityVariation);
                        oov.setAnnotation(Annotation.Variation);
                        applyRulesResult.addOOV(oov);
                    } else {
                        applyRulesResult.addToken(token);
                    }
                }
            }
        }

        return applyRulesResult;
    }

    /**
     * Method to get en entity variation.
     * @param token String
     * @return String
     */
    private String getEntityVariation(String token) {
        String capitalizedWord = StringUtils.capitalize(token);

        if (entitiesWords.contains(capitalizedWord)) {
            return capitalizedWord;
        }
        return "";
    }

    /**
     * Method to read the entities from the files.
     * @throws IOException
     */
    private void readEntitiesWords() throws IOException {
        this.entitiesWords = new ArrayList<>();

        for (String file : entityFiles) {
            this.entitiesWords.addAll(
                    Arrays.asList(File.readToStringArray(Paths.get("src", "main", "resources", file)
                            .toAbsolutePath() + "")));
        }
    }
}