package com.jmorenov.spellchecker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SpellCheckerRun {
    public static void main(String[] args) {
        int argumentIndex;
        if ((argumentIndex = Arrays.asList(args).indexOf("-text")) != -1) {
            String text =  Arrays.asList(args).get(argumentIndex + 1);

            SpellChecker spellChecker = new SpellChecker(text);

            ArrayList<String> words = spellChecker.words();

            for (String word : words) {
                System.out.println(word);
            }
        }
    }

    private void generateResult() {
        // From generate_result.py
    }
}