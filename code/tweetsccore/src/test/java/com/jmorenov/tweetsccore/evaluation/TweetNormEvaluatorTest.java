package com.jmorenov.tweetsccore.evaluation;

import com.jmorenov.tweetsccore.method.DictionaryMethod;
import com.jmorenov.tweetsccore.method.TweetSCMethod;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class TweetNormEvaluatorTest {
    @Test
    public void tweetNormEvaluationOf3TweetsWithTweetSCMethodShouldReturnAResult() throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "evaluation");
        String workingDirectory = resourceDirectory.toAbsolutePath().toString();

        TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator(
                "tweet-norm-dev3_annotated.txt", true, 1);
        tweetNormEvaluator.setWorkingDirectory(workingDirectory);
        tweetNormEvaluator.setTweetsFile("tweet-norm-dev3.txt");
        tweetNormEvaluator.setResultFile("results-dev3.txt");

        TweetNormEvaluationResult evaluationResult = tweetNormEvaluator.evalutate(new TweetSCMethod());

        assertEquals("failure - the result is incorrect", 0, evaluationResult.getErrors());
        assertEquals("failure - the result is incorrect", 0, evaluationResult.getNegatives());
        assertEquals("failure - the result is incorrect", 2, evaluationResult.getPositives());
        assertEquals("failure - the result is incorrect", (float) 100.0, evaluationResult.getAccurancy());
    }

    @Test
    public void tweetNormEvaluationOf10TweetsWithTweetSCMethodShouldReturnAResult() throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources", "evaluation");
        String workingDirectory = resourceDirectory.toAbsolutePath().toString();

        TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator(
                "tweet-norm-dev10_annotated.txt", true, 1);
        tweetNormEvaluator.setWorkingDirectory(workingDirectory);
        tweetNormEvaluator.setTweetsFile("tweet-norm-dev10.txt");
        tweetNormEvaluator.setResultFile("results-dev10.txt");

        TweetNormEvaluationResult evaluationResult = tweetNormEvaluator.evalutate(new TweetSCMethod());

        assertEquals("failure - the result is incorrect", (float) 63.157894, evaluationResult.getAccurancy());
    }
}