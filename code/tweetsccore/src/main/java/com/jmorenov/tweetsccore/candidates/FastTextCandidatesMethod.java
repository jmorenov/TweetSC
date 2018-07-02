package com.jmorenov.tweetsccore.candidates;

import com.jmorenov.tweetsccore.extra.OOV;

import java.util.List;

/**
 * FastTextCandidatesMethod class that define a method to generate candidates.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class FastTextCandidatesMethod extends CandidatesMethod {
    /**
     * Constructor of the class.
     */
    public FastTextCandidatesMethod() {

    }

    /**
     * Method to generate condidates from an OOV.
     * @param oov OOV
     * @return List of Candidates
     */
    @Override
    public List<Candidate> generateCandidates(OOV oov) {
        return null;
    }

    /**
     * Method to obtain the method description.
     * @return String
     */
    @Override
    public String toString() {
        return "FastText";
    }

    /**
     * Method to calculate the cosine similarity between two vector.
     * @param vectorA double[]
     * @param vectorB double[]
     * @return double with the result
     */
    private double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
