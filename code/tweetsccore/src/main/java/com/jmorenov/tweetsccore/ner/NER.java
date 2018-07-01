package com.jmorenov.tweetsccore.ner;

import java.util.List;

/**
 * POSTagging abstract class.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public abstract class NER {
    /**
     * Method to get a list with the NER Elements detected.
     * @return List with the NER Elements.
     */
    public abstract List<NERELement> getNERElements(String text);
}