package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ApplyRules class to apply preprocess rules.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class ApplyRules {
    private static String rulesFileName = "preprocess/rules.txt";
    private Rules rules;

    /**
     * Constructor of the class.
     */
    public ApplyRules() {
        try {
            rules = new Rules(rulesFileName);
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
                    if (!Dictionaries.getInstance().getSpanishDictionary().contains(token.getText())
                            && !Dictionaries.getInstance().getEntitiesDictionary().contains(token.getText())
                            && token.getText().matches(rule.getRegex())) {
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
     * Method to apply the rules with parallelism to a List of Token.
     * @param tokens List of tokens
     * @return ApplyRulesResult
     */
    public ApplyRulesResult applyParallel(List<Token> tokens) {
        ApplyRulesResult applyRulesResult = new ApplyRulesResult();

        if (rules != null) {
            tokens.parallelStream().forEach((token) -> {
                final AtomicReference<Boolean> oovAdded = new AtomicReference();
                oovAdded.set(false);

                rules.getRules().parallelStream().forEach((rule) -> {
                    if (!Dictionaries.getInstance().getSpanishDictionary().contains(token.getText())
                            && !Dictionaries.getInstance().getEntitiesDictionary().contains(token.getText())
                            && token.getText().matches(rule.getRegex())) {
                        OOV oov = new OOV(token);

                        oov.setCorrection(rule.getResult());
                        oov.setAnnotation(Annotation.Variation);
                        applyRulesResult.addOOV(oov);

                        oovAdded.set(true);
                    }
                });

                if (!oovAdded.get()) {
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
            });
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

        if (!Dictionaries.getInstance().getEntitiesDictionary().contains(token)
                && Dictionaries.getInstance().getEntitiesDictionary().contains(capitalizedWord)) {
            return capitalizedWord;
        }
        return "";
    }
}