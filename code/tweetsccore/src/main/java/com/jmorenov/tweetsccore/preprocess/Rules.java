package com.jmorenov.tweetsccore.preprocess;

import com.jmorenov.tweetsccore.extra.File;

import java.io.IOException;
import java.io.InputStream;

public class Rules {
    private String[] rulesFileStream;


    public Rules(String rulesFilename) throws IOException {
        rulesFileStream = File.readToStringArray(rulesFilename);
    }


}
