package com.jmorenov.tweetsccore.evaluation;

import com.jmorenov.tweetsccore.method.DictionaryMethod;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class TweetNormEvaluatorTest {
    @Test
    public void tweetNormEvaluationOf3TweetsWithDictionaryMethodShouldReturnAResult() throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "evaluation");
        String workingDirectory = resourceDirectory.toAbsolutePath().toString();

        TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator("tweet-norm-dev3_annotated.txt", true);
        tweetNormEvaluator.setWorkingDirectory(workingDirectory);
        tweetNormEvaluator.setTweetsFile("tweet-norm-dev3.txt");
        tweetNormEvaluator.setResultFile("results-dev3.txt");

        TweetNormEvaluationResult evaluationResult = tweetNormEvaluator.evalutate(new DictionaryMethod());

        assertEquals("failure - the result is incorrect", 0, evaluationResult.getErrors());
        assertEquals("failure - the result is incorrect", 0, evaluationResult.getNegatives());
        assertEquals("failure - the result is incorrect", 2, evaluationResult.getPositives());
        assertEquals("failure - the result is incorrect", (float) 100.0, evaluationResult.getAccurancy());
    }

    @Test
    public void tweetNormEvaluationOf10TweetsWithDictionaryShouldReturnAResult() throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "evaluation");
        String workingDirectory = resourceDirectory.toAbsolutePath().toString();

        TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator("tweet-norm-dev10_annotated.txt", true);
        tweetNormEvaluator.setWorkingDirectory(workingDirectory);
        tweetNormEvaluator.setTweetsFile("tweet-norm-dev10.txt");
        tweetNormEvaluator.setResultFile("results-dev10.txt");

        TweetNormEvaluationResult evaluationResult = tweetNormEvaluator.evalutate(new DictionaryMethod());

        assertEquals("failure - the result is incorrect", 12, evaluationResult.getErrors());
        assertEquals("failure - the result is incorrect", 10, evaluationResult.getNegatives());
        assertEquals("failure - the result is incorrect", 5, evaluationResult.getPositives());
        assertEquals("failure - the result is incorrect", (float) 26.31579, evaluationResult.getAccurancy());
    }

    @Test
    public void tweetNormEvaluationOf3TweetsWithDictionaryAnalysisMethodShouldReturnAResult() throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "evaluation");
        String workingDirectory = resourceDirectory.toAbsolutePath().toString();

        TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator("tweet-norm-dev3_annotated.txt", true);
        tweetNormEvaluator.setWorkingDirectory(workingDirectory);
        tweetNormEvaluator.setTweetsFile("tweet-norm-dev3.txt");
        tweetNormEvaluator.setResultFile("results-dev3.txt");

        TweetNormEvaluationResult evaluationResult = tweetNormEvaluator.evalutate(new DictionaryMethod());

        assertEquals("failure - the result is incorrect", 0, evaluationResult.getErrors());
        assertEquals("failure - the result is incorrect", 0, evaluationResult.getNegatives());
        assertEquals("failure - the result is incorrect", 2, evaluationResult.getPositives());
        assertEquals("failure - the result is incorrect", (float) 100.0, evaluationResult.getAccurancy());
    }
}