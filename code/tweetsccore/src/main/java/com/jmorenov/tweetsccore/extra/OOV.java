package com.jmorenov.tweetsccore.extra;

import com.jmorenov.tweetsccore.candidates.Candidate;

import java.util.ArrayList;
import java.util.List;

/**
 * Out-Of-Vocabulary class with the structure of a OOV word.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class OOV {
    private Annotation annotation = Annotation.Unknown;
    private String correction;
    private Token token;
    private List<Candidate> candidates;

    /**
     * Constructor of the class.
     * @param token String with the word or token of the OOV.
     * @param startPosition int with the initial position of the OOV in the original text.
     * @param endPosition int with the final position of the OOV in the original text.
     */
    public OOV(String token, int startPosition, int endPosition) {
        this.token = new Token(token, startPosition, endPosition);
    }

    /**
     * Constructor of the class.
     * @param token Token
     */
    public OOV(Token token) {
        this.token = token;
    }

    /**
     * Method to set the annotation
     * @param annotation Annotation
     */
    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    /**
     * Method to set the correction.
     * @param correction String
     */
    public void setCorrection(String correction) {
        this.correction = correction;
    }

    /**
     * Method to get the token.
     * @return String
     */
    public String getToken() {
        return token.getText();
    }

    /**
     * Method to get the annotation.
     * @return Annotation
     */
    public Annotation getAnnotation() {
        return annotation;
    }

    /**
     * Method to get the correction.
      * @return String
     */
    public String getCorrection() {
        return correction;
    }

    /**
     * Method to get the start position into the original text.
     * @return int
     */
    public int getStartPosition() {
        return this.token.getStartPosition();
    }

    /**
     * Method to get the end position into the original text.
     * @return int
     */
    public int getEndPosition() {
        return this.token.getEndPosition();
    }

    /**
     * Method to set the candidates of an OOV.
     * @param candidates List of Candidate
     */
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    /**
     * Method to get the candidates of an OOV.
     * @return List of Candidate
     */
    public List<Candidate> getCandidates() {
        return candidates;
    }
}
