package com.jmorenov.tweetsccore.preprocess;

/**
 * Rule class that define a rule element.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Rule {
    private String regex;
    private String result;

    /**
     * Constructor of the class.
     * @param regex String
     * @param result String
     */
    public Rule(String regex, String result) {
        this.regex = regex;
        this.result = result;
    }

    /**
     * Method to get the regex of the rule.
     * @return String
     */
    public String getRegex() {
        return regex;
    }

    /**
     * Method to get the result of a rule.
     * @return String
     */
    public String getResult() {
        return result;
    }
}
