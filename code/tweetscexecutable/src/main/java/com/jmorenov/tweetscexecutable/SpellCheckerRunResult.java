package com.jmorenov.tweetscexecutable;

import org.apache.commons.cli.Options;

public class SpellCheckerRunResult {
    public String result;
    public Options options;

    public SpellCheckerRunResult(String result, Options options) {
        this.result = result;
        this.options = options;
    }
}