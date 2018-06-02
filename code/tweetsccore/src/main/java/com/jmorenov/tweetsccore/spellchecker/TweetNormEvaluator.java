package com.jmorenov.tweetsccore.spellchecker;

import com.jmorenov.tweetsccore.twitter.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TweetNormEvaluator {
    private boolean _verbose;
    private String _annotatedFile;
    private String _idsFile;
    private String _evaluatorScriptFile = "tweet-norm-eval.py";
    private String _workingDirectory;
    private String _tweetsFile;
    private String _resultFile;
    private ArrayList<Tweet> _tweetList;

    public TweetNormEvaluator() {
        this._verbose = false;
    }

    public TweetNormEvaluator(String anotatedFile, boolean verbose) {
        setAnnotatedFile(anotatedFile);
        this._verbose = verbose;
    }

    public TweetNormEvaluator(String anotatedFile) {
        this(anotatedFile, false);
    }

    public void setIdsFile(String idsFile) {
        this._idsFile = idsFile;
    }

    public void setTweetsFile(String tweetsFile) {
        this._tweetsFile = tweetsFile;
    }

    public void setAnnotatedFile(String annotatedFile) {
        this._annotatedFile = annotatedFile;
    }

    public void setEvaluatorScriptFile(String evaluatorScriptFile) {
        this._evaluatorScriptFile = evaluatorScriptFile;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this._workingDirectory = workingDirectory + "/";
    }

    public void setResultFile(String resultFile) {
        this._resultFile = resultFile;
    }

    public String evalutate(Method method) throws IOException {
        if (_tweetList == null) {
            if (_tweetsFile == null) {
                downloadTweets();
            } else {
                readTweets();
            }
        }

        List<String> spellCheckerResultLines = new ArrayList<String>();
        SpellChecker spellChecker = new SpellChecker(method);
        String tweetCorrected;

        for (Tweet tweet : _tweetList) {
            tweetCorrected = spellChecker.correctTextForTweetNorm(tweet.getText());

            if (tweetCorrected.equals("")) {
                spellCheckerResultLines.add(tweet.getId() + "\t");
            } else {
                spellCheckerResultLines.add(tweet.getId() + "\t" + tweetCorrected);
            }
        }

        Path resultFile = Paths.get(_workingDirectory + _resultFile);
        Files.write(resultFile, spellCheckerResultLines, Charset.forName("UTF-8"));

        return executeEvaluatorScript();
    }

    private void downloadTweets() {
        //TODO
    }

    private void readTweets() throws IOException {
        _tweetList = new ArrayList<Tweet>();
        Path tweetsFilePath = Paths.get(_workingDirectory + _tweetsFile);
        Stream<String> tweetsFileLines = Files.lines(tweetsFilePath);

        tweetsFileLines.forEach(this::readTweet);
    }

    private void readTweet(String tweetLine) {
        String[] elementOfTweet = tweetLine.split("\t");
        Tweet tweet = new Tweet(elementOfTweet[0], elementOfTweet[1], elementOfTweet[2], elementOfTweet[3]);

        _tweetList.add(tweet);
    }

    private String executeEvaluatorScript() throws IOException {
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

        return error + "\n" + output;
    }
}