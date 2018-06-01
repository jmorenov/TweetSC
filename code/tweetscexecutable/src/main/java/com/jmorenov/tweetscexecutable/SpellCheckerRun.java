package com.jmorenov.tweetscexecutable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.spellchecker.SpellCheckerByDictionary;

public class SpellCheckerRun {
    public static void main(String[] args) throws IOException {
        if (checkArguments(args)) {
            int argumentIndex;

            if ((argumentIndex = Arrays.asList(args).indexOf("-text")) != -1) {
                String text = getTextFromArguments(args, argumentIndex+1);

                SpellCheckerByDictionary spellChecker = new SpellCheckerByDictionary();

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
            System.out.println("Error arguments");
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

    private static void correctFromTweets(String anotatedFile, String tweetsFile) {

    }
}