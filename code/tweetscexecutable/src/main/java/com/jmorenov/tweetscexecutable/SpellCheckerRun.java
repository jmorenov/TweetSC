package com.jmorenov.tweetscexecutable;

import java.io.IOException;
import java.util.Arrays;

import com.jmorenov.tweetsccore.spellchecker.DictionaryMethod;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.spellchecker.TweetNormEvaluator;

public class SpellCheckerRun {
    public static void main(String[] args) throws IOException {
        if (checkArguments(args)) {
            int argumentIndex;

            if ((argumentIndex = Arrays.asList(args).indexOf("-text")) != -1) {
                String text = getTextFromArguments(args, argumentIndex+1);

                SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());

                System.out.println(spellChecker.correctText(text));
            } else {
                argumentIndex = Arrays.asList(args).indexOf("-annotated")+1;
                String anotatedFile = args[argumentIndex];

                if ((argumentIndex = Arrays.asList(args).indexOf("-ids")) != -1) {
                    argumentIndex += 1;
                    String idsFile = args[argumentIndex];

                    correctFromIds(anotatedFile, idsFile);
                } else {
                    argumentIndex = Arrays.asList(args).indexOf("-tweets") +1;
                    String tweetsFile = args[argumentIndex];

                    correctFromTweets(anotatedFile, tweetsFile);
                }
            }
        } else {
            System.out.println("Arguments error");
        }
    }

    private static boolean checkArguments(String[] args) {
        return Arrays.asList(args).contains("-text")
                || (Arrays.asList(args).contains("-annotated")
                && (Arrays.asList(args).contains("-ids") || Arrays.asList(args).contains("-tweets")));
    }

    private static String getTextFromArguments(String[] arguments, int argumentIndex) {
        String text = arguments[argumentIndex];

        for (int i = argumentIndex + 1; i < arguments.length; i++) {
            text = text.concat(" " + arguments[i]);
        }

        return text;
    }

    private static void correctFromIds(String anotatedFile, String idsFile) {
        System.out.println("TO DO");
    }

    private static void correctFromTweets(String anotatedFile, String tweetsFile) throws IOException {
        TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator(anotatedFile, true);
        tweetNormEvaluator.setTweetsFile(tweetsFile);
        System.out.println(tweetNormEvaluator.evalutate(new DictionaryMethod()));
    }
}