package com.jmorenov.tweetsccore.ner;

/**
 * NERELement class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class NERELement {
    private String originalElement;
    private String nerDetected;

    /**
     * Constructor of the class
     * @param originalElement
     * @param nerDetected
     */
    public NERELement(String originalElement, String nerDetected) {
        this.originalElement = originalElement;
        this.nerDetected = nerDetected;
    }

    /**
     * Method to get the original element.
     * @return String with the original element
     */
    public String getOriginalElement() {
        return originalElement;
    }

    /**
     * Method to get the ner detected.
     * @return String with the ner detected
     */
    public String getNerDetected() {
        return nerDetected;
    }
}
