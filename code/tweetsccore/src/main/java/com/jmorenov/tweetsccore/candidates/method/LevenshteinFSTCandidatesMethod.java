package com.jmorenov.tweetsccore.candidates.method;

import com.github.liblevenshtein.collection.dictionary.SortedDawg;
import com.github.liblevenshtein.serialization.PlainTextSerializer;
import com.github.liblevenshtein.serialization.Serializer;
import com.github.liblevenshtein.transducer.Algorithm;
import com.github.liblevenshtein.transducer.ITransducer;
import com.github.liblevenshtein.transducer.factory.TransducerBuilder;
import com.jmorenov.tweetsccore.candidates.Candidate;
import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * LevenshteinFSTCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class LevenshteinFSTCandidatesMethod extends CandidatesMethod {
    private  ITransducer<com.github.liblevenshtein.transducer.Candidate> transducer;

    /**
     * Constructor of the class.
     */
    public LevenshteinFSTCandidatesMethod() {
        try {
            InputStream stream = File.getStreamFromResources("aspellNormalized.dict");
            Serializer serializer = new PlainTextSerializer(false);
            SortedDawg dictionary = serializer.deserialize(SortedDawg.class, stream);

            transducer = new TransducerBuilder()
                    .dictionary(dictionary)
                    .algorithm(Algorithm.TRANSPOSITION)
                    .defaultMaxDistance(2)
                    .includeDistance(true)
                    .build();
        } catch (Exception ex) {
            transducer = null;
        }
    }

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        List<Candidate> candidates = new ArrayList<>();

        if (transducer != null) {
            for (com.github.liblevenshtein.transducer.Candidate candidate : transducer.transduce(oov.getToken())) {
                String term = candidate.term();

                candidates.add(new Candidate(term, this.getMethod()));
            }
        }

        return candidates;
    }

    /**
     * Method to obtain the method description.
     * @return CandidatesMethodType
     */
    @Override
    public CandidatesMethodType getMethod() {
        return CandidatesMethodType.LevenshteinFST;
    }
}
