package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.extra.OOV;
import info.debatty.java.stringsimilarity.JaroWinkler;
import opennlp.tools.languagemodel.NGramLanguageModel;
import opennlp.tools.util.StringList;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CandidatesRanking class to ranking the candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class CandidatesRanking {
    private static final String[] files = {"aspellNormalized.dict", "nombres_propios.txt", "entities.txt", "english.txt"};

    private NGramLanguageModel languageModel;
    private JaroWinkler jaroWinkler;

    /**
     * Constructor of the class.
     */
    public CandidatesRanking() {
        languageModel = createLanguageModel(3);
        jaroWinkler = new JaroWinkler();
    }

    /**
     * Method to ranking the candidates.
     * @param text Original text
     * @param oov OOV
     * @return Order List of Candidate
     */
    public List<Candidate> rank(String text, OOV oov) {
        List<Candidate> candidates = oov.getCandidates();

        for (Candidate candidate : candidates) {
            StringBuilder textWithCandidate = new StringBuilder(text);

            textWithCandidate.replace(oov.getStartPosition(), oov.getEndPosition(), candidate.getCandidate());

            double score1 = languageModel.calculateProbability(new StringList(textWithCandidate.toString()));
            double score2 = jaroWinkler.similarity(oov.getToken(), candidate.getCandidate());

            candidate.setScore(score1 + score2);
        }

        Collections.sort(candidates);

        return candidates;
    }

    /**
     * Method to create a language model.
     * @param n int the size of the N-Gram
     * @return NGramLanguageModel
     */
    private NGramLanguageModel createLanguageModel(int n) {
        NGramLanguageModel nGramLanguageModel = new NGramLanguageModel(n);

        try {
            for (String file : files) {
                String[] words = File.readToStringArray(Paths.get("src", "main", "resources", file).toAbsolutePath() + "");

                for (String word : words) {
                    nGramLanguageModel.add(new StringList(word));
                }
            }
        } catch (Exception ex) {
            nGramLanguageModel = null;
        }

        return nGramLanguageModel;
    }
}
