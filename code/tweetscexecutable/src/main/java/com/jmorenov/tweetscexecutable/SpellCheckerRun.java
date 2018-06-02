package com.jmorenov.tweetscexecutable;

import java.io.IOException;
import java.util.Arrays;

import com.jmorenov.tweetsccore.spellchecker.DictionaryMethod;
import com.jmorenov.tweetsccore.spellchecker.SpellChecker;
import com.jmorenov.tweetsccore.spellchecker.TweetNormEvaluator;

public class SpellCheckerRun {
    public static void main(String[] args) throws IOException {
        if (isCorrectTextFuncionality(args)) {
            String text = getTextFromArguments(args);
            SpellChecker spellChecker = new SpellChecker(new DictionaryMethod());

            System.out.println(spellChecker.correctText(text));
        } else if (isEvaluateTweetNormFuncionality(args)) {
            String annotatedFile = getValueOfArgument(args, "-annotatedFile");
            String idsFile = getValueOfArgument(args, "-idsFile");
            String tweetFile = getValueOfArgument(args, "-tweetsFile");
            //String evaluatorScriptFile = getValueOfArgument(args, "-evaluatorScriptFile");
            String workingDirectory = getValueOfArgument(args, "-workingDirectory");
            String resultFile = getValueOfArgument(args, "-resultFile");
            TweetNormEvaluator tweetNormEvaluator = new TweetNormEvaluator(annotatedFile, true);

            tweetNormEvaluator.setWorkingDirectory(workingDirectory);
            tweetNormEvaluator.setTweetsFile(tweetFile);
            //tweetNormEvaluator.setEvaluatorScriptFile(evaluatorScriptFile);
            tweetNormEvaluator.setIdsFile(idsFile);
            tweetNormEvaluator.setResultFile(resultFile);
            System.out.println(tweetNormEvaluator.evalutate(new DictionaryMethod()));
        } else {
            System.out.println("Arguments error");
        }
    }

    private static boolean isCorrectTextFuncionality(String[] arguments) {
        return Arrays.asList(arguments).contains("-text");
    }

    private static boolean isEvaluateTweetNormFuncionality(String[] arguments) {
        return (Arrays.asList(arguments).contains("-annotatedFile")
                && (Arrays.asList(arguments).contains("-idsFile") || Arrays.asList(arguments).contains("-tweetsFile")));
    }

    private static String getTextFromArguments(String[] arguments) {
        int argumentIndex = Arrays.asList(arguments).indexOf("-text") + 1;
        String text = arguments[argumentIndex];

        for (int i = argumentIndex + 1; i < arguments.length; i++) {
            text = text.concat(" " + arguments[i]);
        }

        return text;
    }

    private static String getValueOfArgument(String[] arguments, String argument) {
        int argumentIndex = Arrays.asList(arguments).indexOf(argument);

        if (argumentIndex != -1) {
            return arguments[argumentIndex+1];
        } else {
            return "";
        }
    }
}