package com.jmorenov.tweetsccore.extra;

/**
 * Out-Of-Vocabulary class with the structure of a OOV word.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class OOV {
    private String token;
    private Anotation anotation = Anotation.NoEs;
    private String correction;
    private int startPosition;
    private int endPosition;

    /**
     * Constructor of the class.
     * @param token String with the word or token of the OOV.
     * @param startPosition int with the initial position of the OOV in the original text.
     * @param endPosition int with the final position of the OOV in the original text.
     */
    public OOV(String token, int startPosition, int endPosition) {
        this.token = token;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public void setAnotation(Anotation anotation) {
        this.anotation = anotation;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getToken() {
        return token;
    }

    public Anotation getAnotation() {
        return anotation;
    }

    public String getCorrection() {
        return correction;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }
}
