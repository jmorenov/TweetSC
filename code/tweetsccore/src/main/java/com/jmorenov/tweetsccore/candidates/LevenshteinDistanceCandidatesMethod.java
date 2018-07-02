package com.jmorenov.tweetsccore.candidates;

import com.github.liblevenshtein.collection.dictionary.SortedDawg;
import com.github.liblevenshtein.serialization.PlainTextSerializer;
import com.github.liblevenshtein.serialization.Serializer;
import com.github.liblevenshtein.transducer.Algorithm;
import com.github.liblevenshtein.transducer.ITransducer;
import com.github.liblevenshtein.transducer.factory.TransducerBuilder;
import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * LevenshteinDistanceCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class LevenshteinDistanceCandidatesMethod extends CandidatesMethod {
    private  ITransducer<com.github.liblevenshtein.transducer.Candidate> transducer;
    private SortedDawg dictionary;

    /**
     * Constructor of the class.
     */
    public LevenshteinDistanceCandidatesMethod() throws Exception {
        InputStream stream = File.getStreamFromResources("aspellNormalized.dict");
        Serializer serializer = new PlainTextSerializer(false);
        dictionary = serializer.deserialize(SortedDawg.class, stream);

        transducer = new TransducerBuilder()
                .dictionary(dictionary)
                .algorithm(Algorithm.TRANSPOSITION)
                .defaultMaxDistance(2)
                .includeDistance(true)
                .build();
    }

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        List<Candidate> candidates = new ArrayList<>();

        for (com.github.liblevenshtein.transducer.Candidate candidate : transducer.transduce(oov.getToken())) {
            String term = candidate.term();

            candidates.add(new Candidate(term, this.toString()));
        }

        return candidates;
    }

    /**
     * Method to obtain the method description.
     * @return String
     */
    @Override
    public String toString() {
        return "LevenshteinDistance";
    }
}
