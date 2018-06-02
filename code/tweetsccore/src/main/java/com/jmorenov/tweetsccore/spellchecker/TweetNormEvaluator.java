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
import java.util.stream.Stream;

public class TweetNormEvaluator {
    private boolean _verbose;
    private String _anotatedFile;
    private String _idsFile;
    private String _tweetsFile;
    private ArrayList<Tweet> _tweetList;

    public TweetNormEvaluator() {
        this._verbose = false;
    }

    public TweetNormEvaluator(String anotatedFile, boolean verbose) {
        setAnotatedFile(anotatedFile);
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

    public void setAnotatedFile(String anotatedFile) {
        this._anotatedFile = anotatedFile;
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

        for (Tweet tweet : _tweetList) {
            spellCheckerResultLines.add(tweet.getId());
            spellCheckerResultLines.add(spellChecker.correctTextForTweetNorm(tweet.getText()));
        }

        Path resultFile = Paths.get("resultFile.txt");
        Files.write(resultFile, spellCheckerResultLines, Charset.forName("UTF-8"));

        return executeEvaluatorScript();
    }

    private void downloadTweets() {
        //TODO
    }

    private void readTweets() throws IOException {
        _tweetList = new ArrayList<Tweet>();
        Path tweetsFilePath = Paths.get(_tweetsFile);
        Stream<String> tweetsFileLines = Files.lines(tweetsFilePath);

        tweetsFileLines.forEach(this::readTweet);
    }

    private void readTweet(String tweetLine) {
        String[] elementOfTweet = tweetLine.split(" ");
        Tweet tweet = new Tweet(elementOfTweet[0], elementOfTweet[1], elementOfTweet[2], elementOfTweet[3]);

        _tweetList.add(tweet);
    }

    private String executeEvaluatorScript() throws IOException {
        Process processScript = Runtime.getRuntime().exec("python ../");
        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(processScript.getInputStream()));
        String output = "", outputLine;

        while ((outputLine = stdOutput.readLine()) != null) {
            output = output.concat(outputLine + "\n");
        }

        return output;
    }
}