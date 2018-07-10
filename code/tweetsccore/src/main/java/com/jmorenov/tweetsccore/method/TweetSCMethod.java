package com.jmorenov.tweetsccore.method;

import com.jmorenov.tweetsccore.candidates.*;
import com.jmorenov.tweetsccore.candidates.method.*;
import com.jmorenov.tweetsccore.extra.Annotation;
import com.jmorenov.tweetsccore.extra.OOV;
import com.jmorenov.tweetsccore.extra.OOVDetector;
import com.jmorenov.tweetsccore.extra.Token;
import com.jmorenov.tweetsccore.preprocess.ApplyRules;
import com.jmorenov.tweetsccore.preprocess.ApplyRulesResult;
import com.jmorenov.tweetsccore.tokenizer.SimpleTokenizer;
import com.jmorenov.tweetsccore.tokenizer.Tokenizer;
import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;

import java.util.ArrayList;
import java.util.List;

/**
 * TweetSCMethod class that define a method to spell check.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetSCMethod extends Method {
    private Tokenizer tokenizer;
    private ApplyRules applyRules;
    private OOVDetector oovDetector;
    private CandidatesGenerator candidatesGenerator;
    private CandidatesRanking candidatesRanking;

    /**
     * Constructor of the class.
     */
    public TweetSCMethod() {
        long startTime = System.nanoTime();

        tokenizer = new SimpleTokenizer();
        applyRules = new ApplyRules();
        oovDetector = new OOVDetector();
        List<CandidatesMethod> methods = CandidatesMethodCreator.getAllMethodParallel();
        candidatesGenerator = new CandidatesGenerator(methods);
        candidatesRanking = new CandidatesRanking();

        System.out.println("Constructor: " + ((System.nanoTime() - startTime)/ 1000000000.0));
    }

    /**
     * Method to correct a tweet.
     * @param tweet Tweet with the tweet to correct.
     * @return TweetCorrected
     */
    @Override
    public TweetCorrected correctTweet(Tweet tweet) {
        TweetCorrected tweetCorrected = new TweetCorrected(tweet);

        long startTime = System.nanoTime();
        List<Token> tokens = tokenizer.getTokens(tweet.getText());
        System.out.println("Tokenizer: " + ((System.nanoTime() - startTime)/ 1000000000.0));

        startTime = System.nanoTime();
        ApplyRulesResult applyRulesResult = applyRules.applyParallel(tokens);
        System.out.println("Apply Rules: " + ((System.nanoTime() - startTime)/ 1000000000.0));

        tweetCorrected.setOOVWords(applyRulesResult.getOOVList());

        startTime = System.nanoTime();
        List<OOV> oovList = oovDetector.detectOOV(applyRulesResult.getTokens());
        System.out.println("DetectOOV: " + ((System.nanoTime() - startTime)/ 1000000000.0));

        List<OOV> oovs_CorrectNoEs = new ArrayList<>();
        List<OOV> oovs_Unknown = new ArrayList<>();


        for (OOV oov : oovList) {
            if (oov.getAnnotation() == Annotation.Correct
                    || oov.getAnnotation() == Annotation.NoEs) {
                oovs_CorrectNoEs.add(oov);
            } else {
                oovs_Unknown.add(oov);
            }
        }

        oovList = applyRulesResult.getOOVList();
        oovList.addAll(oovs_CorrectNoEs);

        startTime = System.nanoTime();
        oovs_Unknown = candidatesGenerator.generateCandidates(oovs_Unknown);
        System.out.println("Candidates generation: " + ((System.nanoTime() - startTime)/ 1000000000.0));

        startTime = System.nanoTime();
        for (OOV oov : oovs_Unknown) {
            oov.setCandidates(candidatesRanking.rank(tweet.getText(), oov));

            OOV oovCorrected = correctOOV(oov);

            oov.setAnnotation(oovCorrected.getAnnotation());
            oov.setCorrection(oovCorrected.getCorrection());
        }
        System.out.println("Candidates Ranking: " + ((System.nanoTime() - startTime)/ 1000000000.0));

        oovList.addAll(oovs_Unknown);

        tweetCorrected.setOOVWords(oovList);

        return tweetCorrected;
    }

    /**
     * Method to get the description of the method.
     * @return String
     */
    @Override
    public String toString() {
        return "TweetSCMethod";
    }

    /**
     * Method to correct an OOV.
     * @param oov OOV
     * @return OOV
     */
    private OOV correctOOV(OOV oov) {
        if (oov.getCandidates().size() > 0 && oov.getCandidates().get(0).getScore() >= 0.7) {
            oov.setCorrection(oov.getCandidates().get(0).getCandidate());
            oov.setAnnotation(Annotation.Variation);
        } else {
            oov.setAnnotation(Annotation.NoEs);
        }

        return oov;
    }
}
