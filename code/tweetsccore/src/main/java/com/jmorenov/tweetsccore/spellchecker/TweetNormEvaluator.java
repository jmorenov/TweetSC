package com.jmorenov.tweetsccore.spellchecker;

public class TweetNormEvaluator {
    private boolean _verbose;
    private String _anotatedFile;
    private String _idsFile;
    private String _tweetsFile;

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

    public void evalutate() {

    }

    private void downloadTweets() {

    }
}