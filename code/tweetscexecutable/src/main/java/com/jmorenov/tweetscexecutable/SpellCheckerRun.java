package com.jmorenov.tweetscexecutable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.spellchecker.SpellCheckerByDictionary;

public class SpellCheckerRun {
    public static void main(String[] args) throws IOException {
        int argumentIndex;
        if ((argumentIndex = Arrays.asList(args).indexOf("-text")) != -1) {
            String text =  Arrays.asList(args).get(argumentIndex + 1);

            SpellCheckerByDictionary spellChecker = new SpellCheckerByDictionary();

            System.out.println(spellChecker.correctText(text));
        }
    }

    private void generateResult() {
        // From generate_result.py
    }
}