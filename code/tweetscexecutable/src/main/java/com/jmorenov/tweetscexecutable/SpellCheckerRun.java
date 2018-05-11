package com.jmorenov.tweetscexecutable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.jmorenov.tweetsccore.spellchecker.SpellChecker;

public class SpellCheckerRun {
    public static void main(String[] args) {
        int argumentIndex;
        if ((argumentIndex = Arrays.asList(args).indexOf("-text")) != -1) {
            String text =  Arrays.asList(args).get(argumentIndex + 1);

            SpellChecker spellChecker = new SpellChecker(text);

            System.out.println(spellChecker.correct());
        }
    }

    private void generateResult() {
        // From generate_result.py
    }
}