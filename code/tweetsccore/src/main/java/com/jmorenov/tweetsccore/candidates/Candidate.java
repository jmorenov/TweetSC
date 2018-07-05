package com.jmorenov.tweetsccore.candidates;

/**
 * Candidate class that define the candidate element.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class Candidate implements Comparable<Candidate> {
    private String candidate;
    private CandidatesMethodType generatedBy;
    private double score;

    /**
     * Constructor of the class.
     * @param candidate String with the candidate
     * @param generatedBy String with the method that generated the candidate
     */
    public Candidate(String candidate, CandidatesMethodType generatedBy) {
        this.candidate = candidate;
        this.generatedBy = generatedBy;
    }

    /**
     * Method to get the candidate.
     * @return String with the candidate
     */
    public String getCandidate() {
        return candidate;
    }

    /**
     * Method to get the method that generated the candidate.
     * @return CandidatesMethodType with the generated by
     */
    public CandidatesMethodType getGeneratedBy() {
        return generatedBy;
    }

    /**
     * Method to get the score of the candidate.
     * @return double
     */
    public double getScore() {
        return score;
    }

    /**
     * Method to set the score of the candidate.
     * @param score double
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Method to compare two candidates.
     * @param candidate Candidate
     * @return int
     */
    @Override
    public int compareTo(Candidate candidate) {
        return Double.compare(candidate.getScore(), this.getScore());
    }
}