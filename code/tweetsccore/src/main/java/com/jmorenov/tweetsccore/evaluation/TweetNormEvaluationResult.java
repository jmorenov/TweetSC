package com.jmorenov.tweetsccore.evaluation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetNormEvaluationResult {
    private String resultText;
    private float accurancy;
    private int errors;
    private int positives;
    private int negatives;

    public TweetNormEvaluationResult(String resultText) {
        this.resultText = resultText;

        setErrorsValue();
        setPositivesValue();
        setNegativesValue();
        setAccurancyValue();
    }

    private String obtainValueFromText(String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(this.resultText);
        String value = "0";

        while (m.find()) {
            value = resultText.substring(m.start(1), m.end(1));
        }

        return value;
    }

    private void setErrorsValue() {
        String pattern = "ERR: ([0-9]+) \n";

        this.errors = Integer.parseInt(obtainValueFromText(pattern));
    }

    private void setPositivesValue() {
        String pattern = "POS: ([0-9]+) \n";

        this.positives = Integer.parseInt(obtainValueFromText(pattern));
    }

    private void setAccurancyValue() {
        String pattern = "ACCUR: ([0-9]+.[0-9]+) \n";

        this.accurancy = Float.parseFloat(obtainValueFromText(pattern));
    }

    private void setNegativesValue() {
        String pattern = "NEG: ([0-9]+) \n";

        this.negatives = Integer.parseInt(obtainValueFromText(pattern));
    }

    public String getResultText() {
        return this.resultText;
    }

    public float getAccurancy() {
        return this.accurancy;
    }

    public int getErrors() {
        return this.errors;
    }

    public int getPositives() {
        return this.positives;
    }

    public int getNegatives() {
        return this.negatives;
    }
}
