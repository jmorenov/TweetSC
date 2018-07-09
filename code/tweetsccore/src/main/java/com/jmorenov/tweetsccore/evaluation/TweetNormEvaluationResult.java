package com.jmorenov.tweetsccore.evaluation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TweetNormEvaluationResult class with the structure of
 *      the result.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetNormEvaluationResult {
    private String resultText;
    private float accurancy;
    private int errors;
    private int positives;
    private int negatives;
    private double executionTime;
    /**
     * Constructor of the class.
     * @param resultText String
     * @param time long
     */
    public TweetNormEvaluationResult(String resultText, long time) {
        this.resultText = resultText;
        this.executionTime = time;

        setErrorsValue();
        setPositivesValue();
        setNegativesValue();
        setAccurancyValue();
    }

    /**
     * Constructor of the class.
     * @param pattern String
     * @return String
     */
    private String obtainValueFromText(String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(this.resultText);
        String value = "0";

        while (m.find()) {
            value = resultText.substring(m.start(1), m.end(1));
        }

        return value;
    }

    /**
     * Method to set the error value from the text.
     */
    private void setErrorsValue() {
        String pattern = "ERR: ([0-9]+) \n";

        this.errors = Integer.parseInt(obtainValueFromText(pattern));
    }

    /**
     * Method to set the positives value from the text.
     */
    private void setPositivesValue() {
        String pattern = "POS: ([0-9]+) \n";

        this.positives = Integer.parseInt(obtainValueFromText(pattern));
    }

    /**
     * Method to set the accurancy value from the text.
     */
    private void setAccurancyValue() {
        String pattern = "ACCUR: ([0-9]+.[0-9]+) \n";

        this.accurancy = Float.parseFloat(obtainValueFromText(pattern));
    }

    /**
     * Method to set the negatives value from the text.
     */
    private void setNegativesValue() {
        String pattern = "NEG: ([0-9]+) \n";

        this.negatives = Integer.parseInt(obtainValueFromText(pattern));
    }

    /**
     * Method to set the execution time.
     * @param time long
     */
    public void setExecutionTime(double time) {
        this.executionTime = time;
    }

    /**
     * Method to set the accurancy.
     * @param accurancy float
     */
    public void setAccurancy(float accurancy) {
        this.accurancy = accurancy;
    }

    /**
     * Method to get the result text.
     * @return String
     */
    public String getResultText() {
        return this.resultText;
    }

    /**
     * Method to get the accurancy.
     * @return float
     */
    public float getAccurancy() {
        return this.accurancy;
    }

    /**
     * Method to get the errors.
     * @return int
     */
    public int getErrors() {
        return this.errors;
    }

    /**
     * Method to get the positives.
     * @return int
     */
    public int getPositives() {
        return this.positives;
    }

    /**
     * Method to get the negatives.
     * @return int
     */
    public int getNegatives() {
        return this.negatives;
    }

    /**
     * Method to get the execution time.
     * @return long
     */
    public double getExecutionTime() {
        return executionTime;
    }

    /**
     * Method to get the string with all the results.
     * @return String
     */
    @Override
    public String toString() {
        return "Tweet Norm 2013 evaluation result:"
                + "\nPositives: " + getPositives()
                + "\nNegatives: " + getNegatives()
                + "\nErrors: " + getErrors()
                + "\nAccurancy: " + getAccurancy()
                + "\nExecution time: " + getExecutionTime();
    }
}