package com.jmorenov.tweetsccore.analyzer;

/**
 * AnalysisElement class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class AnalysisElement {
    private String form;
    private String lemma;
    private String tag;
    private String senses;
    private boolean isMultiWord;

    /**
     * Constructor of the class.
     * @param form
     * @param lemma
     * @param tag
     * @param isMultiWord
     */
    public AnalysisElement(String form, String lemma, String tag, String senses, boolean isMultiWord) {
        this.form = form;
        this.lemma = lemma;
        this.tag = tag;
        this.senses = senses;
        this.isMultiWord = isMultiWord;
    }

    /**
     * Method to get the form.
     * @return String with the form
     */
    public String getForm() {
        return form;
    }

    /**
     * Method to get the lemma.
     * @return String with the lemma
     */
    public String getLemma() {
        return lemma;
    }

    /**
     * Method to get the tag.
     * @return String with the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Method to get the senses.
     * @return String with the senses
     */
    public String getSenses() {
        return senses;
    }

    /**
     * Method to get if the element is multi word.
     * @return Boolean with the test
     */
    public boolean isMultiWord() {
        return isMultiWord;
    }

    /**
     * Method to get the class as String.
     * @return String
     */
    @Override
    public String toString() {
        String multiWordString = this.isMultiWord ? "Multiword" : "No multiword";
        String sensesString = this.senses.equals("") ? "No senses" : this.senses;

        return this.form + " : " + this.lemma + " : " + this.tag + " : " + sensesString + " : " + multiWordString;
    }
}
