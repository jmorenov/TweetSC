package com.jmorenov.tweetsccore.evaluation;

import com.jmorenov.tweetsccore.extra.File;
import com.jmorenov.tweetsccore.method.Method;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.twitter.Tweet;
import com.jmorenov.tweetsccore.twitter.TweetCorrected;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * TweetNormEvaluator class to evaluate methods of spell checker
 * with Tweet Norm 2013 files to test.
 *
 * @author <a href="mailto:jmorenov28@gmail.com">Javier Moreno</a>
 */
public class TweetNormEvaluator {
    private boolean _verbose;
    private String _annotatedFile;
    private String _idsFile;
    private String _evaluatorScriptFile = "tweet-norm-eval.py";
    private String _workingDirectory;
    private String _tweetsFile;
    private String _resultFile;
    private ArrayList<Tweet> _tweetList;

    /**
     * Default constructor of the class.
     */
    public TweetNormEvaluator() {
        this._verbose = false;
    }

    /**
     * Constructor of the class with parameters.
     *
     * @param annotatedFile String parameter with the name of the file with the annotated tweets.
     * @param verbose Boolean parameter to define the verbose control.
     */
    public TweetNormEvaluator(String annotatedFile, boolean verbose) {
        setAnnotatedFile(annotatedFile);
        this._verbose = verbose;
    }

    /**
     * Constructor of the class with parameter.
     *
     * @param annotatedFile String parameter with the name of the file with the annotated tweets.
     */
    public TweetNormEvaluator(String annotatedFile) {
        this(annotatedFile, false);
    }

    /**
     * Method to define the file with the ids of the tweets.
     *
     * @param idsFile String parameter with the name of the file with the ids of the tweets.
     */
    public void setIdsFile(String idsFile) {
        this._idsFile = idsFile;
    }

    /**
     * Method to define the file with the tweets.
     *
     * @param tweetsFile String parameter with the name of the file with the tweets.
     */
    public void setTweetsFile(String tweetsFile) {
        this._tweetsFile = tweetsFile;
    }

    /**
     * Method to define the file with the annotated tweets.
     *
     * @param annotatedFile String parameter with the name of the file with the annotated tweets.
     */
    public void setAnnotatedFile(String annotatedFile) {
        this._annotatedFile = annotatedFile;
    }

    /**
     * Method to define the file of the evaluator script.
     *
     * @param evaluatorScriptFile String parameter with the name of the evaluator script.
     */
    public void setEvaluatorScriptFile(String evaluatorScriptFile) {
        this._evaluatorScriptFile = evaluatorScriptFile;
    }

    /**
     * Method to define the working directory.
     *
     * @param workingDirectory String parameter with the working directory.
     */
    public void setWorkingDirectory(String workingDirectory) {
        this._workingDirectory = workingDirectory + "/";
    }

    /**
     * Method to define the result file.
     *
     * @param resultFile String parameter with the result file.
     */
    public void setResultFile(String resultFile) {
        this._resultFile = resultFile;
    }

    /**
     * Method to evaluate the defined file with a method of spell checker.
     *
     * @param method {@link Method} parameter with the method to use.
     * @return String with the output of the evaluation.
     * @throws IOException when the file not found.
     * @see Method
     */
    public TwetNormEvaluationResult evalutate(Method method) throws IOException {
        if (_tweetList == null) {
            if (_tweetsFile == null) {
                downloadTweets();
            } else {
                readTweets();
            }
        }

        List<String> spellCheckerResultLines = new ArrayList<String>();
        SpellChecker spellChecker = new SpellChecker(method);

        for (Tweet tweet : _tweetList) {
            TweetCorrected tweetCorrected = spellChecker.correctTweet(tweet);

            spellCheckerResultLines.add(tweetCorrected.toTweetNormString());
        }

        Path resultFile = Paths.get(_workingDirectory + _resultFile);
        Files.write(resultFile, spellCheckerResultLines, Charset.forName("UTF-8"));

        return executeEvaluatorScript();
    }

    /**
     * Method to download the tweets from the ids file.
     */
    private void downloadTweets() {
        //TODO
    }

    /**
     * Method to read the tweets from the tweets file.
     * @throws IOException when the file not found.
     */
    private void readTweets() throws IOException {
        _tweetList = new ArrayList<Tweet>();
        String[] tweets = File.readToStringArray(_workingDirectory + _tweetsFile);

        for(String tweetLine : tweets) {
            String[] elementOfTweet = tweetLine.split("\t");
            Tweet tweet = new Tweet(elementOfTweet[0], elementOfTweet[1], elementOfTweet[2], elementOfTweet[3]);

            _tweetList.add(tweet);
        }
    }

    /**
     * Method to execute the python script to evaluate.
     * @throws IOException when the file not found.
     * @return String with the output of the execution.
     */
    private TwetNormEvaluationResult executeEvaluatorScript() throws IOException {
        String command = "python " + _workingDirectory + _evaluatorScriptFile + " " + _workingDirectory + _annotatedFile + " " + _workingDirectory + _resultFile;
        Process processScript = Runtime.getRuntime().exec(command);
        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(processScript.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(processScript.getErrorStream()));
        String output = "", error = "", outputLine;

        while ((outputLine = stdError.readLine()) != null) {
            error = error.concat(outputLine + "\n");
        }

        while ((outputLine = stdOutput.readLine()) != null) {
            output = output.concat(outputLine + "\n");
        }

        return new TwetNormEvaluationResult(error + "\n" + output);
    }
}